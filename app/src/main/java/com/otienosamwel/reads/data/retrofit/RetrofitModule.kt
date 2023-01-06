package com.otienosamwel.reads.data.retrofit

import android.content.Context
import com.otienosamwel.reads.R
import com.otienosamwel.reads.utils.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class EndpointsModule @Inject constructor() {

    @Provides
    @Singleton
    fun provideApiService(retrofitModule: RetrofitModule): ApiEndpoints {
        return retrofitModule.retrofit.create(ApiEndpoints::class.java)
    }
}

class RetrofitModule @Inject constructor(
    @ApplicationContext context: Context, val preferences: Preferences
) {
    inner class AuthInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()
            val newRequest =
                originalRequest.newBuilder().header("Bearer", preferences.getToken() ?: "")

            return chain.proceed(newRequest.build())
        }
    }

    private val logging = HttpLoggingInterceptor().also {
        it.setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor())
        .addInterceptor(logging)
        .build();
    private val baseUrl = context.getString(R.string.base_url)


    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
