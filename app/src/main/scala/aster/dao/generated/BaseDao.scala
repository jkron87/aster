package aster.dao.generated

trait BaseDao[T] {
  def insert(row: T): DBIO[Int]
  def update(row: T): DBIO[Int]
  def delete(id: Long): DBIO[Int]
  def findById(id: Long): DBIO[Option[T]]
  def findAll(): DBIO[Seq[T]]
}
