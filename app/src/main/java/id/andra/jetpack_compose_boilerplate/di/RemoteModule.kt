package id.andra.jetpack_compose_boilerplate.di

import id.andra.jetpack_compose_boilerplate.utils.network.AuthProvider
import id.andra.jetpack_compose_boilerplate.utils.network.HttpRequestInterceptor
import id.andra.jetpack_compose_boilerplate.utils.network.createChuckInterceptor
import id.andra.jetpack_compose_boilerplate.utils.network.createHttpLoggingInterceptor
import id.andra.jetpack_compose_boilerplate.utils.network.createHttpRequestInterceptor
import id.andra.jetpack_compose_boilerplate.BuildConfig
import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.andra.jetpack_compose_boilerplate.utils.network.createMoshi
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return createMoshi()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return createHttpLoggingInterceptor(isDev = BuildConfig.DEBUG)
    }

    @Provides
    @Singleton
    fun provideHttpRequestInterceptor(authProvider: AuthProvider): HttpRequestInterceptor {
        return createHttpRequestInterceptor(authProvider)
    }

    @Provides
    @Singleton
    fun provideChuckInterceptor(@ApplicationContext context: Context): ChuckerInterceptor {
        return createChuckInterceptor(context)
    }
}