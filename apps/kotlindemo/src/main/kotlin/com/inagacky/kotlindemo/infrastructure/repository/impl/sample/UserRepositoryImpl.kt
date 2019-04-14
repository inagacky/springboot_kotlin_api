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
    override fun save(user: User) {

        val insertedCnt: Int

        try {
            insertedCnt = sqlSessionTemplate.insert(
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

    /**
     * メールアドレスを元にユーザーを取得する　
     *
     * @param email メールアドレス
     *
     */
    @Throws(SampleSQLException::class)
    override fun findUserByEmail(email: String) : User? {

        val conditions = mapOf(
                "email" to email
        )
        return sqlSessionTemplate.selectOne(
                "UserRepository.findUserByEmail", conditions)
    }

    /**
     * ログインIDを元にユーザーを取得する　
     *
     * @param loginId ログインID
     *
     */
    @Throws(SampleSQLException::class)
    override fun findUserByLoginId(loginId: String) : User? {

        val conditions = mapOf(
                "loginId" to loginId
        )
        return sqlSessionTemplate.selectOne(
                "UserRepository.findUserByLoginId", conditions)
    }

    /**
     * IDを元にユーザー情報を取得する
     *
     * @param userId
     * @return User ユーザー情報
     */
    override fun findUserByUserId(userId: Int) : User? {

        val conditions = mapOf(
                "userId" to userId
        )
        return sqlSessionTemplate.selectOne(
                "UserRepository.findUserByUserId", conditions)
    }

    /**
     * ユーザー情報を更新する
     *
     * @param user
     *
     */
    @Throws(SampleSQLException::class)
    override fun update(user: User) {

        val updatedCnt: Int

        try {
            updatedCnt = sqlSessionTemplate.update(
                    "UserRepository.update", user)

        } catch (e: Exception) {
            log.error("An error occurred during update of user information", e)
            throw SampleSQLException("An error occurred during update of user information", e)
        }

        if (updatedCnt == 0) {
            log.error("The result of user information update is 0", user)
            throw SampleSQLException("The result of user information update is 0")
        }
    }
}
