package com.example.livedata_viewmodel_tutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.livedata_viewmodel_tutorial.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val TAG = "로그"
    }

    lateinit var binding: ActivityMainBinding

    lateinit var myNumberViewModel: MyNumberViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myNumberViewModel = ViewModelProvider(this).get(MyNumberViewModel::class.java)

        myNumberViewModel.currentValue.observe(this, Observer {
            Log.d(TAG, "MainActivty - myNumberViewModel - currentValue 라이브 데이터 값 변경 : $it ")
            binding.numberTextview.text = it.toString()
        })
        //리스너 연결
        binding.plusBtn.setOnClickListener(this)
        binding.minusBtn.setOnClickListener(this)
    }

    //클릭
    override fun onClick(view: View?) {
        val userInput = binding.numberInputEdittext.text.toString().toInt()

        when (view) {
            binding.plusBtn ->
                myNumberViewModel.updateValue(actionType = ActionType.PlUS, userInput)
            binding.minusBtn ->
                myNumberViewModel.updateValue(actionType = ActionType.MINUS, userInput)
        }
    }
}