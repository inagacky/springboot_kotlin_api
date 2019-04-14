package com.inagacky.kotlindemo.application.http.adviser.error

import com.inagacky.kotlindemo.application.http.response.error.ErrorResponse
import com.inagacky.kotlindemo.exception.BaseSampleException
import com.inagacky.kotlindemo.exception.IllegalDataException
import com.inagacky.kotlindemo.exception.UnauthorizedException
import com.inagacky.kotlindemo.util.Logger
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

import java.util.Locale

/**
 *
 * Exceptionのハンドラ　
 *
 */
@ControllerAdvice
@Slf4j
class SampleExceptionAdvice {

    @Autowired
    lateinit var messageSource: MessageSource

    private val log = Logger.getLogger(this)

    /**
     * NotValidExceptionの捕捉
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    private fun handleNotValidException(exception: MethodArgumentNotValidException): ErrorResponse {

        log.info("NotValidException Error", exception)

        return createErrorResponse(exception)
    }

    /**
     * UnauthorizedExceptionの捕捉
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(UnauthorizedException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    private fun handleUnauthorizedException(exception: UnauthorizedException): ErrorResponse {

        log.info("UnauthorizedException Error", exception)

        return createErrorResponse(exception)
    }

    /**
     * IllegalDataExceptionの捕捉
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(IllegalDataException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    private fun handleIllegalDataException(exception: IllegalDataException): ErrorResponse {

        log.warn("IllegalDataException Error", exception)

        return createErrorResponse(exception)
    }

    /**
     * SampleExceptionの捕捉
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(BaseSampleException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    private fun handleSampleException(exception: BaseSampleException): ErrorResponse {

        log.warn("handleSampleException Error", exception)

        return createErrorResponse(exception)
    }

    /**
     * Exceptionの捕捉
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    private fun handleException(exception: Exception): ErrorResponse {

        log.error("Exception Error", exception)

        return createErrorResponse(exception)
    }

    /**
     * 例外パターンに対して、エラーコードの設定とメッセージの設定を行う。
     * @param exception
     * @return
     */
    private fun createErrorResponse(exception: Exception): ErrorResponse {

        val errorResponse = ErrorResponse()

        if (exception is BaseSampleException) {
            // BaseSampleExceptionに基づく例外
            errorResponse.errorMessage = exception.orgErrorMessage
                    ?: exception.getDefaultErrorMessage(messageSource, null)
            errorResponse.errorCode = exception.errorStatusCode
        } else {
            // 汎用例外
            errorResponse.errorMessage = messageSource.getMessage("sample.error.message", null, Locale.getDefault())
            errorResponse.errorCode = ErrorResponse.ErrorCode.SYSTEM_ERROR
        }

        return errorResponse
    }
}
