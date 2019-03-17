package com.inagacky.kotlindemo.infrastructure.repository.impl.sample

import com.inagacky.kotlindemo.domain.repository.sample.BaseSampleRepository
import com.inagacky.kotlindemo.infrastructure.repository.impl.BaseRepositoryImpl
import org.mybatis.spring.SqlSessionTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Repository

/**
 */
@Repository
class BaseSampleRepositoryImpl : BaseRepositoryImpl(), BaseSampleRepository {

    @Autowired
    @Qualifier("sampleSessionTemplate")
    protected val sqlSessionTemplate: SqlSessionTemplate? = null
}
