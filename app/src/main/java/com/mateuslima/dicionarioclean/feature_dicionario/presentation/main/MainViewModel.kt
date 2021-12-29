package com.mateuslima.dicionarioclean.feature_dicionario.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mateuslima.dicionarioclean.core.util.Resource
import com.mateuslima.dicionarioclean.feature_dicionario.domain.model.PalavraInfo
import com.mateuslima.dicionarioclean.feature_dicionario.domain.use_case.RecuperarInfoPalavra
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val recuperarInfoPalavra: RecuperarInfoPalavra
) : ViewModel() {

    val search = MutableStateFlow("")
    val state = MutableStateFlow(PalavraInfoState())

    private val _UIEvent = Channel<UIEvent>()
    val eventFlow = _UIEvent.receiveAsFlow()

    val palavraList = search.flatMapLatest { pesquisa ->
        recuperarInfoPalavra(pesquisa)
            .onEach { result ->
                when (result){
                    is Resource.Sucesso ->{
                        state.value = state.value.copy(result.data ?: emptyList(), false)
                    }
                    is Resource.Loading ->{
                        state.value = state.value.copy(result.data ?: emptyList(), true)
                    }
                    is Resource.Error -> {
                        state.value = state.value.copy(result.data ?: emptyList(), false)
                        _UIEvent.send(UIEvent.showSnackBar(result.message ?: "Erro Desconhecido"))
                    }
                }
            }.map { it.data }
    }.asLiveData()




    data class PalavraInfoState(
        val palavraInfoItens: List<PalavraInfo> = emptyList(),
        val isLoading: Boolean = false
    )

    sealed class UIEvent{
        data class showSnackBar(val mensagem: String) : UIEvent()
    }
}