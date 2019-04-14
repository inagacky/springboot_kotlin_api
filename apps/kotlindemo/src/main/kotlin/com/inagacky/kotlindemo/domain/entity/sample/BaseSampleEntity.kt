package com.inagacky.kotlindemo.domain.entity.sample

import com.inagacky.kotlindemo.domain.entity.BaseEntity

import javax.validation.constraints.NotNull
import java.sql.Timestamp

abstract class BaseSampleEntity : BaseEntity() {

    @field:NotNull
    var createdAt: Timestamp? = null

    @field:NotNull
    var updatedAt: Timestamp? = null
}
