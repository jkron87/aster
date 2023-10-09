package aster.model.dto

import aster.dao.generated.Tables.MeetingRow
import aster.model.http.HttpMeeting

import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

case class Meeting(id: Int, time: Timestamp, participants: String, location: String)

object Meeting {
  def fromRow(row: MeetingRow): Meeting =
    Meeting(row.id, row.time, row.participants, row.location)

  def fromHTTP(meeting: HttpMeeting): Meeting = {
    Meeting(meeting.id, meeting.time.toTimestamp, meeting.participants, meeting.location)
  }

  implicit class StringToTimestamp(s: String) {
    def toTimestamp: Timestamp = {
      val localDateTime = LocalDateTime.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
      Timestamp.valueOf(localDateTime)
    }
  }
}
