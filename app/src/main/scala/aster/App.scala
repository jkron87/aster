package aster

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.{GetMapping, RestController}

@SpringBootApplication
class AppApplication

@RestController
class HelloController {
  @GetMapping(Array("/"))
  def hello(): String = "Meetings API is up and running!"
}

object App {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[AppApplication], args: _*)
  }
}
