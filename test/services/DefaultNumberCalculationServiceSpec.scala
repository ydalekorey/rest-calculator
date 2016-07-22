package services

import models.{CalculationResult, V1thResult}
import org.mockito.Mockito._
import org.scalatest.BeforeAndAfter
import org.scalatest.mock.MockitoSugar
import org.scalatestplus.play.PlaySpec

/**
  * Created by ydalekorey on 7/22/16.
  */
class DefaultNumberCalculationServiceSpec extends PlaySpec with MockitoSugar with BeforeAndAfter{

  private var f2Container: F2Container = _

  private var f1Container: F1Container = _

  private var defaultNumberCalculationService: DefaultNumberCalculationService = _

  before {
    f2Container = mock[F2Container]
    f1Container = mock[F1Container]
    defaultNumberCalculationService = new DefaultNumberCalculationService(f1Container, f2Container)
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

    "return 0 if f1[v3]+v2 < 10" in {
      val v2 = 1
      val v3 = 2
      val v4 = 3

      when(f1Container.getValueByIndex(v3)).thenReturn(1)

      defaultNumberCalculationService.updateV4thValue(v2,v3,v4) must equal(CalculationResult(0))
    }

    "return 1 if f1[v3]+v2 >= 10" in {
      val v2 = 1
      val v3 = 2
      val v4 = 3

      when(f1Container.getValueByIndex(v3)).thenReturn(9)

      defaultNumberCalculationService.updateV4thValue(v2,v3,v4) must equal(CalculationResult(1))
    }

    "write f1[v3]+v2 +10 to f2[v4] if f1[v3]+v2 < 10" in {
      val v2 = 1
      val v3 = 2
      val v4 = 3

      when(f1Container.getValueByIndex(v3)).thenReturn(1)

      defaultNumberCalculationService.updateV4thValue(v2,v3,v4)

      verify(f2Container).writeByIndex(v4, f1Container.getValueByIndex(v3)+v2+10)
    }

    "write f1[v3]+v2 to f2[v4] if f1[v3]+v2 >= 10" in {
      val v2 = 1
      val v3 = 2
      val v4 = 3

      when(f1Container.getValueByIndex(v3)).thenReturn(9)

      defaultNumberCalculationService.updateV4thValue(v2,v3,v4)

      verify(f2Container).writeByIndex(v4, f1Container.getValueByIndex(v3)+v2)
    }
  }

}
