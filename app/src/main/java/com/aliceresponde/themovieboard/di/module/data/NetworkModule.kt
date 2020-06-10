package com.aliceresponde.themovieboard.di.module.data

import android.content.Context
import com.aliceresponde.themovieboard.data.remote.MoviesApi
import com.aliceresponde.themovieboard.data.remote.NetworkConnectionInterceptor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun providesNetworkConnectionInterceptor(context: Context) =
        NetworkConnectionInterceptor(
            context
        )

    @Provides
    @Singleton
    fun providesMoviesApi(interceptor: NetworkConnectionInterceptor) = MoviesApi.invoke(interceptor)
}
