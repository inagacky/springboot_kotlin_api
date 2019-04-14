package com.inagacky.kotlindemo.application.http.request.authentication

import com.inagacky.kotlindemo.application.http.request.AbstractApiRequest

import javax.validation.constraints.NotNull

/**
 * 認証のリクエストクラス
 */
class AuthRequest : AbstractApiRequest() {

    @field:NotNull
    var loginId: String? = null

    @field:NotNull
    var password: String? = null
}
