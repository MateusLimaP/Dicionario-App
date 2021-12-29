package com.mateuslima.dicionarioclean.feature_dicionario.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.mateuslima.dicionarioclean.core.util.BASE_URL_DICIONARIO
import com.mateuslima.dicionarioclean.feature_dicionario.data.local.MyConverters
import com.mateuslima.dicionarioclean.feature_dicionario.data.local.PalavraInfoDatabase
import com.mateuslima.dicionarioclean.feature_dicionario.data.local.dao.PalavraInfoDao
import com.mateuslima.dicionarioclean.feature_dicionario.data.remote.DicionarioService
import com.mateuslima.dicionarioclean.feature_dicionario.data.repository.PalavraInfoRepositoryImpl
import com.mateuslima.dicionarioclean.feature_dicionario.data.util.GsonParser
import com.mateuslima.dicionarioclean.feature_dicionario.data.util.JsonParser
import com.mateuslima.dicionarioclean.feature_dicionario.domain.repository.PalavraInfoRepository
import com.mateuslima.dicionarioclean.feature_dicionario.domain.use_case.RecuperarInfoPalavra
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DicionarioModule {

    @Singleton
    @Provides
    fun providePalavraInfoDatabase(application: Application) : PalavraInfoDatabase{
        return Room.databaseBuilder(application, PalavraInfoDatabase::class.java, "palavra_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providePalavraInfoDao(database: PalavraInfoDatabase) : PalavraInfoDao{
        return database.palavraInfoDao()
    }

    @Singleton
    @Provides
    fun provideRetrofitDicionario() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL_DICIONARIO)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideDicionarioService(retrofit: Retrofit) : DicionarioService{
        return retrofit.create(DicionarioService::class.java)
    }

    @Singleton
    @Provides
    fun provideInfoPalavraUseCase(repository: PalavraInfoRepository) : RecuperarInfoPalavra{
        return RecuperarInfoPalavra(repository)
    }

    @Singleton
    @Provides
    fun providePalavaInfoRepository(
        service: DicionarioService, dao: PalavraInfoDao) : PalavraInfoRepository{
        return PalavraInfoRepositoryImpl(service, dao)
    }

}