package com.neu.notasreactivex.fragment.main

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.neu.notasreactivex.model.Nota

class NotasPresenterImpl(
    context: Context,
    val notasView: NotasView,
    val notaLiveData: MutableLiveData<Nota>
) : NotasPresenter {

    private val listaNotas = mutableListOf<Nota>()
    //override NotasPresenter

    override fun getListaNotas() = listaNotas

    override fun setObserve(viewLifecycleOwner: LifecycleOwner) {
        notaLiveData.observe(viewLifecycleOwner, {
            if (it.id == Nota.DEFAULT_ID)
            {
                notasView.insiraNota(it)
            } else {
                notasView.atualizaNota(it)
            }
        })
    }
}