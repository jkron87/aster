package aster.security

import io.jsonwebtoken.{Jwts, SignatureAlgorithm}

object JwtUtil {
  val SECRET_KEY = "SuperSecretKeyForDemo" // Never expose this in real apps!

  def generateToken(username: String): String = {
    Jwts.builder()
      .setSubject(username)
      .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
      .compact()
  }

  def validateToken(token: String): Boolean = {
    val user = Jwts.parser()
      .setSigningKey(SECRET_KEY)
      .parseClaimsJws(token)
      .getBody
      .getSubject
    user != null
  }

  def getUsernameFromToken(token: String): String = {
    Jwts.parser()
      .setSigningKey(SECRET_KEY)
      .parseClaimsJws(token)
      .getBody
      .getSubject
  }
}
