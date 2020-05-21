package com.example.mvp_practice.main

import com.example.mvp_practice.models.User

open class MainContract {
    interface View {
        fun initView()

        fun toPresenter()
    }

    interface Presenter<v : View> {
        fun setData(data: User)

        fun calcBMI(heights: MutableList<Float>, weights: MutableList<Float>, arrLength: Int)
    }
}