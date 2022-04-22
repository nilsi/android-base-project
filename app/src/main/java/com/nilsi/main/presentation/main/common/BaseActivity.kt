package com.nilsi.main.presentation.main.common

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.lifecycleScope
import com.main.presentation.main.common.UiEvent
import com.main.presentation.main.common.UiEvent.ShowMessage
import com.main.presentation.main.common.UiState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

abstract class BaseActivity<UI_STATE : UiState, VIEW_MODEL : BaseViewModel<UI_STATE>, DATA_BINDING : ViewDataBinding>(
    modelClass: KClass<VIEW_MODEL>
) : AppCompatActivity() {

    protected lateinit var binding: DATA_BINDING

    val viewModel: VIEW_MODEL by viewModel(null, modelClass)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = setContentView(this, getLayoutResId());

        initViews()
        initObservers()
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

    private fun handleDefaultEvents(event: UiEvent) {
        when (event) {
            is ShowMessage -> {
                event.message.let {
                    Toast.makeText(baseContext, it, Toast.LENGTH_LONG)
                }
            }
        }
    }


    fun setToolbarTitle(title: Int) {
        supportActionBar?.title = getString(title)
    }

    fun setToolbarTitle(title: String) {
        supportActionBar?.title = title
    }

    @LayoutRes
    abstract fun getLayoutResId(): Int
    abstract fun initViews()
    abstract fun render(state: UI_STATE)
    abstract fun handleEvent(event: UiEvent)
}




