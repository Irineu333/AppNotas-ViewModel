package com.neu.notasreactivex.fragment.main

import com.neu.notasreactivex.model.Nota

interface NotasView {
    fun insiraNota(nota: Nota)
    fun atualizaNota(nota: Nota)
    fun setListaNotas(value: MutableList<Nota>)
}