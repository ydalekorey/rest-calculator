package services

import java.io.{File, PrintWriter}

import org.scalatest.BeforeAndAfter
import org.scalatest.mock.MockitoSugar
import org.scalatestplus.play.PlaySpec
import play.api.Configuration

/**
  * Created by ydalekorey on 7/22/16.
  */
class CsvFileF2ContainerSpec extends PlaySpec with MockitoSugar with BeforeAndAfter {

  private var f2Container: F2Container = _

  private var configuration: Configuration = _

  before {
    val f2File = new File("f2.csv")
    val content = "1, 2, 3, 4, 5"
    createTestFile(f2File, content)

    configuration = Configuration.apply("f2.location"-> f2File.getAbsolutePath)

    f2Container = new CsvFileF2Container(configuration)

  }


  "CsvFileF2Container" should {
    "read appropriate value from specified column in csv file" in {

      f2Container.getValueByIndex(3) must equal(4)

    }
  }

  "CsvFileF2Container" should {
    "write value to specified column in csv file" in {

      f2Container.writeByIndex(3, 10)

      f2Container.getValueByIndex(3) must equal(10)

    }
  }

  def createTestFile(f2File: File, content: String): Unit = {
    val pw = new PrintWriter(f2File)
    pw.write(content)
    pw.close()
  }

}
