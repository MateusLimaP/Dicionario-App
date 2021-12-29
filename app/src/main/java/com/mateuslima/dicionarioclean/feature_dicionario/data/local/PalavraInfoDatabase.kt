package com.mateuslima.dicionarioclean.feature_dicionario.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mateuslima.dicionarioclean.feature_dicionario.data.local.dao.PalavraInfoDao
import com.mateuslima.dicionarioclean.feature_dicionario.data.local.entity.PalavraInfoEntity

@Database(entities = [PalavraInfoEntity::class], version = 2)
@TypeConverters(MyConverters::class)
abstract class PalavraInfoDatabase : RoomDatabase() {

    abstract fun palavraInfoDao(): PalavraInfoDao
}