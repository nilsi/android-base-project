package com.nilsi.main.presentation.main

import android.widget.Toast
import com.nilsi.main.presentation.common.UiEvent
import com.nilsi.main.R
import com.nilsi.main.databinding.FragmentMainBinding
import com.nilsi.main.presentation.common.BaseFragment

class MainFragment :
    BaseFragment<MainUiState, MainViewModel, FragmentMainBinding>(
        MainViewModel::class
    ) {

    override fun getLayoutResId(): Int {
        return R.layout.fragment_main
    }

    override fun handleEvent(event: UiEvent) {
        when (event) {
            is UiEvent.ShowMessage -> Toast.makeText(
                context,
                event.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun render(state: MainUiState) {
        when (state) {
            MainUiState.Initial -> {
                // Show initial
            }
            MainUiState.Loading -> {
                // Show loading
            }
            MainUiState.ProductFetched -> {
                // Show product
            }
        }
    }

    override fun initViews() {
        viewModel.fetchProduct()
    }
}
