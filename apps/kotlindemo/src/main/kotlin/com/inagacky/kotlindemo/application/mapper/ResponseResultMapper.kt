package com.inagacky.kotlindemo.application.mapper

import com.inagacky.kotlindemo.application.http.response.IApiResponseResult
import com.inagacky.kotlindemo.domain.entity.BaseEntity
import kotlin.reflect.KClass

/**
 * ResponseResult Mapper
 */
class ResponseResultMapper private constructor() : BaseMapper() {

    companion object {

        /**
         *
         * @return
         */
        fun <gEntity : BaseEntity, gApiResponseResult : IApiResponseResult> mappingToResponseResult(entity: gEntity, apiResponseResultClass: KClass<gApiResponseResult>): gApiResponseResult {
            return BaseMapper.modelMapper.map(entity, apiResponseResultClass.java)
        }
    }

}
