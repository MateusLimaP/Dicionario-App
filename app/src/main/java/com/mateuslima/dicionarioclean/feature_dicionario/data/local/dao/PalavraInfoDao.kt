package com.mateuslima.dicionarioclean.feature_dicionario.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mateuslima.dicionarioclean.feature_dicionario.data.local.entity.PalavraInfoEntity

@Dao
interface PalavraInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(palavrasInfo: List<PalavraInfoEntity>)

    @Query("DELETE FROM palavrainfo_table WHERE palavra IN(:palavras)")
    suspend fun deletar(palavras: List<String>)

    @Query("SELECT * FROM palavrainfo_table WHERE palavra LIKE '%' || :palavra || '%'")
    suspend fun getPalavrasInfo(palavra: String) : List<PalavraInfoEntity>
}