package aster.model.http

import aster.model.dto.MeetingDto

import java.text.SimpleDateFormat
import java.util.Collections
import scala.beans.BeanProperty
import scala.collection.JavaConverters._

case class HttpMeeting(
                        @BeanProperty id: Int,
                        @BeanProperty time: String,
                        @BeanProperty participants: java.util.List[Int] = Collections.emptyList(),
                        @BeanProperty location: String
                      )

object HttpMeeting {
  def fromDTO(dto: MeetingDto): HttpMeeting = {
    val dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    HttpMeeting(dto.id, dateFormat.format(dto.time), dto.participants.asJava, dto.location)
  }
}
