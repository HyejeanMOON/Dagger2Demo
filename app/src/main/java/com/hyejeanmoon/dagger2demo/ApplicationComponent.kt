package com.hyejeanmoon.dagger2demo

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class,
        MainActivityModule.Builder::class,
        AndroidSupportInjectionModule::class,
        ApiModule::class]
)
interface ApplicationComponent : AndroidInjector<MyApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    override fun inject(app: MyApplication)

}

internal fun Application.createAppComponent() = DaggerApplicationComponent.builder()
    .application(this)
    .build()




