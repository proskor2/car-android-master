package store.dide.cifracar

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import kotlinx.android.synthetic.main.activity_finish_reg.*
import kotlinx.android.synthetic.main.activity_pin_reg.*
import kotlinx.android.synthetic.main.pin_fragment.*
import store.dide.cifracar.databinding.ActivityFinishRegBinding
import store.dide.cifracar.ui.pin.PinFragment

class FinishRegActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityFinishRegBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish_reg)


    }



}

