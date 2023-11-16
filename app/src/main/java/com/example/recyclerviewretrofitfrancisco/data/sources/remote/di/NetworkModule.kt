package com.example.recyclerviewretrofitfrancisco.data.sources.remote.di

import com.example.recyclerviewretrofitfrancisco.data.sources.remote.CustomerService
import com.example.recyclerviewretrofitfrancisco.utils.Constants.BASE_URL
import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.time.LocalDate
import javax.inject.Singleton


class LocalDateAdapter {
    @ToJson
    fun toJson(localDate: LocalDate): String {
        // Convertir LocalDate a String para la serialización en el JSON
        return localDate.toString()
    }

    @FromJson
    fun fromJson(localDateString: String): LocalDate {
        // Convertir String a LocalDate para la deserialización desde JSON
        return LocalDate.parse(localDateString)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    /*   @Singleton
       @Provides
       fun provideConverterFactory(): GsonConverterFactory =
           GsonConverterFactory.create()*/
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        // Create and configure OkHttpClient here
        return OkHttpClient.Builder().build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        // Create and configure Moshi here
        return Moshi.Builder().build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit {
        val moshiBuilder = moshi.newBuilder()
            .add(LocalDateAdapter())

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshiBuilder.build()))
            .build()
    }

    @Singleton
    @Provides
    fun providePostService(retrofit: Retrofit): CustomerService =
        retrofit.create(CustomerService::class.java)

}