package com.neu.notasreactivex.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.neu.notasreactivex.model.Nota

class NotaViewModel : ViewModel() {

    var nota = MutableLiveData<Nota>()
    var listNota = MutableLiveData<MutableList<Nota>>()

    init {
        NotaViewModel.nota = nota
    }

    companion object{
        lateinit var nota : MutableLiveData<Nota>
    }
}
