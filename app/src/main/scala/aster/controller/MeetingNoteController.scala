package aster.controller

import org.springframework.web.bind.annotation._

@RestController
@RequestMapping(Array("/meeting-notes"))
class MeetingNotesController {

  @PostMapping
  def addMeetingNotes(): String = {
    // Logic to add notes associated with a specific meeting.
    "Notes added successfully!"
  }

  @GetMapping
  def getMeetingNotes(): String = {
    // Logic to retrieve notes based on a meeting ID.
    "Meeting notes based on ID."
  }
}
