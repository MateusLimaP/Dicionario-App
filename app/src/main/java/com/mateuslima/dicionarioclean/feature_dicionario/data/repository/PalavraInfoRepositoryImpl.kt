package com.mateuslima.dicionarioclean.feature_dicionario.data.repository

import com.mateuslima.dicionarioclean.core.util.Resource
import com.mateuslima.dicionarioclean.feature_dicionario.data.local.dao.PalavraInfoDao
import com.mateuslima.dicionarioclean.feature_dicionario.data.remote.DicionarioService
import com.mateuslima.dicionarioclean.feature_dicionario.domain.model.PalavraInfo
import com.mateuslima.dicionarioclean.feature_dicionario.domain.repository.PalavraInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PalavraInfoRepositoryImpl @Inject constructor(
    private val service: DicionarioService,
    private val dao: PalavraInfoDao
) : PalavraInfoRepository {


    override fun getPalavraInfo(palavra: String): Flow<Resource<List<PalavraInfo>>> = flow {
        emit(Resource.Loading())
        val palavraInfoList = dao.getPalavrasInfo(palavra).map { it.toPalavraInfo() }
        emit(Resource.Loading(data = palavraInfoList))

        try {
            val remotePalavraInfoList = service.getPalavraInfo(palavra)
            dao.deletar(remotePalavraInfoList.map { it.word })
            dao.add(remotePalavraInfoList.map { it.toPalavraInfoEntity() })

        } catch (e: HttpException) {
            emit(Resource.Error("Opps Something Went Wrong!", palavraInfoList))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server, check your internet connection!", palavraInfoList))
        }

        val newPalavraInfoList = dao.getPalavrasInfo(palavra).map { it.toPalavraInfo() }
        emit(Resource.Sucesso(newPalavraInfoList))
    }



}