package services

import javax.inject.{Inject, Singleton}

import play.api.Configuration

import scala.io.Source

/**
  * Created by ydalekorey on 7/22/16.
  */
trait F1Container {
  def getValueByIndex(v3: Int): Int
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
}