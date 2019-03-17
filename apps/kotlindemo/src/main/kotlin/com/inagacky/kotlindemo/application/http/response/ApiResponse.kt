package com.inagacky.kotlindemo.application.http.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.inagacky.kotlindemo.application.http.response.error.ErrorResponse

class ApiResponse {

    @JsonProperty("results")
    var result: IApiResponseResult? = null

    @JsonProperty("error")
    var errorResponse: ErrorResponse? = null

    @JsonProperty("status")
    var statusCode: StatusCode? = null

    enum class StatusCode (val code: Int) {
        VALID(200),
        INVALID(500);
    }
}
