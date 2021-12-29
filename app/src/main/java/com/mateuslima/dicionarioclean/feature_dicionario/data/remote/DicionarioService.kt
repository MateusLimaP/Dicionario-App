package com.mateuslima.dicionarioclean.feature_dicionario.data.remote

import com.mateuslima.dicionarioclean.feature_dicionario.data.remote.dto.PalavraInfoDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DicionarioService {

    @GET("api/v2/entries/en/{word}")
    suspend fun getPalavraInfo(
        @Path("word") word: String
    ) : List<PalavraInfoDto>
}