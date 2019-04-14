package com.inagacky.kotlindemo.application.http.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
import com.inagacky.kotlindemo.application.http.response.error.ErrorResponse
import java.util.*

class ApiResponse {

    @JsonProperty("results")
    var result: IApiResponseResult? = null

    @JsonProperty("error")
    var errorResponse: ErrorResponse? = null

    @JsonProperty("status")
    var statusCode: StatusCode? = null

    enum class StatusCode constructor(@get:JsonValue val code: Int) {
        VALID(200),
        INVALID(500);

        companion object {
            fun fromCode(code: Int): StatusCode {
                return Arrays.stream(StatusCode.values())
                        .filter { status -> status.code == code }
                        .findFirst().orElse(null)
            }
        }
    }
}
