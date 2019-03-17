package com.inagacky.kotlindemo.util.crypto

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

import javax.annotation.PostConstruct

/**
 * 暗号化処理
 *
 */
@Component
class SampleAppCryptoEncoder private constructor() {

    @Autowired
    private val passwordEncoder: PasswordEncoder? = null

    /**
     * PasswordEncoderのインスタンス初期化
     */
    @PostConstruct
    fun init() {
        SampleAppCryptoEncoder.staticPasswordEncoder = passwordEncoder
    }

    companion object {

        private var staticPasswordEncoder: PasswordEncoder? = null

        /**
         * BCryptによる暗号化を行います。
         * @param password パスワード
         * @return ハッシュ値
         */
        fun encrypt(password: String?): String {

            return staticPasswordEncoder!!.encode(password)
        }


        /**
         * BCryptの照合を行います。
         * @param password パスワード
         * @param digest ハッシュ値
         * @return
         */
        fun matches(password: String, digest: String): Boolean {

            return staticPasswordEncoder!!.matches(password, digest)
        }
    }
}
