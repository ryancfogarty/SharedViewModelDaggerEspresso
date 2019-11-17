package com.example.sharedviewmodeldaggerespresso

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import com.example.sharedviewmodeldaggerespresso.dagger.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainApplication : Application(), HasActivityInjector, HasSupportFragmentInjector {
    private lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>
    private lateinit var dispatchingFragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    fun inject(
        dispatchingActivityInjector: DispatchingAndroidInjector<Activity>,
        dispatchingFragmentInjector: DispatchingAndroidInjector<Fragment>
    ) {
        this.dispatchingActivityInjector = dispatchingActivityInjector
        this.dispatchingFragmentInjector = dispatchingFragmentInjector
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityInjector

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingFragmentInjector

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent
            .builder()
            .build()
            .inject(this)
    }
}