package com.inagacky.kotlindemo.application.adapter

import com.inagacky.kotlindemo.application.adapter.filter.JWTAuthenticationFilter
import com.inagacky.kotlindemo.application.adapter.filter.JWTAuthorizationFilter
import com.inagacky.kotlindemo.util.constants.ApiRoutingConstants.CREATE_TMP_CUSTOMER_URL
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.password.PasswordEncoder


@Configuration
@EnableWebSecurity
class SecurityConfigurer : WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var userAuthServiceImpl: UserDetailsService

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {

        http.cors()
                .and().authorizeRequests()
                .antMatchers(CREATE_TMP_CUSTOMER_URL).permitAll()
                .anyRequest().authenticated()
                .and().logout()
                .and().csrf().disable()
                .addFilter(JWTAuthenticationFilter(authenticationManager()))
                .addFilter(JWTAuthorizationFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    @Autowired
    @Throws(Exception::class)
    fun configureAuth(auth: AuthenticationManagerBuilder) {

        // 認証するユーザーサービス
        auth.userDetailsService<UserDetailsService>(userAuthServiceImpl)
                // 入力値をハッシュ化した値でパスワード認証
                .passwordEncoder(passwordEncoder())
    }

    /**
     * パスワードエンコーダの登録
     * @return
     */
    @Bean(name = ["passwordEncoder"])
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}
