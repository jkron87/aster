package aster.controller

import aster.model.dto.MeetingNoteDto
import aster.model.http.HttpMeetingNote
import aster.service.MeetingNoteService
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito._
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders._
import org.springframework.test.web.servlet.result.MockMvcResultMatchers._

@RunWith(classOf[SpringRunner])
@SpringBootTest
@AutoConfigureMockMvc
class MeetingNoteControllerTest {

  @Autowired
  var mockMvc: MockMvc = _

  @MockBean
  var meetingNoteService: MeetingNoteService = _

  @Test
  def testGetMeetingNotes(): Unit = {
    val notesList = Seq(MeetingNoteDto(1, 1, "Note text", Some("/path/to/attachment")))

    when(meetingNoteService.findByMeetingId(1)).thenReturn(notesList)

    mockMvc.perform(get("/meeting-notes/1").header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0QHRlc3QuY29tIn0.1RWrbR8soNgIA3oclRrNmdgDtunbITePFv6Bq3AqRDbHXGAbyA37p2oIFuK5obahMyiLBnGceUcMYXC7WI-Ijw"))
      .andExpect(status().isOk)
      .andExpect(content().json("""[{"id":1,"meetingId":1,"text":"Note text","attachmentUrl":"/path/to/attachment"}]"""))
  }

  @Test
  def testAddMeetingNotes(): Unit = {
    val note = HttpMeetingNote(1, 1, "Note text", "/path/to/attachment")

    when(meetingNoteService.insert(HttpMeetingNote.toDTO(note))).thenReturn(1)

    mockMvc.perform(post("/meeting-notes")
      .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0QHRlc3QuY29tIn0.1RWrbR8soNgIA3oclRrNmdgDtunbITePFv6Bq3AqRDbHXGAbyA37p2oIFuK5obahMyiLBnGceUcMYXC7WI-Ijw")
      .contentType(MediaType.APPLICATION_JSON)
      .content("""{"id":1,"meetingId":1,"text":"Note text","attachmentUrl":"/path/to/attachment"}"""))
      .andExpect(status().isOk)
      .andExpect(content().string("Notes added successfully!"))
  }

  @Test
  def testGetMeetingNotesWithoutToken(): Unit = {
    mockMvc.perform(get("/meeting-notes/1"))
      .andExpect(status().isForbidden)
  }

  @Test
  def testAddMeetingNotesWithoutToken(): Unit = {
    mockMvc.perform(post("/meeting-notes")
      .contentType(MediaType.APPLICATION_JSON)
      .content("""{"id":1,"meetingId":1,"text":"Note text","attachmentUrl":"/path/to/attachment"}"""))
      .andExpect(status().isForbidden)
  }

}
