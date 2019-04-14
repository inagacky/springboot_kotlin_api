package com.inagacky.kotlindemo.application.controller

import com.inagacky.kotlindemo.application.http.request.user.CreateTmpUserRequest
import com.inagacky.kotlindemo.application.http.response.IApiResponseResult
import com.inagacky.kotlindemo.application.http.response.user.CreateTmpUserResponse
import com.inagacky.kotlindemo.application.mapper.EntityMapper
import com.inagacky.kotlindemo.application.mapper.ResponseResultMapper
import com.inagacky.kotlindemo.domain.entity.sample.User
import com.inagacky.kotlindemo.domain.service.UserService
import com.inagacky.kotlindemo.util.constants.ApiRoutingConstants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * User API Controller
 *
 */
@RestController
@RequestMapping(ApiRoutingConstants.API_BASE_PATH, produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
class UserController: AbstractApiController() {

    @Autowired
    private lateinit var userService: UserService

    /**
     * 仮顧客作成API
     * @param createTmpUserRequest
     */
    @PostMapping(ApiRoutingConstants.VERSION_1_0 + ApiRoutingConstants.USER_PATH + ApiRoutingConstants.TMP_PATH)
    fun createTmpUser(@RequestBody @Validated createTmpUserRequest: CreateTmpUserRequest): IApiResponseResult {

        // リクエストモデルをエンティティに変換
        val user = EntityMapper.mappingToEntity(createTmpUserRequest, User::class)

        userService.createTmpUser(user)

        // エンティティをレスポンスモデルに変換　
        return ResponseResultMapper.mappingToResponseResult(user, CreateTmpUserResponse::class)
    }

}
