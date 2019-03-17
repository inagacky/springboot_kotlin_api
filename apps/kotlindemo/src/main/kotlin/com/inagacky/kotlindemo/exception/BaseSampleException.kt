package com.inagacky.kotlindemo.exception

abstract class BaseSampleException : Exception {

    constructor(message: String) : super(message) {}

    constructor(cause: Throwable) : super(cause) {}

    constructor(message: String, cause: Throwable) : super(message, cause) {}
}
