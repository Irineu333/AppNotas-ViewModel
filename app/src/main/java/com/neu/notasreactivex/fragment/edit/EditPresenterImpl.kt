package com.neu.notasreactivex.fragment.edit

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.neu.notasreactivex.activity.NotaViewModel
import com.neu.notasreactivex.model.Nota

class EditPresenterImpl(val jsonNota: String?, val editView: EditView) : EditPresenter {

    private val nota: Nota = obterNota()

    //membros

    private fun obterNota(): Nota =
        if (jsonNota.isNullOrEmpty()) criarNota() else notaFromJson(jsonNota)

    private fun notaFromJson(notaJson: String) = Gson().fromJson(notaJson, Nota::class.java)

    private fun criarNota() = Nota.criarNota(titulo = "Nova Nota", nota = "Estou muito...")

    //override EditPresenter

    override fun setarDados() {
        nota.let {
            editView.setTitulo(it.titulo)
            editView.setDescricao(it.descricao)
            editView.setNota(it.nota)
            if (nota.isWithoutId()) editView.setTextTerminarBtn("Criar") else editView.setTextTerminarBtn(
                "Alterar"
            )
        }
    }

    override fun input(titulo: String, descricao: String, nota: String) {
        this.nota.titulo = titulo
        this.nota.descricao = descricao
        this.nota.nota = nota

        NotaViewModel.nota.value = this.nota
    }

}