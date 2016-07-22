package services

import java.io.{File, PrintWriter}
import javax.inject.Singleton

import scala.io.Source

/**
  * Created by ydalekorey on 7/22/16.
  */
trait F2Container {
  def writeByIndex(v4: Int, i: Int): Unit

  def getValueByIndex(v1: Int): Int
}

@Singleton
class CsvFileF2Container(val f2Location: String) extends F2Container {

  override def writeByIndex(v4: Int, i: Int): Unit = {
    val bufferedSource = Source.fromFile(f2Location)
    val numbers = bufferedSource.getLines.next().split(",").map(_.trim).toVector
    bufferedSource.close

    val updatedNumbers = numbers updated(v4, i.toString)

    val pw = new PrintWriter(new File(f2Location))
    val fileContent = updatedNumbers.mkString(", ")
    pw.write(fileContent)
    pw.close
  }

  override def getValueByIndex(v1: Int): Int = {
    val bufferedSource = Source.fromFile(f2Location)

    val numbers = bufferedSource.getLines.next().split(",").map(_.trim).map(_.toInt).toVector

    bufferedSource.close

    numbers(v1)
  }
}
