package com.inagacky.kotlindemo.application.http.adviser.error

import com.inagacky.kotlindemo.application.http.response.error.ErrorDetail
import com.inagacky.kotlindemo.application.http.response.error.ErrorResponse
import com.inagacky.kotlindemo.exception.BaseSampleException
import com.inagacky.kotlindemo.exception.IllegalDataException
import com.inagacky.kotlindemo.exception.UnauthorizedException
import com.inagacky.kotlindemo.util.Logger
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.http.HttpStatus
import org.springframework.validation.BindingResult
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

import java.util.ArrayList
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
     * @param exception
     * @return
     */
    @ExceptionHandler(BaseSampleException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    private fun handleSampleException(exception: BaseSampleException): ErrorResponse {

        log.warn("BaseSampleException Error", exception)

        return createErrorResponse(exception)
    }

    /**
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

        if (exception is MethodArgumentNotValidException) {

            val bindingResult = exception.bindingResult
            setErrorCode(errorResponse, exception)
            errorResponse.errorDetails = createValidateErrorDetailList(bindingResult)
        } else if (exception is UnauthorizedException) {

            setErrorCode(errorResponse, exception)
            errorResponse.errorMessage = messageSource.getMessage("login_authentication.error.message", null, Locale.getDefault())
        } else if (exception is IllegalDataException) {

            setErrorCode(errorResponse, exception)
            errorResponse.errorMessage = messageSource.getMessage("illegal_data.error.message", null, Locale.getDefault())
        } else if (exception is BaseSampleException) {

            setErrorCode(errorResponse, exception)
            errorResponse.errorMessage = messageSource.getMessage("sample.error.message", null, Locale.getDefault())
        } else {
            // 汎用エラー
            setErrorCode(errorResponse, exception)
            errorResponse.errorMessage = messageSource.getMessage("sample.error.message", null, Locale.getDefault())
        }

        return errorResponse
    }

    /**
     * エラーフィールドに対しての詳細設定
     *
     * @param bindingResult
     * @return
     */
    private fun createValidateErrorDetailList(bindingResult: BindingResult): List<ErrorDetail> {

        val errorDetailList = ArrayList<ErrorDetail>()

        for (fieldError in bindingResult.fieldErrors) {

            val errorDetail = ErrorDetail()

            val errorType = fieldError.code
            setValidateErrorDetailCode(errorDetail, errorType!!)
            errorDetail.errorField = fieldError.field

            val fieldName = messageSource.getMessage(fieldError.field, null, null, Locale.getDefault())
            if (fieldName == null) {
                errorDetail.errorMessage = fieldError.defaultMessage
            } else {
                errorDetail.errorMessage = messageSource.getMessage(errorType, arrayOf(fieldName), Locale.getDefault())
            }

            log.info("Error Field:[{}] Error Message:[{}]", errorDetail.errorField, errorDetail.errorMessage)
            errorDetailList.add(errorDetail)
        }

        return errorDetailList
    }

    /**
     * エラーコードの設定
     * @param errorResponse
     * @param exception
     */
    private fun setErrorCode(errorResponse: ErrorResponse, exception: Exception) {

        errorResponse.errorCode = ErrorResponse.ErrorCode.UNKNOWN_ERROR

        if (exception is MethodArgumentNotValidException) {
            // TODO: Please Set Error Code
        } else {
            errorResponse.errorCode = ErrorResponse.ErrorCode.UNKNOWN_ERROR
        }
    }

    /**
     * エラー詳細コードの設定　
     *
     * @param errorDetail
     * @param errorType
     */
    private fun setValidateErrorDetailCode(errorDetail: ErrorDetail, errorType: String) {

        when (errorType) {
            "Error1" -> errorDetail.errorDetailCode = ErrorDetail.ErrorDetailCode.ERROR_1
            "Error2" -> errorDetail.errorDetailCode = ErrorDetail.ErrorDetailCode.ERROR_2
            else -> errorDetail.errorDetailCode = null
        }
    }
}
