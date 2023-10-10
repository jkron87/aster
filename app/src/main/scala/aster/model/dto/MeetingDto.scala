package aster.model.dto

import aster.dao.generated.Tables._
import aster.model.http.HttpMeeting

import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import scala.collection.convert.ImplicitConversions.`collection AsScalaIterable`

case class MeetingDto(id: Int, time: Timestamp, participants: Seq[Int], location: String)

object MeetingDto {
  def fromRow(meetingRow: MeetingRow, meetingParticipants: Seq[MeetingParticipantRow]): MeetingDto =
    MeetingDto(meetingRow.id, meetingRow.time, meetingParticipants.map(_.userId), meetingRow.location)

  def fromHTTP(meeting: HttpMeeting): MeetingDto = {
    MeetingDto(meeting.id, meeting.time.toTimestamp, meeting.participants.toSeq, meeting.location)
  }

  implicit class StringToTimestamp(s: String) {
    def toTimestamp: Timestamp = {
      val localDateTime = LocalDateTime.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
      Timestamp.valueOf(localDateTime)
    }
  }
}
