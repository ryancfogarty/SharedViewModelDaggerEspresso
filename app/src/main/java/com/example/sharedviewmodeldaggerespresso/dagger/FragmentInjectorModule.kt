package com.example.sharedviewmodeldaggerespresso.dagger

import com.example.sharedviewmodeldaggerespresso.ui.main.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
@Suppress("unused")
abstract class FragmentInjectorsModule {

    @PerFragment
    @ContributesAndroidInjector
    abstract fun mainFragment(): MainFragment
}