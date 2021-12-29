package com.mateuslima.dicionarioclean.feature_dicionario.domain.repository

import com.mateuslima.dicionarioclean.core.util.Resource
import com.mateuslima.dicionarioclean.feature_dicionario.domain.model.PalavraInfo
import kotlinx.coroutines.flow.Flow

interface PalavraInfoRepository {

    fun getPalavraInfo(palavra: String) : Flow<Resource<List<PalavraInfo>>>
}