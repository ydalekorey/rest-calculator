package controllers

import javax.inject._

import services.NumberCalculationService
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

@Singleton
class NumberCalculationController @Inject() (numberCalculationService: NumberCalculationService) extends Controller {

  def getF2(v1:Int) = Action {
    Ok(Json.toJson(numberCalculationService.getF2(v1)))
  }
}
