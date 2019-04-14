package com.inagacky.kotlindemo.domain.entity.sample

import com.fasterxml.jackson.annotation.JsonValue
import com.inagacky.kotlindemo.util.crypto.SampleAppCryptoEncoder

import java.util.Arrays

/**
 * User
 */
 class User : BaseSampleEntity() {

    var userId: Number? = null

    var firstName: String? = null

    var lastName: String? = null

    var status: Status? = null

    lateinit var email: String

    var loginId: String? = null

    lateinit var password: String

    enum class Status constructor(@get:JsonValue val id: Int) {
        TEMPORARY(1),
        VALID(5),
        UNSUBSCRIBE(9);

        companion object {
            fun fromId(id: Int): Status {
                return Arrays.stream(Status.values())
                        .filter { status -> status.id == id }
                        .findFirst().orElse(null)
            }
        }
    }

    /**
     * ユーザー作成時の初期データ　
     */
    fun setInitData() {
        this.password = SampleAppCryptoEncoder.encrypt(this.password)
        this.loginId = this.email // ログインIDはメールアドレスとする
        this.status = User.Status.TEMPORARY
    }
}
