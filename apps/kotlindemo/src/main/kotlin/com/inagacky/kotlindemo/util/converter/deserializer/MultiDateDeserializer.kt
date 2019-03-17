package com.inagacky.kotlindemo.util.converter.deserializer

import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer

import java.io.IOException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Arrays
import java.util.Date


/**
 * 　Date型のデシリアライザ
 */
class MultiDateDeserializer @JvmOverloads constructor(vc: Class<*>? = null) : StdDeserializer<Date>(vc) {

    /**
     * デシリアライズ処理　
     * 　
     * @param jsonParser
     * @param ctxt
     * @return
     * @throws IOException
     * @throws JsonProcessingException
     */
    @Throws(IOException::class, JsonProcessingException::class)
    override fun deserialize(jsonParser: JsonParser, ctxt: DeserializationContext): Date {

        val node = jsonParser.codec.readTree<JsonNode>(jsonParser)
        val date = node.textValue()

        for (DATE_FORMAT in DATE_FORMATS) {
            try {
                return SimpleDateFormat(DATE_FORMAT).parse(date)
            } catch (e: ParseException) {
            }

        }
        throw JsonParseException(jsonParser, "Unparseable date: \"" + date + "\". Supported formats: " + Arrays.toString(DATE_FORMATS))
    }

    companion object {

        private val serialVersionUID = 1L

        private val DATE_FORMATS = arrayOf("yyyy/MM/dd", "yyyyMMdd", "yyyy-MM-dd")
    }
}
