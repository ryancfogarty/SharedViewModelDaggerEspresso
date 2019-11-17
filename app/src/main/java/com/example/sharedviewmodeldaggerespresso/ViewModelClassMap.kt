package com.example.sharedviewmodeldaggerespresso

import androidx.lifecycle.ViewModel

typealias ViewModelClassMap = Map<Class<out ViewModel>, @JvmSuppressWildcards Class<out ViewModel>>

@Suppress("UNCHECKED_CAST")
inline fun <reified T: ViewModel> ViewModelClassMap.getClass(clazz: Class<out ViewModel>): Class<T> =
    requireNotNull(get(clazz)) as Class<T>
