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
}
