package dev.panwar.a7minutesworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.FrameLayout
import android.widget.Toast
import dev.panwar.a7minutesworkout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

//    after making a change in gradle file....XmlfileNAmeBinding is automatically created.
    private var binding:ActivityMainBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        write before Setting content view
        binding= ActivityMainBinding.inflate(layoutInflater)
//        it binds root parent of xml file
        setContentView(binding?.root)
//        setContentView(R.layout.activity_main)

//        val flStartButton: FrameLayout=findViewById(R.id.flStart)
//        USing View Binding
        binding?.flStart?.setOnClickListener {
           val intent=Intent(this,ExerciseActivity::class.java)
            startActivity(intent)
        }

        binding?.flBMI?.setOnClickListener {
            val intent=Intent(this,BMIActivity::class.java)
            startActivity(intent)
        }

        binding?.flHistory?.setOnClickListener {
            val intent=Intent(this,HistoryActivity::class.java)
            startActivity(intent)
        }


    }


// must Create this method ...this is important to prevent memory leak after binding is used
    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }
}