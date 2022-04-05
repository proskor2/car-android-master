package store.dide.cifracar.arhive.ui.pin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_pin_auth.*
import kotlinx.android.synthetic.main.pin_fragment.*
import store.dide.cifracar.arhive.ui.FinishRegActivity
import store.dide.cifracar.R

class PinAuthActivity : AppCompatActivity() {

    private val temppin = "1234"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin_auth)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title = "Вход"
        }
        textView10.text = "Введите пароль"

        val mainFragment: PinFragment = PinFragment()
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainerView2, mainFragment)
            .commit()
        mainFragment.addPinSymbol = {
            if (mainFragment.pin == temppin) {
                mainFragment.ClearPage()
                val intent = Intent(this, FinishRegActivity::class.java)
                startActivity(intent)
            }
            if (mainFragment.pin != temppin && mainFragment.pin.length == mainFragment.maxLength)
                mainFragment.textView55.text = "Введённый пароль не корректен"
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onSupportNavigateUp()
        onBackPressed()
        return true
    }
}