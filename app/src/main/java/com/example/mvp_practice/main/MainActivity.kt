package com.example.mvp_practice.main


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.mvp_practice.R
import com.example.mvp_practice.models.User
import com.example.mvp_practice.databinding.ActivityMainBinding
import com.example.mvp_practice.fragments.LineChartFrag
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    var presenter: MainPresenter = MainPresenter()

    lateinit var binding: ActivityMainBinding

    lateinit var userData: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()

        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,
            R.layout.activity_main
        )
        binding.mData = userData
        binding.mView = this
    }

    override fun initView() {
        userData = User("1", "Jerry", "176", "66")
    }

    override fun toPresenter() {
        // DataBinding 顯示資料於TextView (改:getBinding，不要重新new)

        // 將EditView輸入值傳入 userData: User
        userData = User(
            mId.text.toString(),
            mName.text.toString(),
            mHeight.text.toString(),
            mWeight.text.toString()
        )

        binding.mData = userData

        // 對 MainPresenter 送出資料
        presenter.setData(userData)

        // 每送出一筆資料，重新繪製新的Fragment
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.main_content_frame, LineChartFrag())
            .commit()

        Log.d(javaClass.simpleName, userData.toString())
    }
}

