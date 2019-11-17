package com.example.sharedviewmodeldaggerespresso.dagger

import com.example.sharedviewmodeldaggerespresso.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
@Suppress("unused")
abstract class ActivityInjectorsModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [
        ViewModelModule.InjectViewModel::class
    ])
    abstract fun mainActivity(): MainActivity

}