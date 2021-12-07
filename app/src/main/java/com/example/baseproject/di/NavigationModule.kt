package com.example.baseproject.di

import com.example.baseproject.navigation.AppNavigation
import com.example.baseproject.navigation.AppNavigatorImpl
import com.example.core.navigationComponent.BaseNavigator
import com.example.setting.DeviceNavigation
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class NavigationModule {

    @Binds
    abstract fun provideBaseNavigation(navigation: AppNavigatorImpl): BaseNavigator

    @Binds
    abstract fun provideAppNavigation(navigation: AppNavigatorImpl): AppNavigation


    @Binds
    abstract fun provideDemoNavigation(navigation: AppNavigatorImpl): DeviceNavigation
}