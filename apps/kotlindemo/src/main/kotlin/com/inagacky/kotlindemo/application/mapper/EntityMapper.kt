package com.inagacky.kotlindemo.application.mapper

import com.inagacky.kotlindemo.domain.entity.BaseEntity
import com.inagacky.kotlindemo.application.http.request.AbstractApiRequest
import kotlin.reflect.KClass

/**
 * Entity Mapper
 */
class EntityMapper private constructor() : BaseMapper() {
    companion object {

        /**
         *
         * @param fromApiRequestModel
         * @param entityClass
         * @param <gApiRequestModel>
         * @param <gEntity>
         * @return </gEntity></gApiRequestmodel>
         * */
        fun <gApiRequestModel : AbstractApiRequest, gEntity : BaseEntity> mappingToEntity(fromApiRequestModel: gApiRequestModel, entityClass: KClass<gEntity>): gEntity {

            return BaseMapper.modelMapper.map(fromApiRequestModel, entityClass.java)
        }
    }
}
