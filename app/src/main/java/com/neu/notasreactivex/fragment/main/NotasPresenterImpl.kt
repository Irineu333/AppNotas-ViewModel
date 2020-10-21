package com.neu.notasreactivex.fragment.main

import android.content.Context
import android.util.Log
import com.neu.notasreactivex.model.Nota
import com.neu.notasreactivex.reactive.RXJava.observer_lista
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

class NotasPresenterImpl(context : Context, val notasView : NotasView) : NotasPresenter, Observer<Nota> {


    var lista = mutableListOf<Nota>()

    init {
        observer_lista = this
    }

    //override NotasPresenter

    override fun getListaNotas() = lista

    // override Observer<Nota>

    override fun onSubscribe(d: Disposable?) {

    }

    override fun onNext(nota: Nota?) {
        if(nota!==null) {
            setarNota(nota)
        }
    }

    /**
     * Identifica se a nota é nova (foi criada) ou foi alterada e chama a
     * função mais adequada delegando a responsabilidade de inserir ou atualizar para a view
     * @author Irineu A. Silva
     */
    private fun setarNota(nota: Nota) {
        if (nota.id == Nota.DEFAULT_ID)
            notasView.insiraNota(nota)
        else
            notasView.atualizaNota(nota)
    }

    override fun onError(e: Throwable?) {
        Log.d("NotasPresenterImpl", "Observer: onError ${e?.message}")
    }

    override fun onComplete() {

    }
}