package aster.service

import aster.model.dto.Meeting

trait MeetingService {
  def insert(meeting: Meeting): Int
  def update(meeting: Meeting): Int
  def delete(id: Int): Int
  def findById(id: Int): Option[Meeting]
  def findAll(): Seq[Meeting]
}
