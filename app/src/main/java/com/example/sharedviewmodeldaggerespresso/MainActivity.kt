package com.example.sharedviewmodeldaggerespresso

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.sharedviewmodeldaggerespresso.ui.main.MainViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    @Inject
    fun inject(viewModel: MainViewModel) {
        this.viewModel = viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        viewModel.rand.observe(this, Observer {
            title = it.toString()
        })
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
}
