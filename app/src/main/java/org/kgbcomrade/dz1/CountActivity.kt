package org.kgbcomrade.dz1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView

class CountActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_count)
        val numbers = findViewById<TextView>(R.id.numbers)
        val button = findViewById<Button>(R.id.button_timer)
        var running = false
        val cdt = object : CountDownTimer(1000000, 1000){
            override fun onFinish() {
                button.text = "Start"
                running = false
            }

            override fun onTick(millisUntilFinished: Long) {
                numbers.text = ((1000000 - millisUntilFinished)/1000).toString()
            }

        }

        button.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                if(!running)
                    cdt.start()
                else
                    cdt.cancel()
                button.text = (if (running) "Start" else "Stop")
                running = !running
            }

        })


    }
}
