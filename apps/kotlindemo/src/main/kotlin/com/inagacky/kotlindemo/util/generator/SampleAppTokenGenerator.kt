package com.inagacky.kotlindemo.util.generator

import java.util.UUID

/**
 * トークン作成
 *
 */
object SampleAppTokenGenerator {

    /**
     * トークン作成を行います。
     * @return
     */
    fun generateToken(): String {

        return UUID.randomUUID().toString()
    }
}
