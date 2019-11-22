package com.example.sharedviewmodeldaggerespresso

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class TestRunner : AndroidJUnitRunner() {

    @Throws(InstantiationException::class, IllegalAccessException::class, ClassNotFoundException::class)
    override fun newApplication(classLoader: ClassLoader, className: String, context: Context): Application {
        return super.newApplication(classLoader, TestApp::class.java.name, context)
    }
}
