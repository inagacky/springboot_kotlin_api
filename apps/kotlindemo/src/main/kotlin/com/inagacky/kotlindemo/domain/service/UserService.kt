package com.inagacky.kotlindemo.domain.service

import com.inagacky.kotlindemo.domain.entity.sample.User
import com.inagacky.kotlindemo.exception.IllegalDataException
import com.inagacky.kotlindemo.exception.SampleSQLException

/**
 * User API Service
 *
 */
interface UserService : BaseService {

    /**
     * @param user
     *
     * @return
     */
    @Throws(SampleSQLException::class, IllegalDataException::class)
    fun create(user: User)
}
