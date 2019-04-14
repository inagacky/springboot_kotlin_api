package com.inagacky.kotlindemo.application.http.response.user

import com.inagacky.kotlindemo.application.http.response.BaseApiResponseResult
import com.inagacky.kotlindemo.domain.entity.sample.User

class CreateTmpUserResponse : BaseApiResponseResult() {

    var userId: Int? = null

    var email: String? = null

    var status: User.Status? = null
}
