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
import androidx.core.content.ContextCompat
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_nfcread.*
import store.dide.cifracar.databinding.ActivityNfcreadBinding
import store.dide.cifracar.arhive.ui.phone.LoginPhoneActivity
import store.dide.cifracar.db.App
import store.dide.cifracar.db.AppDB
import store.dide.cifracar.db.database.AppDatabase
import store.dide.cifracar.db.models.Status
import store.dide.cifracar.db.models.Tags
import kotlin.concurrent.thread


class NFCReadActivity : AppCompatActivity() {

   private lateinit var binding: ActivityNfcreadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNfcreadBinding.inflate(layoutInflater)
        setContentView(binding.root)




        runOnUiThread() {
            val tagsDao = App.instance?.databaseInstance?.tagsDao()
            val newTag = Tags("1","WGKLOWSC", "233432", Status.ACTIVE, "111", "29.03.2022", "29.03.2022", false )
            tagsDao?.insert(newTag)
        }

        val nfcAdapter = NfcAdapter.getDefaultAdapter(this)
        val nfcPendingIntent = PendingIntent.getActivity(
            this, 0,
            Intent(this, javaClass).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0
        )

        binding.buttonConnectnfc.setOnClickListener() {
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

        binding.imageButtonScanqr.setOnClickListener(){

        }

        binding.buttonDemo.setOnClickListener(){
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.textIhavecard.setOnClickListener(){
            Toast.makeText(this, "You have cards", Toast.LENGTH_LONG).show()
        }

    }

    override fun onResume() {
        super.onResume()


//        Toast.makeText(this, "Resume", Toast.LENGTH_LONG).show()
//        val tagsDao = database.tagsDao()
//        val listTags = tagsDao?.getAll()
//        if (!listTags.isNullOrEmpty()) {
//            binding.textIhavecard.visibility = View.VISIBLE
//        } else {
//            binding.textIhavecard.visibility = View.GONE
//        }
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
