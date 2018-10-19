package org.kgbcomrade.dz1

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ci = Intent(this, CountActivity::class.java)

        object : CountDownTimer(2000, 1000) {

            override fun onFinish() {
                startActivity(ci)
                finish()
            }

            override fun onTick(millisUntilFinished: Long) {

            }
        }.start()
    }

}
