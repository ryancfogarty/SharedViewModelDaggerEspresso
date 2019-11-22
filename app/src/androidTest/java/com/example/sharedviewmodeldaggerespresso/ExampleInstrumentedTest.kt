package com.example.sharedviewmodeldaggerespresso

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.example.sharedviewmodeldaggerespresso.ui.main.MainFragment
import com.example.sharedviewmodeldaggerespresso.ui.main.MainViewModel
import dagger.android.AndroidInjector
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*
import android.widget.TextView
import org.hamcrest.Matchers.*


class MainViewModelStub : MainViewModel() {
    override val rand = MutableLiveData<Int>().apply {
        postValue(Random().nextInt())
    }
}
class ExampleInstrumentedTest {
    private val targetContext = ApplicationProvider.getApplicationContext<Context>()

    @Rule
    @JvmField
    val testActivityRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Rule
    @JvmField
    var androidInjectionRule = AndroidInjectionRule()

    private val classMapStub: ViewModelClassMap =
        mapOf(MainViewModel::class.java to MainViewModelStub::class.java)
    private val viewModelStub = MainViewModelStub()
    private val factoryStub = object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T = viewModelStub as T
    }

    @Before
    fun setUp() {
        androidInjectionRule.fragmentInjector = AndroidInjector { fragment ->
            when (fragment) {
                is MainFragment -> {
                    fragment.inject(classMapStub, factoryStub)
                }
            }
        }

        androidInjectionRule.activityInjector = AndroidInjector { activity ->
            when (activity) {
                is MainActivity -> activity.inject(viewModelStub)
            }
        }
    }

    @Test
    fun activity_and_fragment_use_same_view_model_instance() {
        val activity = MainActivity.newIntent(targetContext)
        testActivityRule.launchActivity(activity)
        
        val rand = viewModelStub.rand.value

        onView(allOf(
            instanceOf(TextView::class.java),
            not(withId(R.id.message))
        )).check(matches(withText(rand.toString())))

        onView(withId(R.id.message)).check(matches(withText(rand.toString())))
    }
}