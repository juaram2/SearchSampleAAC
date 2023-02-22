package com.searchsampleaac.di

import com.searchsampleaac.data.SearchRepository
import com.searchsampleaac.data.SearchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class AppModule {
    @Binds
    abstract fun provideSearchRepository(impl: SearchRepositoryImpl): SearchRepository
}