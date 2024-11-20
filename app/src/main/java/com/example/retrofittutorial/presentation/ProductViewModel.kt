package com.example.retrofittutorial.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofittutorial.data.model.ProductRepository
import com.example.retrofittutorial.data.model.ProductX
import com.example.retrofittutorial.data.model.Result
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductViewModel ( private val productRepository: ProductRepository ): ViewModel(){
    private val _products = MutableStateFlow<List<ProductX>>(emptyList())
    val products = _products
    private val _showErrorToastChannel = Channel<Boolean>()
    val showErrorToastChannel = _showErrorToastChannel
    init {
        viewModelScope.launch {
            productRepository.getProductsList().collectLatest { result ->
                when(result){
                    is Result.Error -> {
                        _showErrorToastChannel.send(true)
                    }
                    is Result.Success -> {
                        result.data?.let { products ->
                            _products.update { products }
                        }
                    }
                }
            }
        }
    }
}