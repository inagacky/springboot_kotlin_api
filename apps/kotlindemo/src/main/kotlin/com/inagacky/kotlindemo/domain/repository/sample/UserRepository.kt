package com.inagacky.kotlindemo.domain.repository.sample

import com.inagacky.kotlindemo.domain.entity.sample.User

/**
 * User Repository
 *
 */
interface UserRepository : BaseSampleRepository {

    /**
     * ユーザー情報の作成を行う
     *
     * @param user
     */
    fun save(user: User)

    /**
     * メールアドレスを元にユーザー情報を取得する
     *
     * @param email
     * @return User ユーザー情報
     */
    fun findUserByEmail(email: String) : User?

    /**
     * ログインIDを元にユーザー情報を取得する
     *
     * @param loginId
     * @return User ユーザー情報
     */
    fun findUserByLoginId(loginId: String) : User?

    /**
     * IDを元にユーザー情報を取得する
     *
     * @param userId
     * @return User ユーザー情報
     */
    fun findUserByUserId(userId: Int) : User?


    /**
     * ユーザー情報を更新する
     *
     * @param user
     */
    fun update(user: User)
}
