package com.inagacky.kotlindemo.application.http.response.user

import com.inagacky.kotlindemo.application.http.response.BaseApiResponseResult
import com.inagacky.kotlindemo.domain.entity.sample.User
import javax.validation.constraints.Email

import javax.validation.constraints.NotNull

class CreateTmpUserResponse : BaseApiResponseResult() {

    var userId: Int? = null

    var email: String? = null

    var status: User.Status? = null
}
