package services

import java.io.{File, PrintWriter}
import java.util.concurrent.locks.ReentrantReadWriteLock.{ReadLock, WriteLock}
import java.util.concurrent.locks.ReentrantReadWriteLock
import javax.inject.{Inject, Singleton}

import com.google.inject.ImplementedBy
import play.api.Configuration

import scala.io.Source

/**
  * Created by ydalekorey on 7/22/16.
  */
@ImplementedBy(classOf[CsvFileF2Container])
trait F2Container {
  def writeByIndex(v4: Int, i: Int): Unit

  def getValueByIndex(v1: Int): Int
}

@Singleton
class CsvFileF2Container @Inject() (val configuration: Configuration, f1Container: F1Container) extends F2Container {

  val f2Location = configuration.getString("f2.location").getOrElse("f2.csv")

  val readWriteLock: ReentrantReadWriteLock = new ReentrantReadWriteLock()
  val readLock: ReadLock = readWriteLock.readLock()
  val writeLock: WriteLock = readWriteLock.writeLock()


  def init(): Unit = withWriteLock {
    val size = f1Container.getSize()

    val zeroValues = List.fill(size)("0")

    val pw = new PrintWriter(new File(f2Location))
    val fileContent = zeroValues.mkString(", ")
    pw.write(fileContent)
    pw.close

  }

  init()

  override def writeByIndex(v4: Int, i: Int): Unit = withWriteLock {


    val bufferedSource = Source.fromFile(f2Location)
    val numbers = bufferedSource.getLines.next().split(",").map(_.trim).toVector
    bufferedSource.close

    val updatedNumbers = numbers updated(v4, i.toString)

    val pw = new PrintWriter(new File(f2Location))
    val fileContent = updatedNumbers.mkString(", ")
    pw.write(fileContent)
    pw.close
  }

  override def getValueByIndex(v1: Int): Int = withReadLock(v1) { v1 =>
    val bufferedSource = Source.fromFile(f2Location)

    val numbers = bufferedSource.getLines.next().split(",").map(_.trim).map(_.toInt).toVector

    bufferedSource.close

    numbers(v1)
  }

  private def withWriteLock(block: () => Unit): Unit = {
    writeLock.lock()
    try {
      block()
    } finally {
      writeLock.unlock()
    }
  }

  private def withReadLock(v:Int)(block:Int => Int): Int = {
    readLock.lock()
    try {
      block(v)
    } finally {
      readLock.unlock()
    }
  }
}
