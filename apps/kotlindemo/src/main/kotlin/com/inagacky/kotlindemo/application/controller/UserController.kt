package com.inagacky.kotlindemo.application.controller

import com.inagacky.kotlindemo.application.http.request.user.UserRequest
import com.inagacky.kotlindemo.application.http.response.IApiResponseResult
import com.inagacky.kotlindemo.application.http.response.user.UserResponse
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
class UserController @Autowired constructor(private val userService: UserService) : AbstractApiController() {

    /**
     * ユーザー作成API
     * @param userRequest
     */
    @PostMapping(ApiRoutingConstants.VERSION_1_0 + ApiRoutingConstants.USERS_PATH)
    fun create(@RequestBody @Validated userRequest: UserRequest): IApiResponseResult {

        // リクエストモデルをエンティティに変換
        val user = EntityMapper.mappingToEntity(userRequest, User::class)

        userService.create(user)

        // エンティティをレスポンスモデルに変換　
        return ResponseResultMapper.mappingToResponseResult(user, UserResponse::class)
    }

}
