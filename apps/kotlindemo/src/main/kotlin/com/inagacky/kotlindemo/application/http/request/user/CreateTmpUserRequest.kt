package com.inagacky.kotlindemo.application.http.request.user

import com.inagacky.kotlindemo.application.http.request.AbstractApiRequest

import javax.validation.constraints.Email
import javax.validation.constraints.NotNull

/**
 * 仮ユーザー作成APIリクエスト
 */
class CreateTmpUserRequest : AbstractApiRequest() {

    @field:NotNull
    @field:Email
    var email: String? = null

    @field:NotNull
    var password: String? = null
}
