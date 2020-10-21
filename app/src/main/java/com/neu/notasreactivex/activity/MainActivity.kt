package com.neu.notasreactivex.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.neu.notasreactivex.R
import com.neu.notasreactivex.model.Nota

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel : NotaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(NotaViewModel::class.java)
    }

    fun getLiveData() : MutableLiveData<Nota> = viewModel.nota
}