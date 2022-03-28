package store.dide.cifracar

import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.MediaController
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity() {
    var stopPosition: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        val sharedPreferences = getSharedPreferences("Start", Context.MODE_PRIVATE)
        val sharedEditor = sharedPreferences.edit()
// load video
        startVideo()
// buttons handler
        button_start.setOnClickListener() {
            hideButtons()
            sharedEditor.putInt("click", 1)
            sharedEditor.apply()
            Handler().postDelayed(
                { startActivity(Intent(this, NFCReadActivity::class.java)) },
                900
            )
        }
        button_demo.setOnClickListener() {
            hideButtons()
            sharedEditor.putInt("click", 1)
            sharedEditor.apply()
            startActivity(Intent(this, MainActivity::class.java)) // потом нахрен убрать
        }
    }

    override fun onResume() {
        super.onResume()
        val sharedPreferences = getSharedPreferences("Start", Context.MODE_PRIVATE)
        if (sharedPreferences.getInt("click", 0) == 1) {
            videoView.visibility = View.GONE
            image_garagelogo.postDelayed({
                run() {
                    image_garagelogo.visibility = View.VISIBLE
                    image_garagelogo.animate().alpha(1.0f).setDuration(1000)
                }
            }, 400)
            showButtons()
        } else {
            startVideo()
        }
    }

    fun startVideo() {
// load and play video in videoview
        val videoUri = Uri.parse("android.resource://${packageName}/${R.raw.startvideo3}")
        videoView.setMediaController(null)
        videoView.setAudioFocusRequest(AudioManager.AUDIOFOCUS_NONE)
        videoView.setVideoURI(videoUri)
        videoView.requestFocus()
        videoView.start()
        videoView.setOnCompletionListener {
            showButtons()
        }
    }

    fun showButtons() {
        image_textlogo.postDelayed({
            run() {
                image_textlogo.visibility = View.VISIBLE
                image_textlogo.animate().alpha(1.0f).setDuration(1000)
            }
        }, 200)

        button_start.postDelayed(Runnable {
            run() {
                button_start.visibility = View.VISIBLE
                button_start.animate().alpha(1.0f).setDuration(1000)
            }
        }, 600)
        button_demo.postDelayed(Runnable {
            run() {
                button_demo.visibility = View.VISIBLE
                button_demo.animate().alpha(1.0f).setDuration(1000)
            }
        }, 800)
    }

    fun hideButtons() {
        image_textlogo.postDelayed({
            run() {
                image_textlogo.visibility = View.GONE
                image_textlogo.animate().alpha(0.1f).setDuration(1000)
            }
        }, 800)

        image_garagelogo.postDelayed({
            run() {
                image_garagelogo.visibility = View.GONE
                image_garagelogo.animate().alpha(0.1f).setDuration(1000)
            }
        }, 600)

        button_start.postDelayed(Runnable {
            run() {
                button_start.visibility = View.GONE
                button_start.animate().alpha(0.1f).setDuration(1000)
            }
        }, 400)
        button_demo.postDelayed(Runnable {
            run() {
                button_demo.visibility = View.GONE
                button_demo.animate().alpha(0.1f).setDuration(1000)
            }
        }, 200)
    }
}
