package models

import play.api.libs.json._

case class V1thResult(value: Int)

object V1thResult {
  implicit val v1thResultFormat: Format[V1thResult] = Json.format[V1thResult]
}
