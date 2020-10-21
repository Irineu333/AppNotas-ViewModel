package com.neu.notasreactivex.reactive

import com.neu.notasreactivex.model.Nota
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer

object RXJava {
    var observer_lista : Observer<Nota>? = null
    var observer_nota : Observer<Nota>? = null

    fun atualizarNota(nota : Nota)
    {
        val just = Observable.just(nota)

        if(observer_lista != null)
        just.subscribe(observer_lista)
        if(observer_nota != null)
        just.subscribe(observer_nota)
    }
}