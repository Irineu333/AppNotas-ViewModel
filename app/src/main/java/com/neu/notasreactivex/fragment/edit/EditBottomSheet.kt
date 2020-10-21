package com.neu.notasreactivex.fragment.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.neu.notasreactivex.R
import kotlinx.android.synthetic.main.edit_bottom_sheet.*


class EditBottomSheet(notaJson: String? = null) : BottomSheetDialogFragment(), EditView,
    View.OnClickListener {

    var editPresenter: EditPresenter = EditPresenterImpl(notaJson, this)

    //override Fragment()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.edit_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editPresenter.setarDados()
        setTituloBottomSheet()

        //setar listeners
        goneBtn.setOnClickListener(this)
        exitBtn.setOnClickListener(this)
    }

    //membros

    private fun setTituloBottomSheet() {
        tituloBottomSheet.text = tag
    }

    //override EditView

    override fun setTitulo(titulo: String) {
        editTitulo.setText(titulo)
    }

    override fun setDescricao(descricao: String) {
        editDescricao.setText(descricao)
    }

    override fun setNota(nota: String) {
        editNota.setText(nota)
    }

    override fun setTextTerminarBtn(text: String) {
        goneBtn.text = text
    }

    //View.OnClickListener

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.goneBtn ->{
                editPresenter.input(
                    editTitulo.text.toString(),
                    editDescricao.text.toString(),
                    editNota.text.toString()
                )
            }
            R.id.exitBtn ->{
                //
            }
        }
        dismiss()
    }

    companion object {
        const val CRIAR_NOTA = "Criar Nota"
        const val EDITAR_NOTA = "Editar Nota"
    }
}