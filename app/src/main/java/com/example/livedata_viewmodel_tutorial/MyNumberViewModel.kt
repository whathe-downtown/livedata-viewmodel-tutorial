package com.example.livedata_viewmodel_tutorial

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class ActionType{
    PlUS, MINUS
}

class MyNumberViewModel : ViewModel(){
    companion object{
        const val TAG = "로그"
    }
    //뮤터블 라이브 데이터 - 수정 가능한 녀석
    //라이브 데이터 - 값 변경이 안됩니다.
    
    // 내부에서 설정하는 자료형은 뮤터블로
    // 변경가능하도록 설정 
    private val _currentValue = MutableLiveData<Int>()

    val currentValue: LiveData<Int>
     get() = _currentValue
    
    //초기값 설정
    init {
        Log.d(TAG, "MyNumberViewmodel - 새엇ㅇ자 호출: ")
        _currentValue.value = 0
    }

    fun updateValue(actionType: ActionType, input:Int){
        when(actionType){
            ActionType.PlUS ->
                _currentValue.value = _currentValue.value?.plus(input)
            ActionType.MINUS ->
                _currentValue.value =_currentValue.value?.minus(input)
        }
    }
}