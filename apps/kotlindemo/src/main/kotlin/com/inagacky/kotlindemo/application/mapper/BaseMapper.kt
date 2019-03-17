package com.inagacky.kotlindemo.application.mapper

import org.modelmapper.ModelMapper

abstract class BaseMapper {
    companion object {

        val modelMapper: ModelMapper = ModelMapper()
    }
}
