package com.example.setting.model

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AhihiModule {

    @Provides
    @Singleton
    fun getAhihi() = Ahihi()
}