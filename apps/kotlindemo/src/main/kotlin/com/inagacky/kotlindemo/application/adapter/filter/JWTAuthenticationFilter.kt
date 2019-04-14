package com.inagacky.kotlindemo.application.adapter.filter

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.fasterxml.jackson.databind.ObjectMapper
import com.inagacky.kotlindemo.application.http.request.authentication.AuthRequest
import com.inagacky.kotlindemo.util.constants.ApiConstants.EXPIRATION_TIME
import com.inagacky.kotlindemo.util.constants.ApiConstants.HEADER_STRING
import com.inagacky.kotlindemo.util.constants.ApiConstants.SECRET
import com.inagacky.kotlindemo.util.constants.ApiConstants.TOKEN_PREFIX
import com.inagacky.kotlindemo.util.constants.ApiRoutingConstants.LOGIN_URL
import lombok.extern.slf4j.Slf4j
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.io.IOException
import java.util.ArrayList
import java.util.Date
import org.springframework.security.core.GrantedAuthority

@Slf4j
/**
 * 認証フィルタ
 */
class JWTAuthenticationFilter(authenticationManager: AuthenticationManager) : UsernamePasswordAuthenticationFilter() {

    init {
        this.authenticationManager = authenticationManager
        // ログイン用のpathを変更する
        setRequiresAuthenticationRequestMatcher(AntPathRequestMatcher(LOGIN_URL, "POST"))
        usernameParameter= "loginId"
        passwordParameter= "password"
    }

    /**
     * 認証処理
     */
    @Throws(AuthenticationException::class)
    override fun attemptAuthentication(req: HttpServletRequest,
                                       res: HttpServletResponse?): Authentication {
        try {
            // requestパラメータからユーザ情報を読み取る
            val authRequest = ObjectMapper().readValue(req.inputStream, AuthRequest::class.java)

            return authenticationManager.authenticate(
                    UsernamePasswordAuthenticationToken(
                            authRequest.loginId,
                            authRequest.password,
                            ArrayList<GrantedAuthority>())
            )

        } catch (e: IOException) {
            logger.error(e.message)
            throw RuntimeException(e)
        }
    }

    /**
     * 認証に成功した場合
     */
    @Throws(IOException::class, ServletException::class)
    override fun successfulAuthentication(req: HttpServletRequest,
                                          res: HttpServletResponse,
                                          chain: FilterChain?,
                                          auth: Authentication) {

        val algorithm = Algorithm.HMAC256(SECRET)
        val token = JWT.create()
                .withSubject((auth.principal as User).username)
                .withExpiresAt(Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(algorithm)

        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token)
    }
}