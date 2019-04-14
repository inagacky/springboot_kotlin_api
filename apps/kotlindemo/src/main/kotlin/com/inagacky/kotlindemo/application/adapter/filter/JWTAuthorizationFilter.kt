package com.inagacky.kotlindemo.application.adapter.filter

import com.inagacky.kotlindemo.exception.UnauthorizedException
import com.inagacky.kotlindemo.util.constants.ApiConstants.HEADER_STRING
import com.inagacky.kotlindemo.util.constants.ApiConstants.SECRET
import com.inagacky.kotlindemo.util.constants.ApiConstants.TOKEN_PREFIX
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import javax.servlet.ServletException
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpServletRequest
import java.util.ArrayList
import com.auth0.jwt.algorithms.Algorithm
import org.springframework.security.core.GrantedAuthority
import com.auth0.jwt.JWT

/**
 * 認可のフィルタ
 * ヘッダ情報からトークンを読み取る
 */
class JWTAuthorizationFilter(authenticationManager: AuthenticationManager) : BasicAuthenticationFilter(authenticationManager) {

    /**
     * 前処理
     */
    @Throws(IOException::class, ServletException::class, UnauthorizedException::class)
    override fun doFilterInternal(request: HttpServletRequest,
                                  response: HttpServletResponse,
                                  chain: FilterChain) {
        // ヘッダから認証情報の読み取り
        val header = request.getHeader(HEADER_STRING)

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(request, response)
            return
        }
        // トークン情報の検証
        val authentication = verifyToken(header)

        SecurityContextHolder.getContext().authentication = authentication
        chain.doFilter(request, response)
    }

    /**
     * トークン情報の検証を行う
     */
    private fun verifyToken(token: String): UsernamePasswordAuthenticationToken? {

        val token = token.replace(TOKEN_PREFIX, "")
        val algorithm = Algorithm.HMAC256(SECRET)
        val verifier = JWT.require(algorithm).build()
        val jwt = verifier.verify(token)

        val user = jwt.subject

        return UsernamePasswordAuthenticationToken(user, null, ArrayList<GrantedAuthority>())
    }
}