package aster.model.dto

import aster.dao.generated.Tables.MeetingRow

case class Meeting(id: Int, time: java.sql.Timestamp, participants: String, location: String)

object Meeting {
  def fromRow(row: MeetingRow): Meeting = {
    Meeting(row.id, row.time, row.participants, row.location)
  }
}
