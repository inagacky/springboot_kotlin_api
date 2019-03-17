package com.inagacky.kotlindemo.util

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Logger
 */
object Logger {
    fun getLogger(o: Any):Logger = LoggerFactory.getLogger(o.javaClass.canonicalName)
}