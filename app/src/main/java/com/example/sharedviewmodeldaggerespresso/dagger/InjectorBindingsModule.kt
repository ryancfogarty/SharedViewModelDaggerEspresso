package com.example.sharedviewmodeldaggerespresso.dagger

import android.app.Activity
import androidx.fragment.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector

@Module
@Suppress("Unused")
abstract class InjectorBindingsModule {
    @Binds
    abstract fun activityInjector(activityInjector: DispatchingAndroidInjector<Activity>) : AndroidInjector<Activity>

    @Binds
    abstract fun fragmentInjector(activityInjector: DispatchingAndroidInjector<Fragment>) : AndroidInjector<Fragment>
}