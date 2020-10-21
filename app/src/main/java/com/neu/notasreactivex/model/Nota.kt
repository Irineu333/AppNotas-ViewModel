package com.neu.notasreactivex.model

data class Nota(var titulo: String, var descricao: String = "", val id: Int = DEFAULT_ID) {


    companion object {

        const val DEFAULT_ID = -1

        fun criarNota(
            nota: String = "",
            titulo: String,
            descricao: String = "",
            id: Int = DEFAULT_ID
        ): Nota {
            val novaNota = Nota(titulo, descricao, id)
            novaNota.nota = nota
            return novaNota
        }

        fun criarNota(nota: Nota, id: Int): Nota {
            val (titulo, descricao) = nota
            return criarNota(nota.nota, titulo, descricao, id)
        }
    }

    var nota: String = ""
        get() = if (field.isEmpty()) "Vazio" else field

    fun isEmpty(): Boolean {
        return nota.isEmpty()
    }

    fun isWithoutId(): Boolean {
        return id == DEFAULT_ID
    }
}