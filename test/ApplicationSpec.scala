import java.io.{File, PrintWriter}

import models.{UpdateRequest, V1thResult}
import org.scalatest.{BeforeAndAfter, TestData}
import org.scalatestplus.play._
import play.api.Application
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.libs.json.Json
import play.api.test._
import play.api.test.Helpers._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
class ApplicationSpec extends PlaySpec with OneAppPerTest with BeforeAndAfter {

  implicit override def newAppForTest(td: TestData): Application = {

    val f1File = new File("f1.csv")
    val content = "1, 2, 3, 4, 5"
    createTestFile(f1File, content)

    val f2File = new File("f2.csv")

    new GuiceApplicationBuilder().configure(
      Map(
        "f1.location" -> f1File.getAbsolutePath,
        "f2.location" -> f2File.getAbsolutePath
      )).build()
  }

  "NumberCalculationController" should {

    "return correct v1-th result" in {

      val v2=19
      val v3=2
      val v4=3
      val v1=v4

      await(route(app, FakeRequest(POST, "/postv2v3v4").withJsonBody(Json.toJson(UpdateRequest(v2,v3,v4)))).get)

      val result = route(app, FakeRequest(GET, s"/getf1v1?v1=${v1}")).get

      status(result) mustBe OK
      contentAsJson(result) must equal(Json.toJson(V1thResult(12)))
    }

  }

  def createTestFile(f2File: File, content: String): Unit = {
    val pw = new PrintWriter(f2File)
    pw.write(content)
    pw.close()
  }

}
