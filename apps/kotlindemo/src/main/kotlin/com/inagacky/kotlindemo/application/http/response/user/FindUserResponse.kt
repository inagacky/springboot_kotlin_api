package com.inagacky.kotlindemo.application.http.response.user

import com.inagacky.kotlindemo.application.http.response.BaseApiResponseResult
import com.inagacky.kotlindemo.domain.entity.sample.User

class FindUserResponse : BaseApiResponseResult() {

    var userId: Int? = null

    var firstName: String? = null

    var lastName: String? = null

    var email: String? = null

    var loginId: String? = null

    var status: User.Status? = null

    // TODO: サンプルのため、パスワードも返却する
    var password: String? = null
}
