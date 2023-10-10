package aster.service

import org.junit._
import org.junit.Assert._
import org.mockito._
import aster.dao.{MeetingDAO, MeetingParticipantDAO}
import aster.model.dto.MeetingDto
import aster.dao.generated.Tables._
import org.mockito.ArgumentMatchers.any

class MeetingServiceImplTest {

  @Mock
  var mockMeetingDAO: MeetingDAO = _

  @Mock
  var mockParticipantDAO: MeetingParticipantDAO = _

  var service: MeetingServiceImpl = _

  @Before
  def setup(): Unit = {
    MockitoAnnotations.initMocks(this)
    service = new MeetingServiceImpl(mockMeetingDAO, mockParticipantDAO)
  }

  @Test
  def testInsertMeeting(): Unit = {
    val meetingDto = MeetingDto(0, new java.sql.Timestamp(System.currentTimeMillis()), Seq(1, 2, 3), "Location")
    Mockito.when(mockMeetingDAO.insertWithParticipants(any[MeetingRow], any[Seq[Int]])).thenReturn(1)

    val result = service.insert(meetingDto)

    assertEquals(1, result)
  }

  @Test
  def testFindAllMeetings(): Unit = {
    val meetingRow = MeetingRow(1, new java.sql.Timestamp(System.currentTimeMillis()), "Location")
    Mockito.when(mockMeetingDAO.findAll()).thenReturn(Seq(meetingRow))
    Mockito.when(mockParticipantDAO.findAllForMeeting(1)).thenReturn(Seq(MeetingParticipantRow(1, 1)))

    val meetings = service.findAll()

    assertEquals(1, meetings.size)
    assertTrue(meetings.head.participants.contains(1))
  }
}
