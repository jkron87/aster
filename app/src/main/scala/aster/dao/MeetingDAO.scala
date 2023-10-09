package aster.dao

import aster.dao.generated.Tables._
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import slick.jdbc.MySQLProfile.api._

import java.util.concurrent.TimeUnit
import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration

@Component
class MeetingDAO @Autowired()(private val database: Database){

  def insert(meeting: MeetingRow): Int = {
    Await.result(database.run(Meeting += meeting), Duration(60, TimeUnit.SECONDS))
  }

  def update(meeting: MeetingRow): Int = {
    Await.result(database.run(Meeting.filter(_.id === meeting.id).update(meeting)), Duration(60, TimeUnit.SECONDS))
  }

  def delete(id: Int): Int = {
    Await.result(database.run(Meeting.filter(_.id === id).delete), Duration(60, TimeUnit.SECONDS))
  }

  def findById(id: Int): Option[MeetingRow] = {
    Await.result(database.run(Meeting.filter(_.id === id).result.headOption), Duration(60, TimeUnit.SECONDS))
  }

  def findAll(): Seq[MeetingRow] = {
    Await.result(database.run(Meeting.result), Duration(60, TimeUnit.SECONDS))
  }

}
