package services

import models.V1thResult
import org.mockito.Mockito._
import org.scalatest.BeforeAndAfter
import org.scalatest.mock.MockitoSugar
import org.scalatestplus.play.PlaySpec

/**
  * Created by ydalekorey on 7/22/16.
  */
class DefaultNumberCalculationServiceSpec extends PlaySpec with MockitoSugar with BeforeAndAfter{

  private var f2Container: F2Container = _

  private var defaultNumberCalculationService: DefaultNumberCalculationService = _

  before {
    f2Container = mock[F2Container]
    defaultNumberCalculationService = new DefaultNumberCalculationService(f2Container)
  }

  "DefaultNumberCalculationService" should {
    "return f2[v1] - 10 if f2[v1]> 10" in {
      when(f2Container.getValueByIndex(1)).thenReturn(12)

      defaultNumberCalculationService.getV1thResult(1) must equal(V1thResult(2))
    }

    "return f2[v1] if f2[v1]<= 10" in {
      when(f2Container.getValueByIndex(1)).thenReturn(7)

      defaultNumberCalculationService.getV1thResult(1) must equal(V1thResult(7))
    }
  }

}
