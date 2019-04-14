package com.inagacky.kotlindemo.domain.service.impl

import com.inagacky.kotlindemo.domain.entity.sample.User
import com.inagacky.kotlindemo.domain.repository.sample.UserRepository
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User as SecurityUser
import org.springframework.stereotype.Service
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService


/**
 * User Auth Service
 */
@Service
@Slf4j
class UserAuthServiceImpl : UserDetailsService {

    @Autowired
    private lateinit var userRepository: UserRepository

    /**
     * ユーザーの認証を行う
     */
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(loginId: String): UserDetails {


        // ログインIDを元にレコードを取得
//        val user: User = userRepository.findUserByLoginId(loginId)
//                ?: throw UsernameNotFoundException(loginId)

        return SecurityUser.withUsername(loginId)
                .password("")
                .authorities("ROLE_USER")
                .build()
    }
}
