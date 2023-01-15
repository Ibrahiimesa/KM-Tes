package com.esa.tes.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.esa.kmtes.adapter.UserAdapter
import com.esa.tes.R
import com.esa.tes.adapter.LoadingStateAdapter
import com.esa.tes.databinding.ActivityThirdScreenBinding
import com.esa.tes.ui.viewmodel.ThirdScreenViewModel
import com.esa.tes.utils.ViewModelFactory

class ThirdScreen : AppCompatActivity() {

    private lateinit var binding: ActivityThirdScreenBinding
    private lateinit var userAdapter: UserAdapter
    private lateinit var thirdScreenViewModel: ThirdScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        setupViewModel()
        setupView()
        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    private fun setupView() {
        userAdapter = UserAdapter()
        binding.rvUser.adapter = userAdapter.withLoadStateFooter(
            footer = LoadingStateAdapter{
                userAdapter.retry()
            }
        )
        binding.rvUser.layoutManager = LinearLayoutManager(this)
        thirdScreenViewModel.getUsers().observe(this){
            userAdapter.submitData(lifecycle, it)
        }
    }

    private fun setupViewModel() {
        val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
        thirdScreenViewModel = ViewModelProvider(this, factory)[ThirdScreenViewModel::class.java]
    }
}