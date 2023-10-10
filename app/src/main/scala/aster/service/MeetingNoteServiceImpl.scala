package aster.service

import aster.dao.MeetingNoteDAO
import aster.model.dto.MeetingNoteDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MeetingNoteServiceImpl @Autowired()(meetingNoteDAO: MeetingNoteDAO) extends MeetingNoteService {

  def insert(note: MeetingNoteDto): Int = {
    meetingNoteDAO.insert(MeetingNoteDto.toRow(note))
  }

  def findByMeetingId(meetingId: Int): Seq[MeetingNoteDto] = {
    meetingNoteDAO.findByMeetingId(meetingId).map(MeetingNoteDto.fromRow)
  }
}
