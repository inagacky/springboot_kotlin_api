package com.inagacky.kotlindemo.domain.repository.sample

import com.inagacky.kotlindemo.domain.entity.sample.User

/**
 * User Repository
 *
 */
interface UserRepository : BaseSampleRepository {

    /**
     * ユーザー情報の作成
     *
     * @param user
     */
    fun save(user: User)
}
