package com.aliceresponde.themovieboard.di.module.data

import com.aliceresponde.themovieboard.BuildConfig
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class RemoteModule {

    @Provides
    @Singleton
    @Named("apiKey")
    fun apiKeyProvider() = BuildConfig.API_KEY

    @Provides
    @Singleton
    @Named("imageUrl")
    fun imageUrlProvider() = BuildConfig.IMG_URL

    @Provides
    @Singleton
    @Named("baseUrl")
    fun baseUrlProvider() = BuildConfig.BASE_URL

    @Provides
    @Singleton
    @Named("videoUrl")
    fun videoUrlProvider() = BuildConfig.VIDEO_URL


}