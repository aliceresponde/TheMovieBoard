package com.aliceresponde.themovieboard.di.module.data

import com.aliceresponde.themovieboard.di.module.data.DataBaseModule
import com.aliceresponde.themovieboard.di.module.data.NetworkModule
import com.aliceresponde.themovieboard.di.module.data.RepositoryModule
import dagger.Module

@Module(
    includes = [
        NetworkModule::class,
        DataBaseModule::class,
        RepositoryModule::class
    ]
)
class DataModule