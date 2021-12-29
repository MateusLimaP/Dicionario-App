package com.mateuslima.dicionarioclean.feature_dicionario.data.util

import java.lang.reflect.Type

interface JsonParser {

    fun <T> fromJson(json: String, type: Type) : T?

    fun <T> toJson(obj: T, type: Type) : String?
}