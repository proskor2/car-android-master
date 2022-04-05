package store.dide.cifracar.controllers

import android.content.Context
import android.view.View
import androidx.appcompat.app.AlertDialog

class alertDialog(private val context: Context) {

    fun showAlertDialog(resource: Int?, view: View?) {
        val builder = AlertDialog.Builder(context)
        if (resource == null) builder.setView(view) else builder.setView(resource)
        builder.create()
        builder.show()
    }
}