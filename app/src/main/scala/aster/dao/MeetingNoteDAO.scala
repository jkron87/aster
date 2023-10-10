package aster.dao

import aster.dao.generated.Tables._
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import slick.jdbc.MySQLProfile.api._

import java.util.concurrent.TimeUnit
import scala.concurrent.Await
import scala.concurrent.duration.Duration

@Component
class MeetingNoteDAO @Autowired()(private val database: Database) {

  def insert(note: MeetingNoteRow): Int = {
    Await.result(database.run(MeetingNote += note), Duration(60, TimeUnit.SECONDS))
  }

  def findByMeetingId(meetingId: Int): Seq[MeetingNoteRow] = {
    val query = MeetingNote.filter(_.meetingId === meetingId).result
    Await.result(database.run(query), Duration(60, TimeUnit.SECONDS))
  }
}
