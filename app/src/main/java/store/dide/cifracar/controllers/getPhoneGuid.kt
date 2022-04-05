package store.dide.cifracar.controllers

import android.content.Context
import android.provider.Settings
import androidx.core.content.ContextCompat

class getPhoneGuid(private val context: Context) {

    fun getGuid(): String{
        val guid = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        return guid
    }


}