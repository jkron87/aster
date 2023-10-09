package aster.controller

import aster.model.dto.Meeting
import aster.model.http.HttpMeeting
import aster.service.MeetingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation._

import java.util
import scala.jdk.CollectionConverters._
import scala.concurrent.ExecutionContext

@RestController
@RequestMapping(Array("/meetings"))
class MeetingController @Autowired()(meetingService: MeetingService)(implicit ec: ExecutionContext) {

  @PostMapping
  def scheduleMeeting(@RequestBody meeting: HttpMeeting) = {
    meetingService.insert(Meeting.fromHTTP(meeting))
  }

  @GetMapping
  @ResponseBody
  def getMeetings(): util.List[HttpMeeting] = {
    meetingService.findAll().map(HttpMeeting.fromDTO).asJava
  }
}
