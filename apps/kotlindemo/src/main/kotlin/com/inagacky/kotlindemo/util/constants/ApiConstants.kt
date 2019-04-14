package com.inagacky.kotlindemo.util.constants

/**
 * 定数クラス
 */

object ApiConstants {

    const val SECRET = "N14ujecDAWol2eFl8sWB"  // TODO:本当は環境変数等で保持する
    const val EXPIRATION_TIME: Long = 28800000 // 8hours
    const val TOKEN_PREFIX = "Bearer "
    const val HEADER_STRING = "Authorization"
}
