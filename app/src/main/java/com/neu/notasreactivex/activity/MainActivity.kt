package com.neu.notasreactivex.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.neu.notasreactivex.R
import com.neu.notasreactivex.model.Nota
import io.reactivex.rxjava3.core.Observable

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}