package com.inagacky.kotlindemo.configure

import org.modelmapper.ModelMapper
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean
import org.springframework.web.filter.CommonsRequestLoggingFilter
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * アプリケーションの設定
 */
@Configuration
class SampleConfigurer : WebMvcConfigurer {

    /**
     * validatorの登録
     * @return
     */
    @Bean(name = arrayOf("validator"))
    fun localValidatorFactoryBean(): LocalValidatorFactoryBean {
        val localValidatorFactoryBean = LocalValidatorFactoryBean()
        localValidatorFactoryBean.setValidationMessageSource(messageSource())

        return localValidatorFactoryBean
    }

    /**
     * メッセージ定義ファイルの登録
     * @return
     */
    @Bean(name = arrayOf("messageSource"))
    fun messageSource(): MessageSource {
        val reloadableResourceBundleMessageSource = ReloadableResourceBundleMessageSource()
        reloadableResourceBundleMessageSource.setBasename("classpath:ValidationMessages")
        reloadableResourceBundleMessageSource.setDefaultEncoding("UTF-8")

        return reloadableResourceBundleMessageSource
    }

    /**
     * Loggerフィルタの登録
     * @return
     */
    @Bean(name = arrayOf("requestLoggingFilter"))
    fun requestLoggingFilter(): CommonsRequestLoggingFilter {

        val commonsRequestLoggingFilter = CommonsRequestLoggingFilter()
        commonsRequestLoggingFilter.setIncludeClientInfo(true)
        commonsRequestLoggingFilter.setIncludeQueryString(true)
        commonsRequestLoggingFilter.setIncludeHeaders(true)
        commonsRequestLoggingFilter.setIncludePayload(true)

        return commonsRequestLoggingFilter
    }

    /**
     * モデルマッパーの登録
     * @return
     */
    @Bean(name = arrayOf("modelMapper"))
    fun modelMapper(): ModelMapper {
        return ModelMapper()
    }
}
