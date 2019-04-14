package com.inagacky.kotlindemo.domain.entity.sample

import com.fasterxml.jackson.annotation.JsonValue
import com.inagacky.kotlindemo.util.crypto.SampleAppCryptoEncoder
import org.apache.commons.lang3.StringUtils

import java.util.Arrays

/**
 * User
 */
 class User : BaseSampleEntity() {

    var userId: Int? = null

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

    /**
     * 自身を引数のユーザー情報で更新する
     */
    fun mergeUser(user: User) {

        this.firstName = user.firstName
        this.lastName = user.lastName
        // emailは空でない場合のみ更新する
        if (!StringUtils.isEmpty(user.email)) {
            this.email = user.email
            this.loginId = user.email // ログインIDはメールアドレス
        }
        // passwordは空でない場合のみ更新する
        if (!StringUtils.isEmpty(user.password)) this.password = SampleAppCryptoEncoder.encrypt(user.password)
    }
}
