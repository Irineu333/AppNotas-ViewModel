package com.neu.notasreactivex.fragment.nota

import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.neu.notasreactivex.activity.NotaViewModel
import com.neu.notasreactivex.model.Nota

class NotaPresenterImpl(val notaView: NotaView, var notaLiveData: MutableLiveData<Nota>) : NotaPresenter{

    override fun setObserve(viewLifecycleOwner: LifecycleOwner) {
        notaLiveData.observe(viewLifecycleOwner, {
            setarDados()
        })
    }

    override fun getNotaJson() = Gson().toJson(notaLiveData.value)

    override fun setarDados() {
        val nota : Nota = notaLiveData.value!!
        val (titulo, descricao) = nota
        notaView.setTitulo(titulo)
        notaView.setDescricao(descricao)
        notaView.setNota(nota.nota)
    }

    private fun getNotaFromJson(notaJson: String) {
        notaLiveData.value = Gson().fromJson(notaJson, Nota::class.java)
    }

    override fun getNotaFromArgs(requireArguments: Bundle) {
        getNotaFromJson(NotaFragmentArgs.fromBundle(requireArguments).nota)
    }

    override fun getLiveData() = notaLiveData
}