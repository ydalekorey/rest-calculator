package models

import play.api.libs.json.{Format, Json}

/**
  * Created by ydalekorey on 7/23/16.
  */
case class UpdateRequest(v2:Int, v3:Int, v4:Int)

object UpdateRequest {
  implicit val updateRequestFormat: Format[UpdateRequest] = Json.format[UpdateRequest]
}