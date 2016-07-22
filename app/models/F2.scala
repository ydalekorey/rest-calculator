package models

import play.api.libs.json._

case class F2(value: Int)

object F2 {
  implicit val f2Format: Format[F2] = Json.format[F2]
}
