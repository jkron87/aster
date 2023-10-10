package aster.dao.generated
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.jdbc.MySQLProfile
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = FlywaySchemaHistory.schema ++ Meeting.schema ++ MeetingNote.schema ++ MeetingParticipant.schema ++ User.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table FlywaySchemaHistory
   *  @param installedRank Database column installed_rank SqlType(INT), PrimaryKey
   *  @param version Database column version SqlType(VARCHAR), Length(50,true), Default(None)
   *  @param description Database column description SqlType(VARCHAR), Length(200,true)
   *  @param `type` Database column type SqlType(VARCHAR), Length(20,true)
   *  @param script Database column script SqlType(VARCHAR), Length(1000,true)
   *  @param checksum Database column checksum SqlType(INT), Default(None)
   *  @param installedBy Database column installed_by SqlType(VARCHAR), Length(100,true)
   *  @param installedOn Database column installed_on SqlType(TIMESTAMP)
   *  @param executionTime Database column execution_time SqlType(INT)
   *  @param success Database column success SqlType(BIT) */
  case class FlywaySchemaHistoryRow(installedRank: Int, version: Option[String] = None, description: String, `type`: String, script: String, checksum: Option[Int] = None, installedBy: String, installedOn: java.sql.Timestamp, executionTime: Int, success: Boolean)
  /** GetResult implicit for fetching FlywaySchemaHistoryRow objects using plain SQL queries */
  implicit def GetResultFlywaySchemaHistoryRow(implicit e0: GR[Int], e1: GR[Option[String]], e2: GR[String], e3: GR[Option[Int]], e4: GR[java.sql.Timestamp], e5: GR[Boolean]): GR[FlywaySchemaHistoryRow] = GR{
    prs => import prs._
    FlywaySchemaHistoryRow.tupled((<<[Int], <<?[String], <<[String], <<[String], <<[String], <<?[Int], <<[String], <<[java.sql.Timestamp], <<[Int], <<[Boolean]))
  }
  /** Table description of table flyway_schema_history. Objects of this class serve as prototypes for rows in queries.
   *  NOTE: The following names collided with Scala keywords and were escaped: type */
  class FlywaySchemaHistory(_tableTag: Tag) extends profile.api.Table[FlywaySchemaHistoryRow](_tableTag, Some("mydatabase"), "flyway_schema_history") {
    def * = (installedRank, version, description, `type`, script, checksum, installedBy, installedOn, executionTime, success) <> (FlywaySchemaHistoryRow.tupled, FlywaySchemaHistoryRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(installedRank), version, Rep.Some(description), Rep.Some(`type`), Rep.Some(script), checksum, Rep.Some(installedBy), Rep.Some(installedOn), Rep.Some(executionTime), Rep.Some(success))).shaped.<>({r=>import r._; _1.map(_=> FlywaySchemaHistoryRow.tupled((_1.get, _2, _3.get, _4.get, _5.get, _6, _7.get, _8.get, _9.get, _10.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column installed_rank SqlType(INT), PrimaryKey */
    val installedRank: Rep[Int] = column[Int]("installed_rank", O.PrimaryKey)
    /** Database column version SqlType(VARCHAR), Length(50,true), Default(None) */
    val version: Rep[Option[String]] = column[Option[String]]("version", O.Length(50,varying=true), O.Default(None))
    /** Database column description SqlType(VARCHAR), Length(200,true) */
    val description: Rep[String] = column[String]("description", O.Length(200,varying=true))
    /** Database column type SqlType(VARCHAR), Length(20,true)
     *  NOTE: The name was escaped because it collided with a Scala keyword. */
    val `type`: Rep[String] = column[String]("type", O.Length(20,varying=true))
    /** Database column script SqlType(VARCHAR), Length(1000,true) */
    val script: Rep[String] = column[String]("script", O.Length(1000,varying=true))
    /** Database column checksum SqlType(INT), Default(None) */
    val checksum: Rep[Option[Int]] = column[Option[Int]]("checksum", O.Default(None))
    /** Database column installed_by SqlType(VARCHAR), Length(100,true) */
    val installedBy: Rep[String] = column[String]("installed_by", O.Length(100,varying=true))
    /** Database column installed_on SqlType(TIMESTAMP) */
    val installedOn: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("installed_on")
    /** Database column execution_time SqlType(INT) */
    val executionTime: Rep[Int] = column[Int]("execution_time")
    /** Database column success SqlType(BIT) */
    val success: Rep[Boolean] = column[Boolean]("success")

    /** Index over (success) (database name flyway_schema_history_s_idx) */
    val index1 = index("flyway_schema_history_s_idx", success)
  }
  /** Collection-like TableQuery object for table FlywaySchemaHistory */
  lazy val FlywaySchemaHistory = new TableQuery(tag => new FlywaySchemaHistory(tag))

  /** Entity class storing rows of table Meeting
   *  @param id Database column id SqlType(INT), AutoInc, PrimaryKey
   *  @param time Database column time SqlType(TIMESTAMP)
   *  @param location Database column location SqlType(VARCHAR), Length(255,true) */
  case class MeetingRow(id: Int, time: java.sql.Timestamp, location: String)
  /** GetResult implicit for fetching MeetingRow objects using plain SQL queries */
  implicit def GetResultMeetingRow(implicit e0: GR[Int], e1: GR[java.sql.Timestamp], e2: GR[String]): GR[MeetingRow] = GR{
    prs => import prs._
    MeetingRow.tupled((<<[Int], <<[java.sql.Timestamp], <<[String]))
  }
  /** Table description of table meeting. Objects of this class serve as prototypes for rows in queries. */
  class Meeting(_tableTag: Tag) extends profile.api.Table[MeetingRow](_tableTag, Some("mydatabase"), "meeting") {
    def * = (id, time, location) <> (MeetingRow.tupled, MeetingRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(time), Rep.Some(location))).shaped.<>({r=>import r._; _1.map(_=> MeetingRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column time SqlType(TIMESTAMP) */
    val time: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("time")
    /** Database column location SqlType(VARCHAR), Length(255,true) */
    val location: Rep[String] = column[String]("location", O.Length(255,varying=true))
  }
  /** Collection-like TableQuery object for table Meeting */
  lazy val Meeting = new TableQuery(tag => new Meeting(tag))

  /** Entity class storing rows of table MeetingNote
   *  @param id Database column id SqlType(INT), AutoInc, PrimaryKey
   *  @param meetingId Database column meeting_id SqlType(INT)
   *  @param text Database column text SqlType(TEXT)
   *  @param attachmentLocation Database column attachment_location SqlType(TEXT), Default(None) */
  case class MeetingNoteRow(id: Int, meetingId: Int, text: String, attachmentLocation: Option[String] = None)
  /** GetResult implicit for fetching MeetingNoteRow objects using plain SQL queries */
  implicit def GetResultMeetingNoteRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[String]]): GR[MeetingNoteRow] = GR{
    prs => import prs._
    MeetingNoteRow.tupled((<<[Int], <<[Int], <<[String], <<?[String]))
  }
  /** Table description of table meeting_note. Objects of this class serve as prototypes for rows in queries. */
  class MeetingNote(_tableTag: Tag) extends profile.api.Table[MeetingNoteRow](_tableTag, Some("mydatabase"), "meeting_note") {
    def * = (id, meetingId, text, attachmentLocation) <> (MeetingNoteRow.tupled, MeetingNoteRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(meetingId), Rep.Some(text), attachmentLocation)).shaped.<>({r=>import r._; _1.map(_=> MeetingNoteRow.tupled((_1.get, _2.get, _3.get, _4)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column meeting_id SqlType(INT) */
    val meetingId: Rep[Int] = column[Int]("meeting_id")
    /** Database column text SqlType(TEXT) */
    val text: Rep[String] = column[String]("text")
    /** Database column attachment_location SqlType(TEXT), Default(None) */
    val attachmentLocation: Rep[Option[String]] = column[Option[String]]("attachment_location", O.Default(None))

    /** Foreign key referencing Meeting (database name meeting_note_ibfk_1) */
    lazy val meetingFk = foreignKey("meeting_note_ibfk_1", meetingId, Meeting)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)
  }
  /** Collection-like TableQuery object for table MeetingNote */
  lazy val MeetingNote = new TableQuery(tag => new MeetingNote(tag))

  /** Entity class storing rows of table MeetingParticipant
   *  @param meetingId Database column meeting_id SqlType(INT)
   *  @param userId Database column user_id SqlType(INT) */
  case class MeetingParticipantRow(meetingId: Int, userId: Int)
  /** GetResult implicit for fetching MeetingParticipantRow objects using plain SQL queries */
  implicit def GetResultMeetingParticipantRow(implicit e0: GR[Int]): GR[MeetingParticipantRow] = GR{
    prs => import prs._
    MeetingParticipantRow.tupled((<<[Int], <<[Int]))
  }
  /** Table description of table meeting_participant. Objects of this class serve as prototypes for rows in queries. */
  class MeetingParticipant(_tableTag: Tag) extends profile.api.Table[MeetingParticipantRow](_tableTag, Some("mydatabase"), "meeting_participant") {
    def * = (meetingId, userId) <> (MeetingParticipantRow.tupled, MeetingParticipantRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(meetingId), Rep.Some(userId))).shaped.<>({r=>import r._; _1.map(_=> MeetingParticipantRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column meeting_id SqlType(INT) */
    val meetingId: Rep[Int] = column[Int]("meeting_id")
    /** Database column user_id SqlType(INT) */
    val userId: Rep[Int] = column[Int]("user_id")

    /** Primary key of MeetingParticipant (database name meeting_participant_PK) */
    val pk = primaryKey("meeting_participant_PK", (meetingId, userId))

    /** Foreign key referencing Meeting (database name meeting_participant_ibfk_1) */
    lazy val meetingFk = foreignKey("meeting_participant_ibfk_1", meetingId, Meeting)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)
    /** Foreign key referencing User (database name meeting_participant_ibfk_2) */
    lazy val userFk = foreignKey("meeting_participant_ibfk_2", userId, User)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)
  }
  /** Collection-like TableQuery object for table MeetingParticipant */
  lazy val MeetingParticipant = new TableQuery(tag => new MeetingParticipant(tag))

  /** Entity class storing rows of table User
   *  @param id Database column id SqlType(INT), AutoInc, PrimaryKey
   *  @param email Database column email SqlType(VARCHAR), Length(255,true)
   *  @param jwtToken Database column jwt_token SqlType(VARCHAR), Length(500,true), Default(None) */
  case class UserRow(id: Int, email: String, jwtToken: Option[String] = None)
  /** GetResult implicit for fetching UserRow objects using plain SQL queries */
  implicit def GetResultUserRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[String]]): GR[UserRow] = GR{
    prs => import prs._
    UserRow.tupled((<<[Int], <<[String], <<?[String]))
  }
  /** Table description of table user. Objects of this class serve as prototypes for rows in queries. */
  class User(_tableTag: Tag) extends profile.api.Table[UserRow](_tableTag, Some("mydatabase"), "user") {
    def * = (id, email, jwtToken) <> (UserRow.tupled, UserRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(email), jwtToken)).shaped.<>({r=>import r._; _1.map(_=> UserRow.tupled((_1.get, _2.get, _3)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column email SqlType(VARCHAR), Length(255,true) */
    val email: Rep[String] = column[String]("email", O.Length(255,varying=true))
    /** Database column jwt_token SqlType(VARCHAR), Length(500,true), Default(None) */
    val jwtToken: Rep[Option[String]] = column[Option[String]]("jwt_token", O.Length(500,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table User */
  lazy val User = new TableQuery(tag => new User(tag))
}
