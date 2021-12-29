package com.mateuslima.dicionarioclean.feature_dicionario.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mateuslima.dicionarioclean.feature_dicionario.domain.model.PalavraInfo
import com.mateuslima.dicionarioclean.feature_dicionario.domain.model.PalavraInfo.*

@Entity(tableName = "palavrainfo_table")
data class PalavraInfoEntity(
    val palavra: String,
    val significados: List<Significado>,
    val fonetica: String?,
    val origem: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
){
    fun toPalavraInfo() : PalavraInfo{
        return PalavraInfo(
            palavra = palavra,
            significados = significados,
            fonetica = fonetica,
            origem = origem
        )
    }
}