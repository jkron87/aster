package aster.model.http

import scala.beans.BeanProperty

case class HttpUser(
                        @BeanProperty id: Int,
                        @BeanProperty email: String
                      )


