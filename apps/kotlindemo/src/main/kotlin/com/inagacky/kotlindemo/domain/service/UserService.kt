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
     *
     * 仮ユーザーの作成処理
     * @param user
     *
     * @return
     */
    @Throws(SampleSQLException::class, IllegalDataException::class)
    fun createTmpUser(user: User)

    /**
     *
     * ユーザーの更新処理
     *
     * @param paramUser
     *
     * @return
     */
    @Throws(SampleSQLException::class, IllegalDataException::class)
    fun updateUser(paramUser: User): User
}
