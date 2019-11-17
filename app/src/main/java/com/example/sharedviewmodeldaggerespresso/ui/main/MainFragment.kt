package com.example.sharedviewmodeldaggerespresso.ui.main

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sharedviewmodeldaggerespresso.R
import com.example.sharedviewmodeldaggerespresso.ViewModelClassMap
import com.example.sharedviewmodeldaggerespresso.getClass
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.main_fragment.*
import javax.inject.Inject

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var classMap: ViewModelClassMap
    private lateinit var factory: ViewModelProvider.Factory

    @Inject
    fun inject(classMap: ViewModelClassMap, factory: ViewModelProvider.Factory) {
        this.classMap = classMap
        this.factory = factory
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders
            .of(requireActivity(), factory)
            .get(classMap.getClass<MainViewModel>(MainViewModel::class.java))

        viewModel.rand.observe(this, Observer {
            message.text = it.toString()
        })
    }
}
