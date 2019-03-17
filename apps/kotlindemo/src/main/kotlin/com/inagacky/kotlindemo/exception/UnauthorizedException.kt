package com.inagacky.kotlindemo.exception

/**
 * 認可失敗時の例外　
 */
class UnauthorizedException : BaseSampleException {

    constructor(message: String) : super(message) {}

    constructor(cause: Throwable) : super(cause) {}

    constructor(message: String, cause: Throwable) : super(message, cause) {}
}
