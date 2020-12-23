package com.codexflow.lastroom.ui


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.codexflow.lastroom.R
import com.codexflow.lastroom.viewmodel.UserViewModel
import com.codexflow.lastroom.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var userviewm: UserViewModel
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userviewm = ViewModelProvider(this).get(UserViewModel::class.java)

        activityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )

        val recyclerView = activityMainBinding.recyclerview
        val adapter = UserListAdapter()
        recyclerView.adapter = adapter

        userviewm.allstore.observe(this, { users ->
            adapter.userlist = users
        })
    }
}
