package com.dh.secretdiary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.core.widget.addTextChangedListener
import com.dh.secretdiary.databinding.ActivityDiaryBinding

class DiaryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDiaryBinding
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initDetailEditText()
    }

    private fun initDetailEditText() {
        val detail = MyApp.prefs.getString("detail", "")
        binding.diaryEditText.setText(detail)

        val runnable = Runnable {
            MyApp.prefs.setString("detail", binding.diaryEditText.toString())
        }

        binding.diaryEditText.addTextChangedListener {
            Log.d("DiaryActivity", "text Changed :: $it")
            handler.removeCallbacks(runnable)
            handler.postDelayed(runnable, 500)
        }
    }
}