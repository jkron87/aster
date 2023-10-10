package aster.controller

import aster.model.http.HttpMeetingNote
import aster.service.MeetingNoteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation._

import scala.jdk.CollectionConverters.IterableHasAsJava

@RestController
@RequestMapping(Array("/meeting-notes"))
class MeetingNoteController @Autowired()(meetingNoteService: MeetingNoteService) {

  @PostMapping
  def addMeetingNotes(@RequestBody note: HttpMeetingNote): Int = {
    meetingNoteService.insert(HttpMeetingNote.toDTO(note))
  }

  @GetMapping(Array("/{meetingId}"))
  def getMeetingNotes(@PathVariable meetingId: Int) = {
    meetingNoteService.findByMeetingId(meetingId).map(HttpMeetingNote.fromDTO).asJava
  }
}
