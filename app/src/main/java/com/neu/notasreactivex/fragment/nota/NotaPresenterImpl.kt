package com.neu.notasreactivex.fragment.nota

import android.os.Bundle
import com.google.gson.Gson
import com.neu.notasreactivex.model.Nota
import com.neu.notasreactivex.reactive.RXJava
import com.neu.notasreactivex.reactive.RXJava.observer_nota
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_nota.*

class NotaPresenterImpl(val notaView: NotaView) : NotaPresenter, Observer<Nota> {
    private lateinit var nota: Nota

    init {
        observer_nota = this
    }

    private fun setarDados() {
        val (titulo, descricao) = nota
        notaView.setTitulo(titulo)
        notaView.setDescricao(descricao)
        notaView.setNota(nota.nota)
    }

    private fun getNotaFromJson(notaJson: String) {
        nota = Gson().fromJson(notaJson, Nota::class.java)
    }

    override fun getNotaFromArgs(requireArguments: Bundle) {
        getNotaFromJson(NotaFragmentArgs.fromBundle(requireArguments).nota)
        setarDados()
    }

    override fun getNotaJson() = Gson().toJson(nota)

    override fun onSubscribe(d: Disposable?) {

    }

    override fun onNext(nota: Nota?) {
        nota.let {
            if(nota?.id == this.nota.id)
            {
                this.nota = nota
                setarDados()
            }
        }
    }

    override fun onError(e: Throwable?) {
    }

    override fun onComplete() {
    }
}