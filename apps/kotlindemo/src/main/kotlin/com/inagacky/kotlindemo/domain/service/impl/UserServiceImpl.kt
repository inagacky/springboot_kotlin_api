package com.inagacky.kotlindemo.domain.service.impl

import com.inagacky.kotlindemo.domain.entity.sample.User
import com.inagacky.kotlindemo.domain.repository.sample.UserRepository
import com.inagacky.kotlindemo.domain.service.UserService
import com.inagacky.kotlindemo.exception.SampleSQLException
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * User API Service
 */
@Service
@Slf4j
class UserServiceImpl : BaseServiceImpl(), UserService {

    @Autowired
    private lateinit var userRepository: UserRepository

    /**
     * 仮ユーザーの作成処理
     *
     * @param user
     */
    @Transactional(rollbackFor = [Exception::class])
    @Throws(SampleSQLException::class)
    override fun createTmpUser(user: User) {

        user.setInitData()
        userRepository.save(user)
    }
}
