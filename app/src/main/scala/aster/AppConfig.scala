package aster

import org.springframework.context.annotation.{Bean, ComponentScan, Configuration}
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import slick.jdbc.MySQLProfile.api._
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor

import scala.concurrent.ExecutionContext

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = Array("aster"))
class AppConfig {

  // Database configuration properties
  @Value("${spring.datasource.url}")
  private val dbUrl: String = "jdbc:mysql://db:3306/mydatabase?allowPublicKeyRetrieval=true&useSSL=false"

  @Value("${spring.datasource.username}")
  private val dbUser: String = "user"

  @Value("${spring.datasource.password}")
  private val dbPassword: String = "userpassword"

  @Bean
  def database(): Database = {
    Database.forURL(dbUrl, driver = "com.mysql.cj.jdbc.Driver", user = dbUser, password = dbPassword)
  }

  @Bean
  def executionContext: ExecutionContext = {
    val taskExecutor = new ThreadPoolTaskExecutor()
    taskExecutor.setCorePoolSize(10)
    taskExecutor.initialize()
    ExecutionContext.fromExecutor(taskExecutor)
  }

}
