package com.inagacky.kotlindemo.application.http.request.user

import com.inagacky.kotlindemo.application.http.request.AbstractApiRequest

import javax.validation.constraints.Email

/**
 * ユーザー更新APIリクエスト
 */
class UpdateUserRequest : AbstractApiRequest() {

    var firstName: String? = null

    var lastName: String? = null

    @field:Email
    var email: String? = null

    var password: String? = null
}
