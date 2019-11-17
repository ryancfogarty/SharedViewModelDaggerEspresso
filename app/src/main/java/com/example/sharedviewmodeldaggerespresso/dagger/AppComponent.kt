package com.example.sharedviewmodeldaggerespresso.dagger

import com.example.sharedviewmodeldaggerespresso.MainApplication
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModules::class,
    ViewModelModule::class,
    ActivityInjectorsModule::class,
    InjectorBindingsModule::class,
    AndroidInjectionModule::class,
    FragmentInjectorsModule::class
])
interface AppComponent {
    fun inject(application: MainApplication)
}