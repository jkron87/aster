package aster.model.dto
import aster.dao.generated.Tables._

case class MeetingParticipantDto(userId: Int, meetingId: Int)

object MeetingParticipantDto {
  def fromRow(meetingParticipantRow: MeetingParticipantRow): MeetingParticipantDto =
    MeetingParticipantDto(meetingParticipantRow.userId, meetingParticipantRow.meetingId)
}

//  def fromHTTP(meeting: HttpMeeting): Meeting = {
//    Meeting(meeting.id, meeting.time.toTimestamp, meeting.location)
//  }
