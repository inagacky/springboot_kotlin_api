package com.inagacky.kotlindemo.domain.entity.sample

import com.inagacky.kotlindemo.domain.entity.BaseEntity
import lombok.Data
import lombok.NoArgsConstructor

import javax.validation.constraints.NotNull
import java.sql.Timestamp

@Data
@NoArgsConstructor
abstract class BaseSampleEntity : BaseEntity() {

    @field:NotNull
    var createdAt: Timestamp? = null

    @field:NotNull
    var updatedAt: Timestamp? = null
}
