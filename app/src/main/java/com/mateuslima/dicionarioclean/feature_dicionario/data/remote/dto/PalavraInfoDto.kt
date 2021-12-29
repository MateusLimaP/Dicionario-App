package com.mateuslima.dicionarioclean.feature_dicionario.data.remote.dto

import com.mateuslima.dicionarioclean.feature_dicionario.data.local.entity.PalavraInfoEntity
import com.mateuslima.dicionarioclean.feature_dicionario.domain.model.PalavraInfo
import com.mateuslima.dicionarioclean.feature_dicionario.domain.model.PalavraInfo.*

// objeto de transferencia de dados

data class PalavraInfoDto(
    val meanings: List<MeaningDto>,
    val origin: String,
    val phonetic: String?,
    val phonetics: List<PhoneticDto>,
    val word: String
) {
    fun toPalavraInfoEntity() : PalavraInfoEntity{
        return PalavraInfoEntity(
            palavra = word,
            significados = meanings.map { it.toSignificado() },
            fonetica = phonetic,
            origem = origin
        )
    }

    data class MeaningDto(
        val definitions: List<DefinitionDto>,
        val partOfSpeech: String?
    ){
        fun toSignificado() : Significado {
            return Significado(
                listaDefinicao = definitions.map { it.toDefinicao() },
                classeGramatical = partOfSpeech
            )
        }
    }

    data class DefinitionDto(
        val antonyms: List<String>,
        val definition: String?,
        val example: String?,
        val synonyms: List<String>
    ){
        fun toDefinicao() : Definicao{
            return Definicao(
                definicao = definition,
                exemplo = example,
                sinonimos = synonyms
            )
        }
    }

    data class PhoneticDto(
        val audio: String,
        val text: String
    )

}