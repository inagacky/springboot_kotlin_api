package com.inagacky.kotlindemo.exception

/**
 * 認可失敗時の例外　
 */
class UnauthorizedException : BaseSampleException {

    override var errorMessageCode = "login_authentication.error.message"

    constructor(message: String) : super(message) {}

    constructor(cause: Throwable) : super(cause) {}

    constructor(message: String, cause: Throwable) : super(message, cause) {}
}
