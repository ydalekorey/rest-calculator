package services

import java.io.{File, PrintWriter}

import org.mockito.Mockito._
import org.scalatest.BeforeAndAfter
import org.scalatest.mock.MockitoSugar
import org.scalatestplus.play.PlaySpec
import play.api.Configuration

/**
  * Created by ydalekorey on 7/22/16.
  */
class CsvFileF2ContainerSpec extends PlaySpec with MockitoSugar with BeforeAndAfter {

  private var f2Container: F2Container = _

  private var f1Container: F1Container = _

  private var configuration: Configuration = _

  before {
    val f2File = new File("f2.csv")
    val content = "1, 2, 3, 4, 5"
    createTestFile(f2File, content)

    configuration = Configuration.apply("f2.location"-> f2File.getAbsolutePath)

    f1Container = mock[F1Container]

    when(f1Container.getSize()).thenReturn(2)

    f2Container = new CsvFileF2Container(configuration, f1Container)

  }


  "CsvFileF2Container" should {
    "write appropriate value to specified column in csv file" in {

      f2Container.writeByIndex(1, 10)

      f2Container.getValueByIndex(1) must equal(10)

    }
  }

  "CsvFileF2Container" should {
    "init underlying file by zeros" in {

      f2Container.getValueByIndex(0) must equal(0)
      f2Container.getValueByIndex(1) must equal(0)

    }
  }

  def createTestFile(f2File: File, content: String): Unit = {
    val pw = new PrintWriter(f2File)
    pw.write(content)
    pw.close()
  }

}
