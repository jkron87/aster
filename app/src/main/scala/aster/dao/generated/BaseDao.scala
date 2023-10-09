package aster.dao.generated

import slick.dbio.DBIO

trait BaseDao[T] {
  def insert(row: T): DBIO[Int]
  def update(row: T): DBIO[Int]
  def delete(id: Int): DBIO[Int]
  def findById(id: Int): DBIO[Option[T]]
  def findAll(): DBIO[Seq[T]]
}
