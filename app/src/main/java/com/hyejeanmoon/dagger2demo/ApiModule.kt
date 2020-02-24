package com.hyejeanmoon.dagger2demo

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    @ApplicationContext
    internal fun provideApplicationContext(application: Application): Context {
        return application
    }
}