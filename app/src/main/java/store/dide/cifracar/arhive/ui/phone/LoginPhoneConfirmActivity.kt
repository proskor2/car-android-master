package store.dide.cifracar.arhive.ui.phone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import store.dide.cifracar.R
import android.content.Intent
import kotlinx.android.synthetic.main.phone_view.*
//import store.dide.cifracar.managers.AuthorizationManager
import store.dide.cifracar.arhive.ui.pin.PinRegActivity
import android.os.CountDownTimer

class LoginPhoneConfirmActivity : AppCompatActivity() {

//    private lateinit var authMan:AuthorizationManager
//    private lateinit var timer : CountDownTimer
//    private val maxTimeDelta=40
//    private var phoneNumber:String=""
//    private var stepTime=maxTimeDelta
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login_phone_confirm)
//
//        phoneNumber = intent.getStringExtra("phoneNumber").toString()
//
//        editTextPhone2.setHint("Код подтверждения")
//        textView9.setText("На указанный номер телефона $phoneNumber было отправлено СМС с кодом подтверждения")
//
//        authMan= AuthorizationManager(this)
//        authMan.VerifyFirebaseAuthPhone(phoneNumber.toString())
//
//        button2.setOnClickListener() {
//            if(editTextPhone2.text.toString().length>0) {
//                if(authMan.verifyPhoneNumberWithCode(authMan.storedVerificationId,editTextPhone2.text.toString()))
//                    editTextPhone2.error = "Код указан неверно"
//            }
//        }
//        val stepDown = stepTime*1000
//        timer = object : CountDownTimer(stepDown.toLong(), 1000) {
//            override fun onTick(millisUntilFinished: Long) {
//
//                var millisUntilFinishedTemp=millisUntilFinished/1000
//                textView7.setText("Повторная отправка, через 00:$millisUntilFinishedTemp")
//
//                if(authMan.GetUser()!=null){
//                    LoadPinRegActivty()
//                }
//            }
//
//            override fun onFinish() {
//                textView7.setText(R.string.Resend_Phone_Code)
//            }
//        }
//        timer.start()
//
//        textView7.setOnClickListener(){
//            if(textView7.text.toString()==R.string.Resend_Phone_Code.toString())
//               timer.start()
//        }
//
//    }
//
//    private fun LoadPinRegActivty(){
//        timer.cancel()
//        val intent = Intent(this, PinRegActivity::class.java)
//        startActivity(intent)
//    }
}
