package com.inagacky.kotlindemo.domain.service.impl

import com.inagacky.kotlindemo.domain.entity.sample.User
import com.inagacky.kotlindemo.domain.repository.sample.UserRepository
import com.inagacky.kotlindemo.domain.service.UserService
import com.inagacky.kotlindemo.exception.IllegalDataException
import com.inagacky.kotlindemo.exception.SampleSQLException
import com.inagacky.kotlindemo.exception.ValidationException
import com.inagacky.kotlindemo.util.Logger
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

/**
 * User API Service
 */
@Service
@Slf4j
class UserServiceImpl : BaseServiceImpl(), UserService {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    lateinit var messageSource: MessageSource

    private val log = Logger.getLogger(this)
    /**
     * 仮ユーザーの作成処理
     *
     * @param user
     */
    @Transactional(rollbackFor = [Exception::class])
    @Throws(SampleSQLException::class, ValidationException::class)
    override fun createTmpUser(user: User) {

        // アカウントの重複の登録を避けるため、メールアドレスでユーザーを検索する
        val findUser: User? = userRepository.findUserByEmail(user.email)
        if (findUser != null) {
            log.info("deplication Email Address: %s", user.email)
            val errorMessage = messageSource.getMessage("duplication.login_id.message", null, Locale.getDefault())
            val validationException = ValidationException(errorMessage)
            validationException.orgErrorMessage = errorMessage
            throw validationException
        }

        // データ登録前の初期処理
        user.setInitData()
        userRepository.save(user)
    }

    /**
     * ユーザーの更新処理
     *
     * @param paramUser
     *
     */
    @Transactional(rollbackFor = [Exception::class])
    @Throws(SampleSQLException::class, ValidationException::class)
    override fun updateUser(paramUser: User) : User {

        // ユーザーの取得
        val orgUser = this.findUser(paramUser.userId!!)

        // ユーザー情報をマージ
        orgUser.mergeUser(paramUser)
        // ステータスを「有効に更新」
        orgUser.status = User.Status.VALID
        // ユーザー情報を更新
        userRepository.update(orgUser)

        return orgUser
    }

    /**
     *
     * ユーザーの取得処理
     *
     * @param userId
     *
     * @return
     */
    @Throws(SampleSQLException::class, ValidationException::class)
    override fun findUser(userId: Int): User {

        // 既存のユーザー情報を取得する
        val user = userRepository.findUserByUserId(userId)
                ?: run {
                    log.info("unknown UserId : %s", userId)
                    val errorMessage = messageSource.getMessage("validation.error.message", null, Locale.getDefault())
                    val validationException = ValidationException(errorMessage)
                    validationException.orgErrorMessage = errorMessage
                    throw validationException
                }

        return user
    }
}
