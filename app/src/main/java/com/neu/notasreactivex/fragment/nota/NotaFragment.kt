package com.neu.notasreactivex.fragment.nota

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.neu.notasreactivex.R
import com.neu.notasreactivex.activity.MainActivity
import com.neu.notasreactivex.fragment.edit.EditBottomSheet
import kotlinx.android.synthetic.main.fragment_nota.*

class NotaFragment : Fragment(), NotaView, View.OnClickListener {

    private lateinit var notaPresenter : NotaPresenter

    //override Fragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        notaPresenter = NotaPresenterImpl(this, mainActivity().getLiveData())
        notaPresenter.getNotaFromArgs(requireArguments())
    }

    private fun mainActivity() = requireActivity() as MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_nota, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notaPresenter.setarDados()
        notaPresenter.setObserve(viewLifecycleOwner)

        editBtn.setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("NotaFragment", "onDestroy")
    }

    //override NotaView

    override fun setTitulo(titulo: String) {
        this.titulo.text = titulo
    }

    override fun setDescricao(descricao: String) {
        this.descricao.text = descricao
        if (descricao.isEmpty())
            this.descricao.visibility = View.GONE
        else
            this.descricao.visibility = View.VISIBLE
    }

    override fun setNota(nota: String) {
        this.nota.text = nota
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.editBtn -> {
                EditBottomSheet(notaPresenter.getNotaJson()).show(
                    childFragmentManager, EditBottomSheet.EDITAR_NOTA
                )
            }
        }
    }
}