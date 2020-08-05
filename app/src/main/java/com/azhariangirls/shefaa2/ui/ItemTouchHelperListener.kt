package com.azhariangirls.shefaa2.ui

import android.content.Context

interface ItemTouchHelperListener {

    fun createDialog(context: Context?, position: Int)
}