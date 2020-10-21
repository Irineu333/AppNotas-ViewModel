package com.neu.notasreactivex.fragment.nota

import android.os.Bundle

interface NotaPresenter {
    fun getNotaFromArgs(requireArguments: Bundle)
    fun getNotaJson() : String
}