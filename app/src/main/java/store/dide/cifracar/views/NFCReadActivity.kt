package store.dide.cifracar.views

import android.app.PendingIntent
import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NfcAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.*
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import store.dide.cifracar.R
import store.dide.cifracar.databinding.ActivityNfcreadBinding
import store.dide.cifracar.arhive.ui.pin.PinAuthActivity
import store.dide.cifracar.arhive.ui.pin.PinRegActivity
import store.dide.cifracar.controllers.alertDialog
import store.dide.cifracar.db.database.AppDatabase
import store.dide.cifracar.models.tagState
import java.util.*
import kotlin.concurrent.thread


class NFCReadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNfcreadBinding
    private lateinit var phoneGUID: String
    private lateinit var tagGUID: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNfcreadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nfcAdapter = NfcAdapter.getDefaultAdapter(this)
        val nfcPendingIntent = PendingIntent.getActivity(
            this, 0,
            Intent(this, javaClass).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0
        )

        binding.buttonConnectnfc.setOnClickListener() {
            if (nfcAdapter == null) {
                alertDialog(this).showAlertDialog(R.layout.nfcnone, null)
            } else if (!nfcAdapter.isEnabled) {
                alertDialog(this).showAlertDialog(R.layout.nfcdisabledalert, null)
            } else if (nfcAdapter.isEnabled) {
                binding.buttonConnectnfc.visibility = View.GONE
                binding.cardProgressbar.visibility = View.VISIBLE
                nfcAdapter.enableForegroundDispatch(this, nfcPendingIntent, null, null)
                val view = LayoutInflater.from(this).inflate(R.layout.nfcalert, null)
                val imagegif = view.findViewById<ImageView>(R.id.imagegif)
                Glide.with(view).load(R.drawable.gifnfc).into(imagegif)
                val builder = AlertDialog.Builder(this)
                builder.setView(view)
                builder.create()
                builder.setOnDismissListener() {
                    binding.buttonConnectnfc.visibility = View.VISIBLE
                    binding.cardProgressbar.visibility = View.GONE
                }
                builder.show()


//                val formPhone=Intent(this, LoginPhoneActivity::class.java)
//                startActivity(formPhone)
            } else {
                Toast.makeText(this, "Ошибка устройства NFC", Toast.LENGTH_LONG).show()
            }
        }

        binding.imageButtonScanqr.setOnClickListener() {
            startActivity(Intent(this, QRActivity::class.java))
        }

        binding.buttonDemo.setOnClickListener() {
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.textIhavecard.setOnClickListener() {
            startActivity(Intent(this, PinAuthActivity::class.java))
        }

        binding.textNocards.setOnClickListener(){
            startActivity(Intent(this, ActivityTagStore::class.java))
        }


    }

    override fun onResume() {
        super.onResume()
        thread {
            val tagsDao = AppDatabase.getDatabase(this).tagsDao()
//            val newTag = Tags(1,"WGKLOWSC", "233432", "Status.ACTIVE", "111", "29.03.2022", "29.03.2022", false )
//            tagsDao?.insert(newTag)
//            val listTags = tagsDao?.getAll()
//            if (listTags.isNullOrEmpty()) {
//                binding.textIhavecard.visibility = View.GONE
//            } else {
//                binding.textIhavecard.visibility = View.VISIBLE
//            }
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
                if (ndefRecord.toUri().toString().contains("cifracar.online")) {
                    val tagURL = ndefRecord.toUri().toString()
                    tagGUID = tagURL.substringAfterLast("/")
                    Toast.makeText(this, "Метка CifraCar", Toast.LENGTH_LONG).show()
                    val state: tagState =
                        tagState.ACTIVE                                                                              // tag STATE
                    when (state){
                        tagState.ACTIVE -> startActivity(Intent(this, PinRegActivity::class.java))
                        tagState.FREE -> startActivity(Intent(this, MainActivity::class.java))          // переделать на экран VIN
                        tagState.TRANSFER -> startActivity(Intent(this, MainActivity::class.java))      // переделать на экран найденных авто
                        tagState.LOCK -> alertDialog(this).showAlertDialog(R.layout.tagerror, null)
                    }
                } else {
                    Toast.makeText(this, "Ошибка формата метки", Toast.LENGTH_LONG).show()
                }
            }
        }


    }


}
