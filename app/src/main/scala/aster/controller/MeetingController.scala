package aster.controller

import aster.model.dto.MeetingResponse
import aster.service.MeetingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation._

import java.util
import scala.jdk.CollectionConverters._
import scala.concurrent.ExecutionContext

@RestController
@RequestMapping(Array("/meetings"))
class MeetingController @Autowired()(meetingService: MeetingService)(implicit ec: ExecutionContext) {

  //  @PostMapping
  //  def scheduleMeeting(@RequestBody meeting: MeetingRow): Future[String] = {
  //    meetingService.insert(meeting).map { numRows =>
  //      if (numRows > 0) {
  //        "Meeting scheduled successfully!"
  //      } else {
  //        "Failed to schedule meeting."
  //      }
  //    }
  //  }
  @GetMapping
  @ResponseBody // Add this annotation
  def getMeetings(): util.List[MeetingResponse] = {
    meetingService.findAll().map(MeetingResponse.fromDTO).asJava
  }
}
