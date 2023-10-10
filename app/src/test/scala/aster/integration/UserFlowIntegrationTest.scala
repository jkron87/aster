package aster.integration

import aster.model.http.{HttpMeeting, HttpMeetingNote}
import org.junit._
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.{HttpEntity, HttpHeaders, HttpMethod, MediaType}
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import scala.jdk.CollectionConverters._

@RunWith(classOf[SpringRunner])
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UserFlowIntegrationTest {

  @LocalServerPort
  private val port: Int = 0

  @Autowired
  private val restTemplate: TestRestTemplate = null

  @Test
  def testUserFlow(): Unit = {
    val baseUrl = s"http://localhost:$port"

    val headers = new HttpHeaders()
    headers.setContentType(MediaType.APPLICATION_JSON)
    headers.set("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0QHRlc3QuY29tIn0.1RWrbR8soNgIA3oclRrNmdgDtunbITePFv6Bq3AqRDbHXGAbyA37p2oIFuK5obahMyiLBnGceUcMYXC7WI-Ijw")

    // 1. Create a meeting
    val meeting = HttpMeeting(0, "2023-10-09 15:30:45", Seq().asJava, "Chicago")
    val meetingRequestEntity = new HttpEntity(meeting, headers)
    val meetingResponse = restTemplate.postForEntity(s"$baseUrl/meetings", meetingRequestEntity, classOf[Int])
    Assert.assertNotNull(meetingResponse.getBody)
    val createdMeetingId = meetingResponse.getBody

    // 2. Create a meeting note for the meeting
    val meetingNote = HttpMeetingNote(0, createdMeetingId, "test meeting note", "/somelocation/file.txt")
    val meetingNoteRequestEntity = new HttpEntity(meetingNote, headers)
    val meetingNoteResponse = restTemplate.postForEntity(s"$baseUrl/meeting-notes", meetingNoteRequestEntity, classOf[Int])
    Assert.assertNotNull(meetingNoteResponse.getBody)

    // 3. Get all the meeting notes for the meeting
    val getMeetingNotesEntity = new HttpEntity[Object](null, headers)// No body needed for GET, just headers
    val meetingNotesResponse = restTemplate.exchange(s"$baseUrl/meeting-notes/${createdMeetingId}", HttpMethod.GET, getMeetingNotesEntity, classOf[Array[HttpMeetingNote]])
    Assert.assertTrue(meetingNotesResponse.getBody.nonEmpty)
  }}
