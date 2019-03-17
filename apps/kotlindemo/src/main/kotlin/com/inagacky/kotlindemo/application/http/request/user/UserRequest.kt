package com.inagacky.kotlindemo.application.http.request.user

import com.inagacky.kotlindemo.application.http.request.AbstractApiRequest

import javax.validation.constraints.Email
import javax.validation.constraints.NotNull

class UserRequest : AbstractApiRequest() {

    @field:NotNull
    var firstName: String? = null

    @field:NotNull
    var lastName: String? = null

    @field:NotNull
    @field:Email
    var email: String? = null

    @field:NotNull
    var password: String? = null
}
