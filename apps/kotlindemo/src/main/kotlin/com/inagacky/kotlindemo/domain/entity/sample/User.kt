package com.inagacky.kotlindemo.domain.entity.sample

import com.fasterxml.jackson.annotation.JsonValue
import com.inagacky.kotlindemo.util.crypto.SampleAppCryptoEncoder

import javax.validation.constraints.NotNull
import java.util.Arrays
import javax.validation.constraints.Email

/**
 * User
 */
 class User : BaseSampleEntity() {

    var userId: Int? = null

    @field:NotNull
    var firstName: String? = null

    @field:NotNull
    var lastName: String? = null

    @field:NotNull
    var status: Status? = null

    @field:NotNull
    @field:Email
    var email: String? = null

    @field:NotNull
    var password: String? = null

    enum class Status constructor(@get:JsonValue val int: Int) {
        VALID(1),
        UNSUBSCRIBE(9);

        companion object {

            fun fromId(id: Int): Status {

                return Arrays.stream(Status.values())
                        .filter { status -> status.int == id }
                        .findFirst().orElse(null)
            }
        }
    }

    /**
     * ユーザー作成時の初期データ　
     */
    fun setInitData() {
        this.password = SampleAppCryptoEncoder.encrypt(this.password)
        this.status = User.Status.VALID
    }
}
