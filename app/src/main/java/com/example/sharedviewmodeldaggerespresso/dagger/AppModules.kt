package com.example.sharedviewmodeldaggerespresso.dagger

import android.app.Application
import android.content.Context
import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sharedviewmodeldaggerespresso.MainApplication
import dagger.MapKey
import dagger.Module
import dagger.Provides
import javax.inject.Provider
import javax.inject.Qualifier
import javax.inject.Singleton
import kotlin.reflect.KClass


/* Key used to associate ViewModel types with providers */
@MapKey
@Target(AnnotationTarget.FUNCTION)
annotation class ViewModelKey(
    val value: KClass<out ViewModel>
)

@Module
class AppModules {

    @Provides
    @Singleton
    fun viewModelFactory(
        providers: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
    ) = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            val t = providers[modelClass] ?: providers
                .asIterable()
                .firstOrNull { it.key.isAssignableFrom(modelClass) }
                ?.value

            return requireNotNull(t).get() as T
        }
    }
}