package store.dide.cifracar

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import store.dide.cifracar.databinding.ActivityTagStoreBinding

class ActivityTagStore : AppCompatActivity() {

    private lateinit var binding: ActivityTagStoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTagStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cardOzon.setOnClickListener(){
            val url = "https://www.ozon.ru/"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }

        binding.cardWildberries.setOnClickListener(){
            val url = "https://www.wildberries.ru/"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }

    }
}