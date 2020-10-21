package com.neu.notasreactivex.fragment.nota

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.neu.notasreactivex.R
import com.neu.notasreactivex.fragment.edit.EditBottomSheet
import com.neu.notasreactivex.model.Nota
import com.neu.notasreactivex.reactive.RXJava
import kotlinx.android.synthetic.main.fragment_nota.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class NotaFragment : Fragment(), NotaView, View.OnClickListener {

    private var param1: String? = null
    private var param2: String? = null

    private var notaPresenter = NotaPresenterImpl(this)

    //override Fragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_nota, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notaPresenter.getNotaFromArgs(requireArguments())
        editBtn.setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        RXJava.observer_nota = null
        Log.d("NotaFragment", "onDestroy")
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            NotaFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    //override NotaView

    override fun setTitulo(titulo: String) {
        this.titulo.text = titulo
    }

    override fun setDescricao(descricao: String) {
        this.descricao.text = descricao
        if(descricao.isEmpty())
            this.descricao.visibility = View.GONE
        else
            this.descricao.visibility = View.VISIBLE
    }

    override fun setNota(nota: String) {
        this.nota.text = nota
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.editBtn -> {
                EditBottomSheet(notaPresenter.getNotaJson()).show(childFragmentManager, EditBottomSheet.EDITAR_NOTA)
            }
        }
    }
}