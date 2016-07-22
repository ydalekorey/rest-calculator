package models

import play.api.libs.json._

case class CalculationResult(result: Int)

object CalculationResult {
  implicit val calculationResultFormat: Format[CalculationResult] = Json.format[CalculationResult]
}
