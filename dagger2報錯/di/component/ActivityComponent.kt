package com.example.mvp_practice.di.component

import com.example.mvp_practice.ui.main.MainActivity
import com.example.mvp_practice.di.module.ActivityModule
import dagger.Component

@Component(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)

}