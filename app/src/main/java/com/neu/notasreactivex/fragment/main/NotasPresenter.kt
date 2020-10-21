package com.neu.notasreactivex.fragment.main

import com.neu.notasreactivex.model.Nota

interface NotasPresenter {

    fun getListaNotas () : MutableList<Nota>
}