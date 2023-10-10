package aster.model.http

import aster.model.dto.MeetingNoteDto

import scala.beans.BeanProperty

/**
 * Represents the HTTP data transfer object for a MeetingNote.
 *
 * @param id The note ID.
 * @param meetingId The ID of the associated meeting.
 * @param text The text content of the note.
 * @param attachmentUrl The URL pointing to an attachment or null if not present.
 */
case class HttpMeetingNote(
                            @BeanProperty id: Int,
                            @BeanProperty meetingId: Int,
                            @BeanProperty text: String,
                            @BeanProperty attachmentUrl: String = null
                          )

object HttpMeetingNote {
  def fromDTO(dto: MeetingNoteDto): HttpMeetingNote = {
    HttpMeetingNote(dto.id, dto.meetingId, dto.text, dto.attachmentUrl.orNull)
  }

  def toDTO(httpMeetingNote: HttpMeetingNote): MeetingNoteDto = {
    MeetingNoteDto(httpMeetingNote.id, httpMeetingNote.meetingId, httpMeetingNote.text, Option(httpMeetingNote.attachmentUrl))
  }
}
