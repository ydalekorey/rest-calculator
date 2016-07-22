package controllers

import javax.inject._

import services.NumberCalculationService
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

@Singleton
class NumberCalculationController @Inject() (numberCalculationService: NumberCalculationService) extends Controller {

  def getV1thResult(v1:Int) = Action {
    Ok(Json.toJson(numberCalculationService.getV1thResult(v1)))
  }

  def postV2V3V4(v2:Int, v3:Int, v4:Int) = Action {
    Ok(Json.toJson(numberCalculationService.updateV4thValue(v2,v3,v4)))
  }
}
