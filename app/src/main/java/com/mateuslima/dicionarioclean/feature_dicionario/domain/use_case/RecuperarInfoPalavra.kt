package com.mateuslima.dicionarioclean.feature_dicionario.domain.use_case

import com.mateuslima.dicionarioclean.core.util.Resource
import com.mateuslima.dicionarioclean.feature_dicionario.domain.model.PalavraInfo
import com.mateuslima.dicionarioclean.feature_dicionario.domain.repository.PalavraInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RecuperarInfoPalavra @Inject constructor(
    private val repository: PalavraInfoRepository
) {
    operator fun invoke(palavra: String) : Flow<Resource<List<PalavraInfo>>>{
        if (palavra.isBlank())
            return flow { }
        return repository.getPalavraInfo(palavra)
    }
}