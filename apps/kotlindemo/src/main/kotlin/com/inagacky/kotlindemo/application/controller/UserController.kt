package com.inagacky.kotlindemo.application.controller

import com.inagacky.kotlindemo.application.http.request.user.CreateTmpUserRequest
import com.inagacky.kotlindemo.application.http.request.user.UpdateUserRequest
import com.inagacky.kotlindemo.application.http.response.IApiResponseResult
import com.inagacky.kotlindemo.application.http.response.user.CreateTmpUserResponse
import com.inagacky.kotlindemo.application.http.response.user.UpdateUserResponse
import com.inagacky.kotlindemo.application.mapper.EntityMapper
import com.inagacky.kotlindemo.application.mapper.ResponseResultMapper
import com.inagacky.kotlindemo.domain.entity.sample.User
import com.inagacky.kotlindemo.domain.service.UserService
import com.inagacky.kotlindemo.util.constants.ApiRoutingConstants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

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
     * 仮ユーザー作成API
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

    /**
     * ユーザー更新API
     *
     * @param updateUserRequest
     */
    @PutMapping(ApiRoutingConstants.VERSION_1_0 + ApiRoutingConstants.USER_PATH + ApiRoutingConstants.PARAMENTER_USER_ID)
    fun updateUser(@RequestBody @Validated updateUserRequest: UpdateUserRequest, @PathVariable("userId") userId: Int) : IApiResponseResult {

        //TODO:本当は自身のレコードのみ修正可能等考慮すべき

        // リクエストモデルをエンティティに変換
        val user = EntityMapper.mappingToEntity(updateUserRequest, User::class)
        user.userId = userId

        val updatedUser = userService.updateUser(user)

        // エンティティをレスポンスモデルに変換　
        return ResponseResultMapper.mappingToResponseResult(updatedUser, UpdateUserResponse::class)
    }

}
