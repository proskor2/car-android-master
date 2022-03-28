package store.dide.cifracar

import android.app.PendingIntent
import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NfcAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_nfcread.*
import store.dide.cifracar.ui.phone.LoginPhoneActivity


class NFCReadActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nfcread)

        val nfcAdapter = NfcAdapter.getDefaultAdapter(this)
        val nfcPendingIntent = PendingIntent.getActivity(
            this, 0,
            Intent(this, javaClass).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0
        )

        button_connectnfc.setOnClickListener() {
            if (nfcAdapter == null) {
                val builder = AlertDialog.Builder(this)
                builder.setView(R.layout.nfcnone)
                builder.create()
                builder.show()
            } else if (!nfcAdapter.isEnabled) {
                val builder = AlertDialog.Builder(this)
                builder.setView(R.layout.nfcdisabledalert)
                builder.create()
                builder.show()
            } else if (nfcAdapter.isEnabled) {
                nfcAdapter?.enableForegroundDispatch(this, nfcPendingIntent, null, null)
                val builder = AlertDialog.Builder(this)
                builder.setView(R.layout.nfcalert)
                builder.create()
                builder.show()

                val formPhone=Intent(this, LoginPhoneActivity::class.java)
                startActivity(formPhone)
            } else {
                Toast.makeText(this, "Ошибка устройства NFC", Toast.LENGTH_LONG).show()
            }
        }



    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent != null) processIntent(intent)
    }

    private fun processIntent(checkIntent: Intent) {
        val rawMessages = checkIntent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)
        if (!rawMessages.isNullOrEmpty()) {
            val ndefMsg = rawMessages[0] as NdefMessage
            if (!ndefMsg.records.isNullOrEmpty()) {
                val ndefRecord = ndefMsg.records[0]
                if (ndefRecord.toUri() != null) {
                    Toast.makeText(this, "${ndefRecord.toUri()}", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Ошибка формата метки", Toast.LENGTH_LONG).show()
                }
            }
        }
    }


}
