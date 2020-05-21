package com.example.mvp_practice.main

import android.util.Log
import com.example.mvp_practice.models.User

// 宣告靜態變數
val BMI_value = mutableListOf<Float>()

class MainPresenter :
    MainContract.Presenter<MainContract.View> {
    val Heights = mutableListOf<Float>()
    val Weights = mutableListOf<Float>()

    // 獲得Data，調用calcBMI
    override fun setData(data: User) {
        // 多宣告
        val (user_id, user_name, user_height, user_weight) = data

//        IDs.plus(user_id.toInt())
        Heights += user_height.toFloat()
        Weights += user_weight.toFloat()

        Log.d("setData", Heights.toString())

        calcBMI(Heights, Weights, Heights.size - 1)
    }

    // 計算BMI值
    override fun calcBMI(heights: MutableList<Float>, weights: MutableList<Float>, index: Int) {
        BMI_value += (weights[index] / (heights[index] * heights[index] / 10000))

        Log.d("calcBMI", BMI_value.toString())
    }
}