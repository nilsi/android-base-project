package com.nilsi.main.presentation.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass


abstract class BaseFragment<UI_STATE : UiState, VIEW_MODEL : BaseViewModel<UI_STATE>, DATA_BINDING : ViewDataBinding>(
    modelClass: KClass<VIEW_MODEL>,
    sharedViewModel: Boolean = true,
) : Fragment() {

    private lateinit var binding: DATA_BINDING

    val viewModel: VIEW_MODEL by if (sharedViewModel)
        sharedViewModel(null, modelClass) else
        viewModel(null, modelClass)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding = DataBindingUtil.inflate(
            inflater,
            getLayoutResId(),
            container,
            false
        )

        initViews()
        initObservers()

        return binding.root
    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            launch {
                viewModel.getState().collect { render(it) }
            }
            launch {
                viewModel.getEvents().collect { handleDefaultEvents(it) }
            }
        }
    }

    fun setToolbarTitle(title: Int) {
        (activity as BaseActivity<*, *, *>).setToolbarTitle(title)
    }

    fun setToolbarTitle(title: String) {
        (activity as BaseActivity<*, *, *>).setToolbarTitle(title)
    }


    private fun handleDefaultEvents(event: UiEvent) {
        when (event) {
            is UiEvent.ShowMessage -> {
                event.message.let {
                    Toast.makeText(context, it, Toast.LENGTH_LONG)
                }
            }
        }
    }

    @LayoutRes
    abstract fun getLayoutResId(): Int
    abstract fun render(state: UI_STATE)
    abstract fun handleEvent(event: UiEvent)
    abstract fun initViews()
}
