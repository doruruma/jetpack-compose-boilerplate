package id.andra.jetpack_compose_boilerplate.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.andra.jetpack_compose_boilerplate.utils.network.AuthProvider
import id.andra.jetpack_compose_boilerplate.utils.pref.CacheManager
import id.andra.jetpack_compose_boilerplate.utils.pref.PreferenceProvider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideCacheManager(@ApplicationContext context: Context): CacheManager {
        return CacheManager(context)
    }

    @Provides
    @Singleton
    fun provideAuthProvider(cacheManager: CacheManager): AuthProvider {
        return AuthProvider(cacheManager)
    }

    @Provides
    @Singleton
    fun providePreferenceProvider(cacheManager: CacheManager): PreferenceProvider {
        return PreferenceProvider(cacheManager)
    }
}