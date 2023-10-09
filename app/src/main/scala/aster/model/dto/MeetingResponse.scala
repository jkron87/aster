package aster.model.dto

import java.text.SimpleDateFormat

case class MeetingResponse(id: Int, time: String, participants: String, location: String)

object MeetingResponse {
  def fromDTO(dto: Meeting): MeetingResponse = {
    val dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    MeetingResponse(dto.id, dateFormat.format(dto.time), dto.participants, dto.location)
  }
}
