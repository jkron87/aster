package aster.dao

import aster.dao.generated.Tables._
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import slick.jdbc.MySQLProfile.api._

import java.util.concurrent.TimeUnit
import scala.concurrent.Await
import scala.concurrent.duration.{Duration, DurationInt}

@Component
class MeetingParticipantDAO @Autowired()(private val database: Database){

  def insert(meetingParticipant: MeetingParticipantRow): Int = {
    Await.result(database.run(MeetingParticipant += meetingParticipant), Duration(60, TimeUnit.SECONDS))
  }

  def findAllForMeeting(id: Int): Seq[MeetingParticipantRow] = {
    val query = MeetingParticipant.filter(_.meetingId === id).result
    Await.result(database.run(query), 60.seconds)
  }

}
