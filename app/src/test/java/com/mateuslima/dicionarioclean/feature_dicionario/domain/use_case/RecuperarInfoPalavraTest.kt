package com.mateuslima.dicionarioclean.feature_dicionario.domain.use_case

import com.google.common.truth.Truth.assertThat
import com.mateuslima.dicionarioclean.core.util.Resource
import com.mateuslima.dicionarioclean.feature_dicionario.data.repository.FakePalavraInfoRepository
import com.mateuslima.dicionarioclean.feature_dicionario.data.repository.PalavraInfoRepositoryImpl
import com.mateuslima.dicionarioclean.feature_dicionario.domain.model.PalavraInfo
import com.mateuslima.dicionarioclean.feature_dicionario.domain.repository.PalavraInfoRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.junit.MockitoJUnitRunner



class RecuperarInfoPalavraTest {

    private lateinit var sut: RecuperarInfoPalavra
    private lateinit var fakePalavraInfoRepository: FakePalavraInfoRepository


    @Before
    fun setup() {
        fakePalavraInfoRepository = FakePalavraInfoRepository()
        sut = RecuperarInfoPalavra(fakePalavraInfoRepository)
    }


    @Test
    fun `campo pesquisa preenchido, return listFlow`() = runBlocking{
        val event = sut("algo").first()
        assertThat(event).isNotNull()

    }

    @Test
    fun `campo vazio, return emptyflow`() = runBlocking{
        val result = fakePalavraInfoRepository.getPalavraInfo("").first()
        assertThat(result.data).isEmpty()

    }


}