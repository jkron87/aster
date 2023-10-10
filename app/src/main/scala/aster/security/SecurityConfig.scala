package aster.security

import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.{EnableWebSecurity, WebSecurityConfigurerAdapter}
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {

  override def configure(http: HttpSecurity): Unit = {
    http.csrf().disable()
      .authorizeRequests()
      .antMatchers(HttpMethod.POST, "/meetings/login").permitAll()
      .anyRequest().authenticated()
      .and()
      .addFilterBefore(new JwtFilter(), classOf[UsernamePasswordAuthenticationFilter])
  }
}
