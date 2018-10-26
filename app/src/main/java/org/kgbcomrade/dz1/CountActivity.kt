package org.kgbcomrade.dz1

import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.TextView

class CountActivity : AppCompatActivity() {

    var running = false
    var time : Int = 0
    lateinit var cdt : CountDownTimer

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

        cdt = object : CountDownTimer(999000 - time * 1000L, 1000){
            override fun onFinish() {
                button.text = "Start"
                running = false
            }

            override fun onTick(millisUntilFinished: Long) {
                time = ((1000000L - millisUntilFinished)/1000L).toInt()
                numbers.text = numToText(time)
                Log.i("La logue", "(${hashCode()}): time: $time")
            }

        }



        if(running)
            cdt.start()

        button.setOnClickListener {
            if(!running)
                cdt.start()
            else {
                cdt.cancel()
                cdt = object : CountDownTimer(999000, 1000){
                    override fun onFinish() {
                        button.text = "Start"
                        running = false
                    }

                    override fun onTick(millisUntilFinished: Long) {
                        time = ((1000000L - millisUntilFinished)/1000L).toInt()
                        numbers.text = numToText(time)
                        Log.i("La logue", "(${hashCode()}): time: $time")
                    }

                }
            }
            button.text = (if (running) "Start" else "Stop")
            running = !running
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        cdt.cancel()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putInt("TIME", time)
        outState?.putBoolean("RUNNING", running)
        super.onSaveInstanceState(outState)
    }
}
