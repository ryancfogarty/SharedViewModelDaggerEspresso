package com.example.sharedviewmodeldaggerespresso

import android.app.Activity
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import org.junit.rules.ExternalResource

class AndroidInjectionRule : ExternalResource() {

    var activityInjector: AndroidInjector<Activity> = AndroidInjector { }
    var fragmentInjector: AndroidInjector<Fragment> = AndroidInjector { }

    override fun before() {
        TestApp.activityInjector = AndroidInjector { activity ->
            activityInjector.inject(activity)
        }

        TestApp.fragmentInjector = AndroidInjector { fragment ->
            fragmentInjector.inject(fragment)
        }
    }
}