package com.mateuslima.dicionarioclean.feature_dicionario.data.repository

import com.mateuslima.dicionarioclean.core.util.Resource
import com.mateuslima.dicionarioclean.feature_dicionario.domain.model.PalavraInfo
import com.mateuslima.dicionarioclean.feature_dicionario.domain.repository.PalavraInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

class FakePalavraInfoRepository : PalavraInfoRepository {

    override fun getPalavraInfo(palavra: String): Flow<Resource<List<PalavraInfo>>> = flow {
        val list = listOf(PalavraInfo(palavra = "teste", emptyList(), "fonetica", "desc"))
        if (palavra.isBlank())
            emit(Resource.Sucesso(emptyList()))
        emit(Resource.Loading())
        emit(Resource.Sucesso(list))
    }
}