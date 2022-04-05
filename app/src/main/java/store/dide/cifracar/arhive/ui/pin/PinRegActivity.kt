package store.dide.cifracar.arhive.ui.pin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_pin_reg.*
import kotlinx.android.synthetic.main.pin_fragment.*
import store.dide.cifracar.arhive.ui.FinishRegActivity
import store.dide.cifracar.R


class PinRegActivity : AppCompatActivity() {

    private val temppin="1234"
    private var firstPin:String=""
    private var step=1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin_reg)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title = "Регистрация"
        }
        textView13.text="Введите пароль"



        step = intent.getStringExtra("step")?.toInt() ?: 1

        val mainFragment: PinFragment = PinFragment()
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainerView, mainFragment)
            .commit()
        mainFragment.addPinSymbol={
            when(step){
                1-> {
                    if(mainFragment.pin==temppin) {
                        step = 2
                        firstPin=mainFragment.pin
                        textView13.text="Повторите пароль"
                        mainFragment.ClearPage()
                    }
                }
                2->{
                    if(mainFragment.pin==temppin){
                        step = 1
                        mainFragment.ClearPage()
                        val intent = Intent(this, FinishRegActivity::class.java)
                        startActivity(intent)
                    }
                    if(mainFragment.pin!=temppin && mainFragment.pin.length==temppin.length){
                        mainFragment.textView55.text="Пароли не совпадают"
                        mainFragment.ClearPage()
                    }
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onSupportNavigateUp()
        onBackPressed()
        return true
    }
}