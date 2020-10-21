package com.neu.notasreactivex.fragment.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.neu.notasreactivex.R
import com.neu.notasreactivex.adapter.NotasRecycler
import com.neu.notasreactivex.fragment.edit.EditBottomSheet
import com.neu.notasreactivex.model.Nota
import com.neu.notasreactivex.reactive.RXJava
import kotlinx.android.synthetic.main.fragment_notas.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class NotasFragment : Fragment(), NotasView, NotasRecycler.OnNotaClickListener,
    View.OnClickListener {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var notasPresenter: NotasPresenter
    private lateinit var recyclerAdapter: NotasRecycler

    //override Fragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        notasPresenter = NotasPresenterImpl(requireContext(), this)
        Log.d("NotasFragment", "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d("NotasFragment", "onCreateView")
        return inflater.inflate(R.layout.fragment_notas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerAdapter = criarAdapter()
        notasRecycler.adapter = recyclerAdapter

        fab.setOnClickListener(this)
    }


    override fun onDestroy() {
        super.onDestroy()
        RXJava.observer_lista = null
        Log.d("NotasFragment", "onDestroy")
    }

    //membros

    private fun navegarPara(jsonNota: String) {
        findNavController().navigate(
            NotasFragmentDirections.actionNotasFragmentToCriarNotaFragment(jsonNota)
        )
    }

    private fun criarAdapter() = NotasRecycler(notasPresenter.getListaNotas(), this)

    //estáticos
    companion object {

        fun newInstance(param1: String, param2: String) =
            NotasFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    //override NotasRecycler.OnNotaClickListener

    override fun onItemClick(nota: Nota) {
        navegarPara(Gson().toJson(nota))
    }

    //override View.OnClickListener

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.fab -> {
                EditBottomSheet().show(childFragmentManager, EditBottomSheet.CRIAR_NOTA)
            }
        }
    }

    override fun insiraNota(nota: Nota) {
        recyclerAdapter.insiraNota(nota)
    }

    override fun atualizaNota(nota: Nota) {
        recyclerAdapter.atualizaNota(nota)
    }
}

/**
 * Função usada para testes
 * @author Irineu A. Silva
 */
fun main(){


}