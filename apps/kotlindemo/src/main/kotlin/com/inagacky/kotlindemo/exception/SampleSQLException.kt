package com.inagacky.kotlindemo.exception

/**
 * SQL処理での例外　
 */
class SampleSQLException : BaseSampleException {

    constructor(message: String) : super(message) {}

    constructor(cause: Throwable) : super(cause) {}

    constructor(message: String, cause: Throwable) : super(message, cause) {}
}
