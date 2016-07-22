package controllers


import models.{CalculationResult, V1thResult}
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
        when(numberCalculationService.getV1thResult(1)).thenReturn(V1thResult(2))
        val result = await(numberCalculationController.getV1thResult(1).apply(FakeRequest()))

        result mustBe Ok(Json.toJson(V1thResult(2)))

        verify(numberCalculationService).getV1thResult(1)
      }
    }

    "return appropriate calculation result" when {
      "v2, v3, v4 values submitted" in {
        when(numberCalculationService.updateV4thValue(1,2,3)).thenReturn(CalculationResult(true))
        val result = await(numberCalculationController.postV2V3V4(1,2,3).apply(FakeRequest()))

        result mustBe Ok(Json.toJson(CalculationResult(true)))

        verify(numberCalculationService).updateV4thValue(1,2,3)
      }
    }
  }

}
