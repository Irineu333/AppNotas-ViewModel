package com.neu.notasreactivex.fragment.nota

import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.neu.notasreactivex.model.Nota

interface NotaPresenter {
    fun getNotaFromArgs(requireArguments: Bundle)
    fun getLiveData() : MutableLiveData<Nota>
    fun setarDados()
    fun setObserve(viewLifecycleOwner: LifecycleOwner)
    fun getNotaJson(): String
}