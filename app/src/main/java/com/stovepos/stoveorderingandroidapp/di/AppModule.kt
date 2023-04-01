package com.stovepos.stoveorderingandroidapp.di

import android.content.Context
import androidx.room.Room
import com.stovepos.stoveorderingandroidapp.data.local.MenuCatButtonDao
import com.stovepos.stoveorderingandroidapp.data.local.MenuItemDao
import com.stovepos.stoveorderingandroidapp.data.local.MenuItemDatabase
import com.stovepos.stoveorderingandroidapp.network.StoveApi
import com.stovepos.stoveorderingandroidapp.repository.*
import com.stovepos.stoveorderingandroidapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    @Singleton
    fun provideMenuItemsRepository(db: MenuItemDatabase): MenuItemsRepository {

        return MenuItemsRepositoryImpl(db.menuItemDao, db.menuCatButtonDao)

    }

    @Provides
    @Singleton
    fun provideVenueInfoLocalRepository(db: MenuItemDatabase): VenueInfoLocalRepository {
        return VenueInfoLocalRepositoryImpl(db.venueInfoDao)
    }

    @Provides
    @Singleton
    fun providesMenuItemDatabase(@ApplicationContext context: Context): MenuItemDatabase =
        Room.databaseBuilder(
            context,
            klass = MenuItemDatabase::class.java,
            name = MenuItemDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()

}