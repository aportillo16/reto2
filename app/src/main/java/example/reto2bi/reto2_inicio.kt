package example.reto2bi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.reto2bi.databinding.Reto2InicioBinding

class reto2_inicio: AppCompatActivity() {
    lateinit var binding: Reto2InicioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(false);

        binding = Reto2InicioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tokenSession = intent.getStringExtra("SESSION_TOKEN")

        binding.sessionTokenTextView.text = tokenSession
    }

    override fun onBackPressed() {
        finishAffinity()
    }
}