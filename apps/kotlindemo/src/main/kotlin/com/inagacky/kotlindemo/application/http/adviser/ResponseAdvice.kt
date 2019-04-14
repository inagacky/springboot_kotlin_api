package com.inagacky.kotlindemo.application.http.adviser

import com.inagacky.kotlindemo.application.http.response.ApiResponse
import com.inagacky.kotlindemo.application.http.response.IApiResponseResult
import com.inagacky.kotlindemo.application.http.response.error.ErrorResponse
import lombok.extern.slf4j.Slf4j
import org.springframework.core.MethodParameter
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice

/**
 * レスポンスに対するアドバイザ　
 */
@ControllerAdvice
@Slf4j
class ResponseAdvice : ResponseBodyAdvice<Any> {

    override fun supports(returnType: MethodParameter, converterType: Class<out HttpMessageConverter<*>>): Boolean {
        return true
    }

    /**
     * レスポンスの形式を変換
     * 　
     * @param body
     * @param returnType
     * @param selectedContentType
     * @param selectedConverterType
     * @param request
     * @param response
     * @return
     */
    override fun beforeBodyWrite(body: Any?, returnType: MethodParameter, selectedContentType: MediaType,
                                 selectedConverterType: Class<out HttpMessageConverter<*>>, request: ServerHttpRequest,
                                 response: ServerHttpResponse): Any? {

        val apiResponse = ApiResponse()

        // レスポンスの形式によって、ステータスコードを設定
        if (body is IApiResponseResult) {
            apiResponse.statusCode = ApiResponse.StatusCode.VALID
            apiResponse.result = body
        } else if (body is ErrorResponse) {
            apiResponse.statusCode = ApiResponse.StatusCode.INVALID
            apiResponse.errorResponse = body
        } else {
            apiResponse.statusCode = ApiResponse.StatusCode.INVALID
        }

        return apiResponse
    }
}
