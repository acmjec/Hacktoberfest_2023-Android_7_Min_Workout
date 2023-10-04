package dev.panwar.a7minutesworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView


class sp_screen : AppCompatActivity() {
        private val splashTimeOut: Long = 3000 // 3 seconds

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_sp_screen)

            // Add any animation code here

            Handler().postDelayed({
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, splashTimeOut)
            val splashImageView = findViewById<ImageView>(R.id.splashImageView)
            splashImageView.alpha = 0f
            splashImageView.animate().setDuration(1500).alpha(1f).withEndAction {
                // Animation complete
            }

        }
    }

