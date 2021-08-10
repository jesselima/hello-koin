package com.jeziellago.hellokoin.core.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jeziellago.hellokoin.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = setupRetrofit()

fun setupRetrofit() : Retrofit {
    val httpClient = OkHttpClient.Builder()

    httpClient.addInterceptor { chain ->
        val originalRequest = chain.request()

        val originalHttpUrl = originalRequest.url

        // TODO ==== This block adds query params to - If not in use just remove the block
        val httpUrlBuilder = originalHttpUrl.newBuilder()
            .addQueryParameter("api_key", BuildConfig.SOME_API_KEY)
            .build()
        val requestBuilder = originalRequest.newBuilder().url(httpUrlBuilder)
        // End of code add params

        // TODO ==== This lines adds params to header - If nto in use, just remove the lines
        requestBuilder.addHeader("api_key", BuildConfig.SOME_API_KEY)
        requestBuilder.addHeader("build_type", BuildConfig.BUILD_TYPE)
        requestBuilder.addHeader("app_version", BuildConfig.VERSION_NAME)

        val request = requestBuilder.build()

        chain.proceed(request)
    }

    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

    httpClient.callTimeout(timeout = 20L, unit = TimeUnit.SECONDS)
    httpClient.addInterceptor(httpLoggingInterceptor)

    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(httpClient.build())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
}

class ApiService {
    companion object {
        fun <T> createService(service: Class<T>) : T {
            return retrofit.create(service)
        }
    }
}

