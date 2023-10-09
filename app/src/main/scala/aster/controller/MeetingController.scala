package aster.controller

import org.springframework.web.bind.annotation._

@RestController
@RequestMapping(Array("/meetings"))
class MeetingController {

  @PostMapping
  def scheduleMeeting(): String = {
    // Logic to schedule a new meeting.
    "Meeting scheduled successfully!"
  }

  @GetMapping
  def getMeetings(): String = {
    // Logic to retrieve list of scheduled meetings.
    "List of scheduled meetings."
  }
}
