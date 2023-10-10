package aster.controller

import aster.model.dto.MeetingDto
import aster.model.http.HttpMeeting
import aster.service.MeetingService
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

import java.sql.Timestamp
import scala.jdk.CollectionConverters._

@RunWith(classOf[SpringRunner])
@SpringBootTest
@AutoConfigureMockMvc
class MeetingControllerTest {

  @Autowired
  var mockMvc: MockMvc = _

  @MockBean
  var meetingService: MeetingService = _

  @Test
  def testGetMeetings(): Unit = {
    val meetingsList = Seq(MeetingDto(1, Timestamp.valueOf("2023-10-09 15:30:45"), Seq(1), "Chicago"))

    when(meetingService.findAll()).thenReturn(meetingsList)

    mockMvc.perform(get("/meetings").header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0QHRlc3QuY29tIn0.1RWrbR8soNgIA3oclRrNmdgDtunbITePFv6Bq3AqRDbHXGAbyA37p2oIFuK5obahMyiLBnGceUcMYXC7WI-Ijw"))
      .andExpect(status().isOk)
      .andExpect(content().json("""[{"id":1,"time":"2023-10-09 15:30:45","participants":[1],"location":"Chicago"}]"""))
  }

  @Test
  def testScheduleMeeting(): Unit = {
    val meeting = HttpMeeting(1, "2023-10-09 15:30:45", Seq(1).asJava, "Chicago")

    when(meetingService.insert(MeetingDto.fromHTTP(meeting))).thenReturn(1)

    mockMvc.perform(post("/meetings")
      .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0QHRlc3QuY29tIn0.1RWrbR8soNgIA3oclRrNmdgDtunbITePFv6Bq3AqRDbHXGAbyA37p2oIFuK5obahMyiLBnGceUcMYXC7WI-Ijw")
      .contentType(MediaType.APPLICATION_JSON)
      .content("""{"id":1,"time":"2023-10-09 15:30:45","participants":[1],"location":"Chicago"}"""))
      .andExpect(status().isOk)
  }

  @Test
  def testGetMeetingsWithoutToken(): Unit = {
    mockMvc.perform(get("/meetings"))
      .andExpect(status().isForbidden)
  }

  @Test
  def testScheduleMeetingWithoutToken(): Unit = {
    mockMvc.perform(post("/meetings")
      .contentType(MediaType.APPLICATION_JSON)
      .content("""{"id":1,"time":"2023-10-09 15:30:45","participants":[1],"location":"Chicago"}"""))
      .andExpect(status().isForbidden)
  }


}
