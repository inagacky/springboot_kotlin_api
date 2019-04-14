package com.inagacky.kotlindemo.exception

import com.inagacky.kotlindemo.application.http.response.error.ErrorResponse
import org.springframework.context.MessageSource
import java.util.*

abstract class BaseSampleException : Exception {

    open var errorMessageCode = "sample.error.message"
    open var errorStatusCode = ErrorResponse.ErrorCode.SYSTEM_ERROR

    var orgErrorMessage: String? = null

    constructor(message: String) : super(message) {}

    constructor(cause: Throwable) : super(cause) {}

    constructor(message: String, cause: Throwable) : super(message, cause) {}

    /**
     * デフォルトのエラーメッセージを取得する
     * @param messageSource メッセージソース
     * @param args メッセージの引数
     */
    fun getDefaultErrorMessage(messageSource: MessageSource, args :Array<Objects>?) : String {
        return messageSource.getMessage(errorMessageCode, args, Locale.getDefault())
    }
}
