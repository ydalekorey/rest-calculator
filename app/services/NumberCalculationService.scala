package services

import javax.inject.{Inject, Singleton}

import com.google.inject.ImplementedBy
import models.{CalculationResult, V1thResult}

/**
  * Created by ydalekorey on 7/22/16.
  */

@ImplementedBy(classOf[DefaultNumberCalculationService])
trait NumberCalculationService {

  def updateV4thValue(v2: Int, v3: Int, v4: Int): CalculationResult


  def getV1thResult(v1: Int): V1thResult

}

@Singleton
class DefaultNumberCalculationService @Inject() (f1Container: F1Container, f2Container: F2Container) extends NumberCalculationService {
  override def getV1thResult(v1: Int): V1thResult = {
    val f2 = f2Container.getValueByIndex(v1)
    if (f2 > 10) {
      V1thResult(f2 - 10)
    } else {
      V1thResult(f2)
    }
  }

  override def updateV4thValue(v2: Int, v3: Int, v4: Int): CalculationResult = {
    val f1 = f1Container.getValueByIndex(v3)

    if (f1 + v2 < 10) {
      f2Container.writeByIndex(v4, f1+v2+10)
      CalculationResult(0)
    } else {
      f2Container.writeByIndex(v4, f1+v2)
      CalculationResult(1)
    }
  }
}
