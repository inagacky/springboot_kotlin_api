package com.inagacky.kotlindemo.application.http.Interceptor

import com.inagacky.kotlindemo.configure.annotation.NonAuth
import com.inagacky.kotlindemo.util.Logger
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 *
 * 認可を行うインターセプタ
 *
 */
class AuthorizationInterceptor : HandlerInterceptor {

    private val log = Logger.getLogger(this)

    enum class ErrorCode constructor(val value: String) {
        INVALID_TOKEN("invalid_token")
    }

    /**
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {

        return authorize(request, response, handler)
    }

    /**
     * 認可の処理を実施する
     * @param request
     * @param response
     * @param handler
     *
     * @return
     */
    private fun authorize(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {

        if (handler is ResourceHttpRequestHandler) {
            return true
        }

        return if (preAuthorize(request, response, handler::class)) {
            true
        } else true

        // TODO: Please Authorize Actions

    }

    /**
     * 認可の事前処理　
     *
     * @param request
     * @param response
     * @param handler
     * @return
     */
    private fun preAuthorize(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {

//        val nonCustomerAuthAnnotation = AnnotationUtils.findAnnotation(handlerMethod.method, NonAuth::class.java)
        // 特定アノテーションが付いている場合は認証を実施しない
        val handlerMethod = handler as HandlerMethod
        if (!handlerMethod.hasMethodAnnotation(NonAuth::class.java)) {
            log.info(String.format("Exclude NonAuth Check URI[%s]", request.requestURI))
            return true
        }

        return false
    }

    /**
     * 認可されていない場合のエラー設定　
     * 　
     * @param response
     * @param responseErrorCode
     */
    private fun setErrorResponse(response: HttpServletResponse, responseErrorCode: ErrorCode) {
        response.status = HttpServletResponse.SC_UNAUTHORIZED

        // TODO: Please Error Action
    }
}
