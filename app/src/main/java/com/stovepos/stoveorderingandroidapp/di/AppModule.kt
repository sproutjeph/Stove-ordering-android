package com.stovepos.stoveorderingandroidapp.di

import com.stovepos.stoveorderingandroidapp.network.StoveApi
import com.stovepos.stoveorderingandroidapp.repository.MenuDataRepository
import com.stovepos.stoveorderingandroidapp.repository.MenuDataRepositoryImpl
import com.stovepos.stoveorderingandroidapp.repository.VenueInfoRepository
import com.stovepos.stoveorderingandroidapp.repository.VenueInfoRepositoryImpl
import com.stovepos.stoveorderingandroidapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideStoveApi(): StoveApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StoveApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMenuDataRepository(api: StoveApi):MenuDataRepository{
        return MenuDataRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideVenueInfoRepository(api: StoveApi): VenueInfoRepository{
        return VenueInfoRepositoryImpl(api)
    }
}