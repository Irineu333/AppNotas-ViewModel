package com.neu.notasreactivex.fragment.main

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.neu.notasreactivex.model.Nota

interface NotasPresenter {

    fun getListaNotas () : MutableList<Nota>
    fun setObserve(viewLifecycleOwner: LifecycleOwner)
}