package com.neu.notasreactivex.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.neu.notasreactivex.R
import com.neu.notasreactivex.model.Nota
import kotlin.random.Random

class NotasRecycler(
    private var listaNotas: MutableList<Nota> = mutableListOf(),
    private val onNotaClickListener: OnNotaClickListener
) :
    RecyclerView.Adapter<NotasRecycler.NotaHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaHolder {
        val inflate =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_nota, parent, false)
        return NotaHolder(inflate)
    }

    override fun onBindViewHolder(holder: NotaHolder, position: Int) {
        val nota = listaNotas[position]
        holder.setTitulo(nota.titulo)
        holder.setDescricao(nota.descricao)

        holder.itemView.setOnClickListener {
            onNotaClickListener.onItemClick(nota)
        }
    }

    override fun getItemCount(): Int {
        return listaNotas.size
    }

    fun insiraNota(nota: Nota) {

        var id = Random(listaNotas.size).nextInt()

        while(listaNotas.containsId(id))
            id++;

        listaNotas.add(Nota.criarNota(nota, id))

        notifyItemChanged(listaNotas.size-1)
    }

    private fun MutableList<Nota>.containsId(id: Int): Boolean {
        this.forEach {
            if (it.id == id)
                return true
        }
        return false
    }


    fun atualizaNota(nota: Nota) {

        for ((index, elemento) in listaNotas.withIndex()) {
            if (elemento.id == nota.id) {
                listaNotas.removeAt(index)
                listaNotas.add(index, nota)
                notifyItemInserted(index)
                break
            }
        }
        Log.d("NotasRecycler", "atualizaNota: não encontrou")
    }

    class NotaHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tituloTextView: TextView = view.findViewById(R.id.notaTitulo)
        val descricaoTextView: TextView = view.findViewById(R.id.notaDescricao)

        fun setTitulo(titulo: String) {
            tituloTextView.text = titulo
        }

        fun setDescricao(descricao: String) {
            descricaoTextView.text = descricao
            if(descricao.isEmpty())
                descricaoTextView.visibility = View.GONE
            else
                descricaoTextView.visibility = View.VISIBLE
        }

    }

    interface OnNotaClickListener {
        fun onItemClick(nota: Nota)
    }
}
