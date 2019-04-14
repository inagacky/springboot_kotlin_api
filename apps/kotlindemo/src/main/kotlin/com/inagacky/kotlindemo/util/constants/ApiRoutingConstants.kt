package com.inagacky.kotlindemo.util.constants

/**
 * ルーティング定数クラス
 */

object ApiRoutingConstants {

    const val API_BASE_PATH = "/api"
    const val VERSION_1_0 = "/v1.0"
    const val USER_PATH = "/users"
    const val LOGIN_PATH = "/login"
    const val TMP_PATH = "/temporaries"
    const val PARAMENTER_USER_ID = "/{userId}"

    // 仮顧客作成APIのURL
    const val CREATE_TMP_CUSTOMER_URL = API_BASE_PATH + VERSION_1_0 + USER_PATH + TMP_PATH

    // ログインURL
    const val LOGIN_URL = API_BASE_PATH + VERSION_1_0 + LOGIN_PATH

}
