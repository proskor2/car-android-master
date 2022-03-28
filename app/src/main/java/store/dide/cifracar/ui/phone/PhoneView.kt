package store.dide.cifracar.ui.phone

import android.content.Context
import android.widget.LinearLayout
import store.dide.cifracar.R

import android.view.LayoutInflater

class PhoneView(context: Context) : LinearLayout(context) {

    init {
        init(context)
    }

    private fun init(context: Context) {
        val inflater = context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.phone_view, this)

    }
}