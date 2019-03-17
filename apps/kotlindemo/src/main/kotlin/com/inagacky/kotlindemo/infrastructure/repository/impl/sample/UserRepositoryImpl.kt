package com.inagacky.kotlindemo.infrastructure.repository.impl.sample

import com.inagacky.kotlindemo.domain.entity.sample.User
import com.inagacky.kotlindemo.domain.repository.sample.UserRepository
import com.inagacky.kotlindemo.exception.SampleSQLException
import com.inagacky.kotlindemo.util.Logger
import lombok.NonNull
import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Repository

@Repository
@Slf4j
class UserRepositoryImpl : BaseSampleRepositoryImpl(), UserRepository {

    private val log = Logger.getLogger(this)

    /**
     * ユーザー情報の保存処理　
     *
     * @param user
     */
    @Throws(SampleSQLException::class)
    override fun save(@NonNull user: User) {

        val insertedCnt: Int

        try {
            insertedCnt = sqlSessionTemplate!!.insert(
                    "UserRepository.save", user)

        } catch (e: Exception) {
            log.error("An error occurred during registration of user information", e)
            throw SampleSQLException("An error occurred during registration of user information", e)
        }

        if (insertedCnt == 0) {
            log.error("The result of user information registration is 0", user)
            throw SampleSQLException("The result of user information registration is 0")
        }
    }
}
