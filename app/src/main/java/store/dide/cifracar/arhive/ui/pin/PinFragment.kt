package store.dide.cifracar.arhive.ui.pin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_pin_reg.*
import kotlinx.android.synthetic.main.nfcalert.*
import kotlinx.android.synthetic.main.pin_fragment.*
import store.dide.cifracar.R


class PinFragment : Fragment() {

    companion object {
        fun newInstance() = PinFragment()
    }

    private lateinit var viewModel: PinViewModel
    var pin:String=""
    val maxLength=4
    var addPinSymbol: () -> Unit = {}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rot = inflater!!.inflate(R.layout.pin_fragment, container, false)

        (rot?.findViewById(R.id.textView77) as TextView)?.setOnClickListener(){AddText(1)}
        (rot?.findViewById(R.id.textView73) as TextView)?.setOnClickListener(){AddText(2)}
        (rot?.findViewById(R.id.textView81) as TextView)?.setOnClickListener(){AddText(3)}
        (rot?.findViewById(R.id.textView78) as TextView)?.setOnClickListener(){AddText(4)}
        (rot?.findViewById(R.id.textView74) as TextView)?.setOnClickListener(){AddText(5)}
        (rot?.findViewById(R.id.textView82) as TextView)?.setOnClickListener(){AddText(6)}
        (rot?.findViewById(R.id.textView79) as TextView)?.setOnClickListener(){AddText(7)}
        (rot?.findViewById(R.id.textView75) as TextView)?.setOnClickListener(){AddText(8)}
        (rot?.findViewById(R.id.textView83) as TextView)?.setOnClickListener(){AddText(9)}
        (rot?.findViewById(R.id.textView76) as TextView)?.setOnClickListener(){AddText(0)}

        (rot?.findViewById(R.id.textView84) as TextView)?.setOnClickListener(){
            if(pin.length>0) {
                if (pin.length == 1) {
                    textView14.text = ""
                    textView14.setBackgroundResource(android.R.drawable.presence_invisible)
                }
                if (pin.length == 2) {
                    textView28.text = ""
                    textView28.setBackgroundResource(android.R.drawable.presence_invisible)
                }
                if (pin.length == 3) {
                    textView29.text = ""
                    textView29.setBackgroundResource(android.R.drawable.presence_invisible)
                }
                if (pin.length == 4) {
                    textView30.text = ""
                    textView30.setBackgroundResource(android.R.drawable.presence_invisible)
                }
                pin = pin.substring(0, pin.length - 1)
            }
        }

        return rot
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PinViewModel::class.java)

    }

    fun AddText(number:Int){
        if(maxLength<=pin.length)
            return

        if(pin.length==0) {
            textView14.text = number.toString()
            textView14.setBackgroundResource(R.drawable.ellipse)
        }
        if(pin.length==1) {
            textView28.text = number.toString()
            textView28.setBackgroundResource(R.drawable.ellipse)
        }
        if(pin.length==2) {
            textView29.text = number.toString()
            textView29.setBackgroundResource(R.drawable.ellipse)
        }
        if(pin.length==3) {
            textView30.text = number.toString()
            textView30.setBackgroundResource(R.drawable.ellipse)
        }
        pin += number.toString()

        addPinSymbol()
    }

    fun ClearPage(){
        pin=""
        textView14.text = ""
        textView28.text=""
        textView29.text=""
        textView30.text=""
        textView55.text=""
        textView14.setBackgroundResource(android.R.drawable.presence_invisible)
        textView28.setBackgroundResource(android.R.drawable.presence_invisible)
        textView29.setBackgroundResource(android.R.drawable.presence_invisible)
        textView30.setBackgroundResource(android.R.drawable.presence_invisible)
    }
}