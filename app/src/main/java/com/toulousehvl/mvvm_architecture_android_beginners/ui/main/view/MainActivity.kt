package com.toulousehvl.mvvm_architecture_android_beginners.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.toulousehvl.mvvm_architecture_android_beginners.R
import com.toulousehvl.mvvm_architecture_android_beginners.data.api.ApiHelper
import com.toulousehvl.mvvm_architecture_android_beginners.data.api.ApiServiceImpl
import com.toulousehvl.mvvm_architecture_android_beginners.data.model.User
import com.toulousehvl.mvvm_architecture_android_beginners.databinding.ActivityMainBinding
import com.toulousehvl.mvvm_architecture_android_beginners.ui.main.adapter.MainAdapter
import com.toulousehvl.mvvm_architecture_android_beginners.ui.main.viewmodel.MainViewModel
import com.toulousehvl.mvvm_architecture_android_beginners.ui.main.viewmodel.ViewModelFactory
import com.toulousehvl.mvvm_architecture_android_beginners.utils.Status

import kotlinx.android.synthetic.main.activity_main.*

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: MainAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //setContentView(R.layout.activity_main)
        setupViewModel()

/*
        this.binding.btnGenerateInsult.setOnClickListener {
            Log.e(TAG, "button")

            setupObserver()

        }*/
    }

    /*
        private fun setupUI(){
            recyclerView.layoutManager = LinearLayoutManager(this)
            adapter = MainAdapter(arrayListOf())
            recyclerView.addItemDecoration(
                DividerItemDecoration(
                    recyclerView.context,
                    (recyclerView.layoutManager as LinearLayoutManager).orientation
                )
            )
            recyclerView.adapter = adapter
        }
    */
    private fun setupObserver() {
        mainViewModel!!.getInsults().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let { insult -> binding.insultWord = insult }
                    Log.e(TAG, "status SUCCESS : ${insulttxt.toString()}")
                }
                Status.LOADING -> {
                    Log.e(TAG, "status LOADING")
                    progressBar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }
/*    private fun setupObserver() {
        mainViewModel.getUsers().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let { users -> renderList(users) }
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }*/

    private fun renderList(users: List<User>) {
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }

    //initialise ViewModel
    private fun setupViewModel() {
        binding.mainViewModel = ViewModelProviders.of(
            this, ViewModelFactory(ApiHelper(ApiServiceImpl()))
        ).get(MainViewModel::class.java)
        Log.e(TAG, "setupViewModel")
    }
}