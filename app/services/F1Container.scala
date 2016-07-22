package services

import javax.inject.{Inject, Singleton}

import com.google.inject.ImplementedBy
import play.api.Configuration

import scala.io.Source

/**
  * Created by ydalekorey on 7/22/16.
  */
@ImplementedBy(classOf[CsvFileF1Container])
trait F1Container {
  def getValueByIndex(v3: Int): Int

  def getSize(): Int
}

@Singleton
class CsvFileF1Container @Inject() (val configuration: Configuration) extends F1Container {

  val f1Location = configuration.getString("f1.location").getOrElse("f1.csv")

  override def getValueByIndex(v1: Int): Int = {

    val bufferedSource = Source.fromFile(f1Location)

    val numbers = bufferedSource.getLines.next().split(",").map(_.trim).map(_.toInt).toVector

    bufferedSource.close

    numbers(v1)
  }

  override def getSize(): Int = {
    val bufferedSource = Source.fromFile(f1Location)

    val size = bufferedSource.getLines.next().split(",").size

    bufferedSource.close

    size
  }
}