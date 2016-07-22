package controllers

import javax.inject._

import models.UpdateRequest
import services.NumberCalculationService
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

@Singleton
class NumberCalculationController @Inject() (numberCalculationService: NumberCalculationService) extends Controller {

  def getV1thResult(v1:Int) = Action {
    Ok(Json.toJson(numberCalculationService.getV1thResult(v1)))
  }

  def postV2V3V4() = Action(parse.json) {implicit request =>

    val ur =  request.body.as[UpdateRequest]
    Ok(Json.toJson(numberCalculationService.updateV4thValue(ur.v2,ur.v3,ur.v4)))
  }
}
