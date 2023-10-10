package aster.integration

import aster.model.http.{HttpMeeting, HttpMeetingNote}
import org.junit._
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders._
import org.springframework.test.web.servlet.result.MockMvcResultMatchers._
import org.springframework.http.MediaType
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.runner.RunWith

import scala.jdk.CollectionConverters._

@RunWith(classOf[SpringRunner])
@SpringBootTest
@AutoConfigureMockMvc
class UserFlowIntegrationTest {

  @Autowired
  private val mockMvc: MockMvc = null

  @Autowired
  private val objectMapper: ObjectMapper = null

  private val jwtToken: String = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0QHRlc3QuY29tIn0.1RWrbR8soNgIA3oclRrNmdgDtunbITePFv6Bq3AqRDbHXGAbyA37p2oIFuK5obahMyiLBnGceUcMYXC7WI-Ijw"

  @Test
  def testUserFlow(): Unit = {

    val headers = s"Bearer $jwtToken"

    // 1. Create a meeting
    val meeting = HttpMeeting(0, "2023-10-09 15:30:45", Seq().asJava, "Chicago")
    val meetingResponse = mockMvc.perform(post("/meetings")
      .contentType(MediaType.APPLICATION_JSON)
      .header("Authorization", headers)
      .content(objectMapper.writeValueAsString(meeting)))
      .andExpect(status().isOk)
      .andReturn()

    val createdMeetingId = objectMapper.readValue(meetingResponse.getResponse.getContentAsString, classOf[Int])

    // 2. Create a meeting note for the meeting
    val meetingNote = HttpMeetingNote(0, createdMeetingId, "test meeting note", "/somelocation/file.txt")
    mockMvc.perform(post("/meeting-notes")
      .contentType(MediaType.APPLICATION_JSON)
      .header("Authorization", headers)
      .content(objectMapper.writeValueAsString(meetingNote)))
      .andExpect(status().isOk)

    // 3. Get all the meeting notes for the meeting
    mockMvc.perform(get(s"/meeting-notes/$createdMeetingId")
      .header("Authorization", headers))
      .andExpect(status().isOk)
      .andExpect(jsonPath("$[0].text").value("test meeting note"))
  }
}
