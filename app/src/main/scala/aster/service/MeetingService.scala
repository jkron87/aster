package aster.service

import aster.model.dto.MeetingDto

trait MeetingService {
  def insert(meeting: MeetingDto): Int
  def findAll(): Seq[MeetingDto]
}
