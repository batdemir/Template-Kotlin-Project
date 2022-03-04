package com.batdemir.template.di.remote

import android.content.Context
import com.batdemir.core.data.remote.interceptor.*
import com.batdemir.core.extensions.DateFormat
import com.batdemir.template.BuildConfig
import com.batdemir.template.other.Constant
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
    fun provideHostSelectionInterceptor(): HostSelectionInterceptor = HostSelectionInterceptor()

    @Provides
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        builder: OkHttpClient.Builder,
        interceptor: HttpLoggingInterceptor,
        acceptLanguageInterceptor: AcceptLanguageInterceptor,
        authInterceptor: AuthInterceptor,
        encodingInterceptor: EncodingInterceptor,
        userAgentInterceptor: UserAgentInterceptor
    ): OkHttpClient {
        if (BuildConfig.DEBUG) {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addNetworkInterceptor(interceptor)
            builder.addInterceptor(ChuckInterceptor(context))
        }
        return builder.build()
    }
}