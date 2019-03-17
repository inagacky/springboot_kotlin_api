package com.inagacky.kotlindemo.application.http.response.user

import com.inagacky.kotlindemo.application.http.response.BaseApiResponseResult
import com.inagacky.kotlindemo.domain.entity.sample.User
import javax.validation.constraints.Email

import javax.validation.constraints.NotNull

class UserResponse : BaseApiResponseResult() {

    @field:NotNull()
    var userId: Int? = null

    @field:NotNull()
    var firstName: String? = null

    @field:NotNull()
    var lastName: String? = null

    @field:NotNull()
    @field:Email()
    var email: String? = null

    var status: User.Status? = null
}
