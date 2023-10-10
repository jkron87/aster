package aster.service

import aster.dao.{MeetingDAO, MeetingParticipantDAO}
import aster.dao.generated.Tables._
import aster.model.dto.MeetingDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MeetingServiceImpl @Autowired()(meetingDAO: MeetingDAO, meetingParticipantDAO: MeetingParticipantDAO) extends MeetingService {

  /**
   * Insert a new meeting along with its participants into the database.
   *
   * @param meeting the meeting details to be inserted.
   * @return the generated ID of the inserted meeting.
   */
  def insert(meeting: MeetingDto): Int = {
    val meetingId: Int = meetingDAO.insertWithParticipants(MeetingRow.apply(meeting.id, meeting.time, meeting.location), meeting.participants)
    meetingId
  }

  /**
   * Fetch all meetings from the database along with their participants.
   *
   * @return a sequence of MeetingDto representing all meetings and their participants.
   */
  def findAll(): Seq[MeetingDto] = {
    val meetings = meetingDAO.findAll()

    meetings.map { meeting =>
      val participants = meetingParticipantDAO.findAllForMeeting(meeting.id).map(_.userId)
      MeetingDto(meeting.id, meeting.time, participants, meeting.location)
    }
  }

}
