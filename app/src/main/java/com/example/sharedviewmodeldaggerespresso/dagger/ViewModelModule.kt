package com.example.sharedviewmodeldaggerespresso.dagger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.sharedviewmodeldaggerespresso.MainActivity
import com.example.sharedviewmodeldaggerespresso.ViewModelClassMap
import com.example.sharedviewmodeldaggerespresso.ui.main.MainViewModel
import com.example.sharedviewmodeldaggerespresso.ui.main.MainViewModelImpl
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(includes = [
    ViewModelModule.ProvideViewModel::class,
    ViewModelModule.ViewModelAbstractionMap::class
])
abstract class ViewModelModule {

    /*
        Module that uses bound activity and provided factory uses ViewModelProviders to provide instance of ViewModel
    */
    @Module
    class InjectViewModel {

        @Provides
        fun mainViewModel(
            factory: ViewModelProvider.Factory,
            target: MainActivity
        ): MainViewModel = ViewModelProviders.of(target, factory).get(MainViewModelImpl::class.java)

    }

    /*
        This is used so fragments can get the ViewModel from their activity without knowing what the impl is.
        When testing, provide a Map of Pairs of the abstract class to the stub implementation
     */
    @Module
    class ViewModelAbstractionMap {

        @Provides
        fun viewModelClassMap(): ViewModelClassMap = mapOf(
            MainViewModel::class.java to MainViewModelImpl::class.java
        )
    }

    @Module
    class ProvideViewModel {

        @Provides
        @IntoMap
        @ViewModelKey(MainViewModel::class)
        fun mainViewModel(): ViewModel = MainViewModelImpl()

    }
}