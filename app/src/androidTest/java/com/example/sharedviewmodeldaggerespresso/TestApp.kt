package com.example.sharedviewmodeldaggerespresso

import android.app.Activity
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector

class TestApp : MainApplication() {

    companion object {
        var activityInjector: AndroidInjector<Activity>? = null
        var fragmentInjector: AndroidInjector<Fragment>? = null
    }

    override fun activityInjector(): AndroidInjector<Activity> =
        activityInjector ?: super.activityInjector()

    override fun supportFragmentInjector(): AndroidInjector<Fragment> =
        fragmentInjector ?: super.supportFragmentInjector()
}