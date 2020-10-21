package com.neu.notasreactivex.fragment.edit

interface EditPresenter {
    fun setarDados()
    fun input(titulo : String, descricao : String, nota : String)
}