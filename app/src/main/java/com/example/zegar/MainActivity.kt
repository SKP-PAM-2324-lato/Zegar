package com.example.zegar

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var buttonStopwatch: Button
    private lateinit var buttonAlarm: Button
    private lateinit var buttonTimer: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        buttonStopwatch = findViewById(R.id.stopwatchButton)
        buttonTimer = findViewById(R.id.timerButton)
        buttonAlarm = findViewById(R.id.alarmButton)

        buttonStopwatch.setOnClickListener {
            val intent = Intent(Intent.ACTION_MAIN).apply {
                setClassName("com.google.android.deskclock", "com.android.deskclock.DeskClock")
                addCategory(Intent.CATEGORY_LAUNCHER)
            }

            if(intent.resolveActivity(packageManager) != null){
                startActivity(intent)
            }
        }

        buttonAlarm.setOnClickListener {
            val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
                putExtra(AlarmClock.EXTRA_HOUR, 3)
                putExtra(AlarmClock.EXTRA_MINUTES, 42)
                putExtra(AlarmClock.EXTRA_IS_PM, true)
                putExtra(AlarmClock.EXTRA_MESSAGE, "Obudź się!")

            }
            startActivity(intent)

        }

        buttonTimer.setOnClickListener {
            val intent = Intent(AlarmClock.ACTION_SET_TIMER).apply {
                putExtra(AlarmClock.EXTRA_LENGTH, 60)
                putExtra(AlarmClock.EXTRA_MESSAGE, "timer" )
                putExtra(AlarmClock.EXTRA_SKIP_UI, true)
            }

            startActivity(intent)
        }
    }
}