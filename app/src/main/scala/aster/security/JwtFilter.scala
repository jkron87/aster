package aster.security

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

import javax.servlet.FilterChain
import javax.servlet.http.{HttpServletRequest, HttpServletResponse}

class JwtFilter extends OncePerRequestFilter {

  override def doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain): Unit = {
    val authHeader = request.getHeader("Authorization")
    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      val jwt = authHeader.substring(7)
      if (JwtUtil.validateToken(jwt)) {
        val username = JwtUtil.getUsernameFromToken(jwt)
        val authentication = new UsernamePasswordAuthenticationToken(username, null, java.util.Collections.emptyList())
        SecurityContextHolder.getContext.setAuthentication(authentication)
      }
    }
    filterChain.doFilter(request, response)
  }
}
