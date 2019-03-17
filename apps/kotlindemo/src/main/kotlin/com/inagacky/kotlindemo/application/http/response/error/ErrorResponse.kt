package com.inagacky.kotlindemo.application.http.response.error

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializable
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.jsontype.TypeSerializer
import lombok.Data
import lombok.NoArgsConstructor

import java.io.IOException

@Data
@NoArgsConstructor
class ErrorResponse {

    @JsonProperty("error_code")
    var errorCode: ErrorCode? = null
        set(errorCode) {
            field = this.errorCode
        }
    @JsonProperty("error_message")
    var errorMessage: String? = null
        set(errorMessage) {
            field = this.errorMessage
        }
    @JsonProperty("details")
    var errorDetails: List<ErrorDetail>? = null
        set(errorDetails) {
            field = this.errorDetails
        }

    enum class ErrorCode private constructor(val int: Int) : JsonSerializable {
        UNKNOWN_ERROR(100);

        @Throws(IOException::class)
        override fun serialize(jsonGenerator: JsonGenerator, provider: SerializerProvider) {
            jsonGenerator.writeNumber(int)
        }

        @Throws(IOException::class)
        override fun serializeWithType(jsonGenerator: JsonGenerator, provider: SerializerProvider, typeSer: TypeSerializer) {
            jsonGenerator.writeNumber(int)
        }
    }
}
