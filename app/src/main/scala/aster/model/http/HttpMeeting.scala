package aster.model.http

import aster.model.dto.Meeting

import java.text.SimpleDateFormat
import scala.beans.BeanProperty

case class HttpMeeting(
                            @BeanProperty id: Int,
                            @BeanProperty time: String,
                            @BeanProperty participants: String,
                            @BeanProperty location: String
                          )

object HttpMeeting {
  def fromDTO(dto: Meeting): HttpMeeting = {
    val dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    HttpMeeting(dto.id, dateFormat.format(dto.time), dto.participants, dto.location)
  }
}
