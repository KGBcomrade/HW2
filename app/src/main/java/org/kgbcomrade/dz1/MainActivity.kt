package org.kgbcomrade.dz1

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var cdt : CountDownTimer
    var time = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ci = Intent(this, CountActivity::class.java)

        if (savedInstanceState != null) time = savedInstanceState.getInt("TIME").toLong()

        cdt = object : CountDownTimer(2000 - time, 1000) {

            override fun onFinish() {
                startActivity(ci)
                finish()
            }

            override fun onTick(millisUntilFinished: Long) {
                time = 2000L - millisUntilFinished
            }
        }.start()
    }


    override fun onDestroy() {
        super.onDestroy()
        cdt.cancel()
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        outState?.putInt("TIME", time.toInt())
        super.onSaveInstanceState(outState, outPersistentState)

    }

}
