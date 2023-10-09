package aster.service

import aster.dao.MeetingDAO
import aster.dao.generated.Tables.MeetingRow
import aster.model.dto.Meeting
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MeetingServiceImpl @Autowired()(meetingDAO: MeetingDAO) extends MeetingService {

  def insert(meeting: Meeting): Int = {
    meetingDAO.insert(MeetingRow.apply(meeting.id, meeting.time, meeting.participants, meeting.location))
  }

  def update(meeting: Meeting): Int = {

    meetingDAO.update(MeetingRow.apply(meeting.id, meeting.time, meeting.participants, meeting.location))
  }

  def delete(id: Int): Int = {
    meetingDAO.delete(id)
  }

  def findById(id: Int): Option[Meeting] = {
    meetingDAO.findById(id).map(Meeting.fromRow)
  }

  def findAll(): Seq[Meeting] = {
    meetingDAO.findAll().map(Meeting.fromRow)
  }

}
