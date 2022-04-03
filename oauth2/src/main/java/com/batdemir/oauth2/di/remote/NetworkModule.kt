package com.batdemir.oauth2.di.remote

import android.content.Context
import com.batdemir.core.data.remote.interceptor.AuthInterceptor
import com.batdemir.core.data.remote.interceptor.HostSelectionInterceptor
import com.batdemir.core.extensions.DateFormat
import com.batdemir.oauth2.BuildConfig
import com.batdemir.oauth2.other.Constant
import com.google.gson.GsonBuilder
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

@InstallIn(SingletonComponent::class)
@Module(
    includes = [
        RemoteDataSourceModule::class,
        RemoteServiceModule::class
    ]
)
object NetworkModule {
    @Provides
    fun provideRetrofitBuilder(): Retrofit.Builder = Retrofit.Builder()

    @Provides
    fun provideOkHttpClientBuilder(
        hostSelectionInterceptor: HostSelectionInterceptor
    ): OkHttpClient.Builder = OkHttpClient.Builder()
        .readTimeout(Constant.TIMEOUT, TimeUnit.SECONDS)
        .connectTimeout(Constant.TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(hostSelectionInterceptor)

    @Provides
    fun provideConverterFactory(): Converter.Factory = GsonConverterFactory
        .create(
            GsonBuilder()
                .setDateFormat(DateFormat.DEFAULT_DATE_FORMAT.toString())
                .create()
        )

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()

    @Provides
    @Named(NAME_AUTH_CLIENT)
    fun provideOkHttpClientAuth(
        @ApplicationContext context: Context,
        builder: OkHttpClient.Builder,
        interceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        if (BuildConfig.DEBUG) {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addNetworkInterceptor(interceptor)
            builder.addInterceptor(ChuckInterceptor(context))
        }
        return builder.build()
    }

    @Provides
    @Named(NAME_API_CLIENT)
    fun provideOkHttpClientApi(
        @ApplicationContext context: Context,
        builder: OkHttpClient.Builder,
        interceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor
    ): OkHttpClient {
        if (BuildConfig.DEBUG) {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addNetworkInterceptor(interceptor)
            builder.addInterceptor(ChuckInterceptor(context))
        }
        builder.addInterceptor(authInterceptor)
        return builder.build()
    }

    internal const val NAME_AUTH_CLIENT = "auth_client"
    internal const val NAME_API_CLIENT = "api_client"
}
