package controllers


import akka.stream.Materializer
import models.F2

import scala.concurrent.Future
import play.api.libs.json.Json
import play.api.mvc._
import play.api.test._
import play.api.test.Helpers._
import org.scalatest.mock.MockitoSugar
import org.scalatestplus.play._
import org.mockito.Mockito._
import org.scalatest.BeforeAndAfter
import services.NumberCalculationService


class NumberCalculationControllerSpec extends PlaySpec with MockitoSugar with Results with OneAppPerSuite with BeforeAndAfter {

  private var numberCalculationController: NumberCalculationController = _

  private var numberCalculationService: NumberCalculationService = _


  before {
    numberCalculationService = mock[NumberCalculationService]
    numberCalculationController = new NumberCalculationController(numberCalculationService)
  }

  "NumberCalculationController controller" should {


    "return appropriate number" when {
      "v1 index passed" in {
        when(numberCalculationService.getF2(1)).thenReturn(F2(2))
        val result = await(numberCalculationController.getF2(1).apply(FakeRequest()))

        result mustBe Ok(Json.toJson(F2(2)))

        verify(numberCalculationService).getF2(1)
      }
    }
  }

}
