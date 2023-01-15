package com.esa.tes.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.esa.tes.R
import com.esa.tes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        supportActionBar?.hide()

        binding.btnNext.setOnClickListener(this)
        binding.btnCheck.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.btn_next -> {
                val value = binding.tvInputName
                val moveIntent = Intent(this@MainActivity, SecondScreen::class.java).apply {
                    putExtra(SecondScreen.RESULT_NAME, value.text.toString())
                }
                startActivity(moveIntent)
            }
            R.id.btn_check -> {
                val palindrome = binding.tvInputPalindrome.text.toString()
                if (palindrome.isNotEmpty()){
                    val result = isPalindrome(palindrome)
                    Toast.makeText(this, "$result palindrome", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "Not palindrome", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun isPalindrome(text: String):Boolean{
        val text2 = text.replace(" ", "")
        val result = text2.reversed()

        return text2 == result
    }
}