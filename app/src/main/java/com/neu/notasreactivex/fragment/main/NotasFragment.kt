package com.neu.notasreactivex.fragment.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.neu.notasreactivex.R
import com.neu.notasreactivex.activity.MainActivity
import com.neu.notasreactivex.activity.NotaViewModel
import com.neu.notasreactivex.adapter.NotasRecycler
import com.neu.notasreactivex.fragment.edit.EditBottomSheet
import com.neu.notasreactivex.model.Nota
import kotlinx.android.synthetic.main.fragment_notas.*


class NotasFragment : Fragment(), NotasView, NotasRecycler.OnNotaClickListener,
    View.OnClickListener {

    private lateinit var notasPresenter: NotasPresenter
    private lateinit var recyclerAdapter: NotasRecycler

    //override Fragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        notasPresenter = NotasPresenterImpl(requireContext(), this, NotaViewModel.nota)

        Log.d("NotasFragment", "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        notasPresenter.setObserve(viewLifecycleOwner)

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
        Log.d("NotasFragment", "onDestroy")
    }

    //membros

    private fun mainActivity() = requireActivity() as MainActivity

    private fun navegarPara(jsonNota: String) {
        findNavController().navigate(
            NotasFragmentDirections.actionNotasFragmentToCriarNotaFragment(jsonNota)
        )
    }

    private fun criarAdapter() = NotasRecycler(notasPresenter.getListaNotas(), onNotaClickListener = this)

    //override NotasRecycler.OnNotaClickListener

    override fun onItemClick(nota: Nota) {
        navegarPara(Gson().toJson(nota))
    }

    //override View.OnClickListener

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.fab -> {
                EditBottomSheet().show(
                    childFragmentManager,
                    EditBottomSheet.CRIAR_NOTA
                )
            }
        }
    }

    override fun insiraNota(nota: Nota) {
        recyclerAdapter.insiraNota(nota)
    }

    override fun atualizaNota(nota: Nota) {
        recyclerAdapter.atualizaNota(nota)
    }

    override fun setListaNotas(value: MutableList<Nota>) {

    }
}

/**
 * Função usada para testes
 * @author Irineu A. Silva
 */
fun main() {


}