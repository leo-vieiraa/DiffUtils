package com.leovieira.diffutils.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leovieira.diffutils.model.Image
import com.leovieira.diffutils.repository.PixabayRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val repository: PixabayRepository
) : ViewModel() {

    private var _images = MutableLiveData<List<Image>>()
    val images: LiveData<List<Image>> = _images

    fun getImages(q: String = "") {
        viewModelScope.launch {
            val returnedImages = repository.getImages(q= q)
            returnedImages?.let {
                _images.value = it
            }
        }
    }

}