package com.inagacky.kotlindemo.exception

import com.inagacky.kotlindemo.application.http.response.error.ErrorResponse

/**
 * バリデーションエラーでの例外　
 */
class ValidationException : BaseSampleException {

    override var errorMessageCode = "validation.error.message"
    override var errorStatusCode = ErrorResponse.ErrorCode.VALIDATION_ERROR

    constructor(message: String) : super(message)

    constructor(cause: Throwable) : super(cause)

    constructor(message: String, cause: Throwable) : super(message, cause)
}
