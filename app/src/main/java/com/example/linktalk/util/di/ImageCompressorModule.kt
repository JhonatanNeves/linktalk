package com.example.linktalk.util.di

import com.example.linktalk.util.image.ImageCompressor
import com.example.linktalk.util.image.ImageCompressorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn (ViewModelComponent::class)
interface ImageCompressorModule {
    @Binds
    fun bindImageCompressor(compressor: ImageCompressorImpl): ImageCompressor
}
