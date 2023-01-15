package com.esa.tes.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.esa.tes.R
import com.esa.tes.databinding.ActivitySecondScreenBinding

class SecondScreen : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivitySecondScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val name = intent.getStringExtra(RESULT_NAME)

        binding.nameSec.text = name.toString()
        binding.btnChoose.setOnClickListener(this)
        binding.ivBack.setOnClickListener {
            finish()
        }

    }

    override fun onClick(v: View) {
        val moveIntent = Intent(this@SecondScreen, ThirdScreen::class.java)
        startActivity(moveIntent)
    }

    companion object {
        const val RESULT_NAME = "name"
    }
}