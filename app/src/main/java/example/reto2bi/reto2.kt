package example.reto2bi

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.reto2bi.R
import com.example.reto2bi.databinding.Reto2Binding

class reto2 : AppCompatActivity() {

    private lateinit var binding: Reto2Binding

    private var topAnim: Animation? = null
    private var bottomAnim: Animation? = null

    private lateinit var image: ImageView
    private lateinit var user: EditText
    private lateinit var pass: EditText
    private lateinit var ingr: TextView
    private lateinit var droidx: androidx.cardview.widget.CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = Reto2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)

        image = binding.imgViewWallet
        user = binding.edTUser
        pass = binding.edTPass
        ingr = binding.txtButtonIngress
        droidx = binding.cardvw

        image.setAnimation(topAnim)
        user.setAnimation(bottomAnim)
        pass.setAnimation(bottomAnim)
        ingr.setAnimation(bottomAnim)
        droidx.setAnimation(bottomAnim)

        val mDelay: Long = 2000
        Handler(Looper.getMainLooper()).postDelayed({
        }, mDelay)

        ingr.setOnClickListener {
            if(user.text.toString() != "" && pass.text.toString() != "")
                validaLogin(user.text.toString(), pass.text.toString())
            else
                showToast("No contiene texto...")
        }
    }

    private fun validaLogin(username: String, password: String){
        val sessionToken = api.local_api().validateUser(username, password)

        if(sessionToken != null){
            val intent = Intent(this, reto2_inicio::class.java).apply {
                putExtra("SESSION_TOKEN", sessionToken)
            }
            startActivity(intent)
        }else {
            showToast("Credenciales incorrectas...")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}