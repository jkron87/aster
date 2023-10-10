package aster.model.dto

import aster.dao.generated.Tables._
import aster.model.http.HttpMeetingNote

case class MeetingNoteDto(
                           id: Int,
                           meetingId: Int,
                           text: String,
                           attachmentUrl: Option[String] = None
                         )

object MeetingNoteDto {

  def fromRow(meetingNoteRow: MeetingNoteRow): MeetingNoteDto =
    MeetingNoteDto(
      meetingNoteRow.id,
      meetingNoteRow.meetingId,
      meetingNoteRow.text,
      meetingNoteRow.attachmentLocation
    )

  def toRow(meetingNoteDto: MeetingNoteDto): MeetingNoteRow = {
    MeetingNoteRow(
      meetingNoteDto.id,
      meetingNoteDto.meetingId,
      meetingNoteDto.text,
      meetingNoteDto.attachmentUrl
    )
  }

  def fromHTTP(meetingNote: HttpMeetingNote): MeetingNoteDto = {
    MeetingNoteDto(
      meetingNote.id,
      meetingNote.meetingId,
      meetingNote.text,
      Option(meetingNote.attachmentUrl)
    )
  }

}
