package com.inagacky.kotlindemo.configure.datasource

import org.apache.ibatis.session.SqlSessionFactory
import org.mybatis.spring.SqlSessionFactoryBean
import org.mybatis.spring.SqlSessionTemplate
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.transaction.PlatformTransactionManager

import javax.sql.DataSource

@Configuration
class SampleDataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.sample")
    fun sampleProperties(): DataSourceProperties {

        return DataSourceProperties()
    }

    @Bean(name = arrayOf("sampleDataSource"))
    @Primary
    fun sampleDataSource(@Qualifier("sampleProperties") properties: DataSourceProperties): DataSource {

        return properties.initializeDataSourceBuilder().build()
    }

    @Bean(name = arrayOf("sampleTransactionManager"))
    @Primary
    fun sampleTransactionManager(dataSource1: DataSource): PlatformTransactionManager {
        return DataSourceTransactionManager(dataSource1)
    }

    @Bean(name = arrayOf("sampleSessionFactory"))
    @Primary
    @Throws(Exception::class)
    fun sqlSessionFactory(@Qualifier("sampleDataSource") primaryDataSource: DataSource): SqlSessionFactory? {
        val bean = SqlSessionFactoryBean()
        bean.setDataSource(primaryDataSource)
        bean.setMapperLocations(PathMatchingResourcePatternResolver().getResources(MAPPER_XML_PATH))
        bean.setConfigLocation(PathMatchingResourcePatternResolver().getResource(CONFIG_XML_PATH))

        return bean.getObject()
    }

    @Bean(name = arrayOf("sampleSessionTemplate"))
    fun sqlSessionTemplate(
            @Qualifier("sampleSessionFactory") sqlSessionFactory: SqlSessionFactory): SqlSessionTemplate {

        return SqlSessionTemplate(sqlSessionFactory)
    }

    companion object {
        val MAPPER_XML_PATH = "classpath:com/inagacky/api_sample_app/infrastructure/repository/sample/*.xml"
        val CONFIG_XML_PATH = "classpath:mybatis-config.xml"
    }
}
