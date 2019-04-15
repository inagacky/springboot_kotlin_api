package com.inagacky.kotlindemo.domain.service

import com.inagacky.kotlindemo.domain.entity.sample.User
import com.inagacky.kotlindemo.exception.IllegalDataException
import com.inagacky.kotlindemo.exception.SampleSQLException
import com.inagacky.kotlindemo.exception.ValidationException

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
    @Throws(SampleSQLException::class, ValidationException::class)
    fun createTmpUser(user: User)

    /**
     *
     * ユーザーの更新処理
     *
     * @param paramUser
     *
     * @return
     */
    @Throws(SampleSQLException::class, ValidationException::class)
    fun updateUser(paramUser: User): User

    /**
     *
     * ユーザーの取得処理
     *
     * @param userId
     *
     * @return
     */
    @Throws(SampleSQLException::class, IllegalDataException::class)
    fun findUser(userId: Int): User
}
