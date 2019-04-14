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
     * ログインIDをもとにユーザー情報を取得する
     *
     * @param loginId
     * @return User ユーザー情報
     */
//    fun findUserByLoginId(loginId: String) : User?
}
