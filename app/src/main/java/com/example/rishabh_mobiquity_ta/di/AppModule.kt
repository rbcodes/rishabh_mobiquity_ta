package com.example.rishabh_mobiquity_ta.di

import com.example.rishabh_mobiquity_ta.ui.MainActivity
import com.example.rishabh_mobiquity_ta.ui.data.AppConstants
import com.example.rishabh_mobiquity_ta.ui.data.api.ApiService
import com.example.rishabh_mobiquity_ta.ui.data.datasource.ShoppingDataSource
import com.example.rishabh_mobiquity_ta.ui.data.datasource.ShoppingDataSourceImpl
import com.example.rishabh_mobiquity_ta.ui.repository.DefaultShoppingRepository
import com.example.rishabh_mobiquity_ta.ui.repository.ShoppingRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit{

        val httpLoggingInterceptor =HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor)
            .readTimeout(60, TimeUnit.SECONDS)

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory()).build()

        return Retrofit.Builder()
            .baseUrl(AppConstants.APP_BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    }

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): ApiService{
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesShoppingDataSource(appService: ApiService) : ShoppingDataSource{
        return ShoppingDataSourceImpl(appService)
    }

    @Provides
    @Singleton
    fun providesDefaultShoppingRepository(shoppingDataSource: ShoppingDataSource): DefaultShoppingRepository{
        return ShoppingRepository(shoppingDataSource)
    }

}