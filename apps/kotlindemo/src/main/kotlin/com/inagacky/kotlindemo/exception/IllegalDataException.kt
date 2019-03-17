package com.inagacky.kotlindemo.exception


/**
 * データ不整合が発生した　場合の例外
 */
class IllegalDataException : BaseSampleException {

    constructor(message: String) : super(message) {}

    constructor(cause: Throwable) : super(cause) {}

    constructor(message: String, cause: Throwable) : super(message, cause) {}
}
