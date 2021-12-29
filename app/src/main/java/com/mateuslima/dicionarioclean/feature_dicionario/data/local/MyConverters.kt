package com.mateuslima.dicionarioclean.feature_dicionario.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mateuslima.dicionarioclean.feature_dicionario.data.util.JsonParser
import com.mateuslima.dicionarioclean.feature_dicionario.domain.model.PalavraInfo
import com.mateuslima.dicionarioclean.feature_dicionario.domain.model.PalavraInfo.*
import javax.inject.Inject


class MyConverters{

    @TypeConverter
    fun significadoListToString(data: List<Significado>) : String{
        val type = object : TypeToken<List<Significado>>(){}.type
        return Gson().toJson(data, type)
    }

    @TypeConverter
    fun stringToSignificadoList(data: String) : List<Significado>{
        val type = object : TypeToken<List<Significado>>(){}.type
        return Gson().fromJson(data, type)
    }
}