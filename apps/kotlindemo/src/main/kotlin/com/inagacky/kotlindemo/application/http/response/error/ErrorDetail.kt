package com.inagacky.kotlindemo.application.http.response.error

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializable
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.jsontype.TypeSerializer
import lombok.Data
import lombok.NoArgsConstructor

import java.io.IOException

@Data
@NoArgsConstructor
class ErrorDetail {

    var errorField: String? = null
    var errorDetailCode: ErrorDetailCode? = null
    var errorMessage: String? = null
    
    enum class ErrorDetailCode private constructor(val int: Int) : JsonSerializable {
        ERROR_1(201),
        ERROR_2(202);

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
