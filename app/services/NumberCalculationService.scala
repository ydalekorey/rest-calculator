package services

import javax.inject.{Inject, Singleton}

import models.V1thResult

/**
  * Created by ydalekorey on 7/22/16.
  */
trait NumberCalculationService {

  def getV1thResult(v1: Int): V1thResult

}

@Singleton
class DefaultNumberCalculationService @Inject() (f2Container: F2Container) extends NumberCalculationService {
  override def getV1thResult(v1: Int): V1thResult = {
    val f2 = f2Container.getValueByIndex(v1)
    if (f2 > 10) {
      V1thResult(f2 - 10)
    } else {
      V1thResult(f2)
    }
  }
}
