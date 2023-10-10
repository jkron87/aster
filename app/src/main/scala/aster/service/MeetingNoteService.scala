package aster.service

import aster.model.dto.MeetingNoteDto

trait MeetingNoteService {
  def insert(note: MeetingNoteDto): Int
  def findByMeetingId(meetingId: Int): Seq[MeetingNoteDto]
}
