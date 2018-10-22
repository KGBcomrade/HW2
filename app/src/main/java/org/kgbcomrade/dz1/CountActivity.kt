package org.kgbcomrade.dz1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView

class CountActivity : AppCompatActivity() {

    var running = false
    var time : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_count)
        val numbers = findViewById<TextView>(R.id.numbers)
        val button = findViewById<Button>(R.id.button_timer)

        savedInstanceState?.run {
            running = getBoolean("RUNNING")
            time = getInt("TIME")
        }

        button.text = if(running) "Stop" else "Start"
        numbers.text = if(time == 0) "" else numToText(time)

        val cdt = object : CountDownTimer(999000 - time * 1000L, 1000){
            override fun onFinish() {
                button.text = "Start"
                running = false
            }

            override fun onTick(millisUntilFinished: Long) {
                time = ((1000000L - millisUntilFinished)/1000L).toInt()
                numbers.text = numToText(time)
            }

        }

        if(running)
            cdt.start()

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

    override fun onSaveInstanceState(outState: Bundle?) {
        outState!!.putInt("TIME", time)
        outState.putBoolean("RUNNING", running)
        super.onSaveInstanceState(outState)
    }
}
