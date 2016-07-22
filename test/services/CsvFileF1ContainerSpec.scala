package services

import java.io.{File, PrintWriter}

import org.scalatest.BeforeAndAfter
import org.scalatest.mock.MockitoSugar
import org.scalatestplus.play.PlaySpec

/**
  * Created by ydalekorey on 7/22/16.
  */
class CsvFileF1ContainerSpec extends PlaySpec with MockitoSugar with BeforeAndAfter {

  private var f1Container: F1Container = _

  before {
    val f1File = new File("f1.csv")
    val content = "1, 2, 3, 4, 5"
    createTestFile(f1File, content)

    f1Container = new CsvFileF1Container(f1File.getAbsolutePath)

  }


  "CsvFileF2Container" should {
    "read appropriate value from specified column in csv file" in {

      f1Container.getValueByIndex(3) must equal(4)

    }
  }

  def createTestFile(f2File: File, content: String): Unit = {
    val pw = new PrintWriter(f2File)
    pw.write(content)
    pw.close()
  }

}
