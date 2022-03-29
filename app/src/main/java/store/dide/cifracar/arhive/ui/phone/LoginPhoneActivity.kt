package store.dide.cifracar.arhive.ui.phone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import store.dide.cifracar.R
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import kotlinx.android.synthetic.main.phone_view.*
import store.dide.cifracar.arhive.ui.pin.PinAuthActivity
import store.dide.cifracar.arhive.ui.pin.PinRegActivity

class LoginPhoneActivity : AppCompatActivity() {

    private val mask="+0(000)000-00-00"
    private val maxLength=mask.length

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_default)

        editTextPhone2.setFilters(arrayOf<InputFilter>(LengthFilter(maxLength)))
        editTextPhone2.setHint("Номер телефона")
        textView9.setText(R.string.Information_by_phone)

        editTextPhone2.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?,
                                           p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?,
                                       p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                var res: String
                val reg = Regex("[^A-Za-z0-9 ]")
                res = reg.replace(p0.toString(), "")

                for (i in mask.indices) {
                    if (mask[i] != '0' && res.length > i) {
                        res = StringBuilder(res).insert(i, mask[i]).toString()
                    }
                }
                editTextPhone2.removeTextChangedListener(this)
                editTextPhone2.editableText.replace(0, editTextPhone2.text.length, res)
                editTextPhone2.setSelection(res.length)
                editTextPhone2.addTextChangedListener(this)
            }
        })

        button2.setOnClickListener() {
            if(editTextPhone2.text.toString().isValidPhoneNumber()) {
                val intent = Intent(this, LoginPhoneConfirmActivity::class.java)
                intent.putExtra("phoneNumber", editTextPhone2.text.toString())
                startActivity(intent)
            }
            else
            {
                editTextPhone2.error="Номер телефона указан неверно"
            }
        }
    }

    fun CharSequence?.isValidPhoneNumber():Boolean{
        if(this?.length !=maxLength)
            return false
        return !isNullOrEmpty() && Patterns.PHONE.matcher(this).matches()
    }

    fun String.addCharAtIndex(char: Char, index: Int) =
        StringBuilder(this).apply { insert(index, char) }.toString()
}
