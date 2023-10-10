package aster.controller

import aster.model.dto.MeetingDto
import aster.model.http.{HttpMeeting, HttpUser}
import aster.security.JwtUtil
import aster.service.MeetingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{HttpStatus, ResponseEntity}
import org.springframework.web.bind.annotation._

import java.util
import scala.jdk.CollectionConverters._
import scala.concurrent.ExecutionContext

@RestController
@RequestMapping(Array("/meetings"))
class MeetingController @Autowired()(meetingService: MeetingService)(implicit ec: ExecutionContext) {

  @PostMapping
  def scheduleMeeting(@RequestBody meeting: HttpMeeting) = {
    meetingService.insert(MeetingDto.fromHTTP(meeting))
  }

  @GetMapping
  @ResponseBody
  def getMeetings(): util.List[HttpMeeting] = {
    meetingService.findAll().map(HttpMeeting.fromDTO).asJava
  }

  /**
   * TEMPORARY LOGIN METHOD
   * This is a makeshift authentication method for demonstration purposes only.
   * In a real-world scenario:
   * 1. You should NEVER hard-code credentials in the code.
   * 2. User passwords should be hashed and stored securely.
   * 3. Validate the user's credentials against a database
   */
  @PostMapping(Array("/login"))
  def login(@RequestBody user: HttpUser): ResponseEntity[String] = {
    if (user.email == "admin@test.com") {
      ResponseEntity.ok(JwtUtil.generateToken(user.email))
    } else {
      ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials")
    }
  }

}
