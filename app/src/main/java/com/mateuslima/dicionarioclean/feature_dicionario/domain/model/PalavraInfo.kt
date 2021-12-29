package com.mateuslima.dicionarioclean.feature_dicionario.domain.model

data class PalavraInfo(
    val palavra: String?,
    val significados: List<Significado>,
    val fonetica: String?,
    val origem: String?
){

    data class Significado(
        val listaDefinicao: List<Definicao>,
        val classeGramatical: String?
    )

    data class Definicao(
        val definicao: String?,
        val exemplo: String?,
        val sinonimos: List<String>
    )
}