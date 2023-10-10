package aster.service

import org.junit._
import org.junit.Assert._
import org.mockito._
import aster.dao.MeetingNoteDAO
import aster.model.dto.MeetingNoteDto
import aster.dao.generated.Tables._
import org.mockito.ArgumentMatchers.any

class MeetingNoteServiceImplTest {

  @Mock
  var mockMeetingNoteDAO: MeetingNoteDAO = _

  var service: MeetingNoteServiceImpl = _

  @Before
  def setup(): Unit = {
    MockitoAnnotations.initMocks(this)
    service = new MeetingNoteServiceImpl(mockMeetingNoteDAO)
  }

  @Test
  def testInsertNote(): Unit = {
    val noteDto = MeetingNoteDto(0, 1, "Test note", Some("/path/to/attachment"))
    Mockito.when(mockMeetingNoteDAO.insert(any[MeetingNoteRow])).thenReturn(1)

    val result = service.insert(noteDto)

    assertEquals(1, result)
  }

  @Test
  def testFindByMeetingId(): Unit = {
    val noteRow = MeetingNoteRow(1, 1, "Test note", Some("/path/to/attachment"))
    Mockito.when(mockMeetingNoteDAO.findByMeetingId(1)).thenReturn(Seq(noteRow))

    val notes = service.findByMeetingId(1)

    assertEquals(1, notes.size)
    assertEquals("Test note", notes.head.text)
    assertTrue(notes.head.attachmentUrl.isDefined)
    assertEquals("/path/to/attachment", notes.head.attachmentUrl.get)
  }

  @Test
  def testFindByMeetingId_MultipleNotes(): Unit = {
    val note1 = MeetingNoteRow(1, 1, "Note 1", Some("/path/to/attachment1"))
    val note2 = MeetingNoteRow(2, 1, "Note 2", Some("/path/to/attachment2"))
    Mockito.when(mockMeetingNoteDAO.findByMeetingId(1)).thenReturn(Seq(note1, note2))

    val notes = service.findByMeetingId(1)

    assertEquals(2, notes.size)

    // Verify first note
    assertEquals("Note 1", notes.head.text)
    assertTrue(notes.head.attachmentUrl.isDefined)
    assertEquals("/path/to/attachment1", notes.head.attachmentUrl.get)

    // Verify second note
    assertEquals("Note 2", notes(1).text)
    assertTrue(notes(1).attachmentUrl.isDefined)
    assertEquals("/path/to/attachment2", notes(1).attachmentUrl.get)
  }

}
