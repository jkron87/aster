package aster.dao

import aster.dao.generated.Tables._
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import slick.jdbc.MySQLProfile.api._

import java.util.concurrent.TimeUnit
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

@Component
class MeetingDAO @Autowired()(private val database: Database) {

  def insertWithParticipants(meeting: MeetingRow, participantIds: Seq[Int]): Int = {
    val insertAction = for {
      meetingId <- (Meeting returning Meeting.map(_.id)) += meeting
      _ <- MeetingParticipant ++= participantIds.map(participantId => MeetingParticipantRow(meetingId, participantId))
    } yield meetingId

    Await.result(database.run(insertAction.transactionally), Duration(60, TimeUnit.SECONDS))
  }


  def findAll(): Seq[MeetingRow] = {
    Await.result(database.run(Meeting.result), Duration(60, TimeUnit.SECONDS))
  }

}
