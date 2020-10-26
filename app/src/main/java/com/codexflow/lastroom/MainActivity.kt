package com.codexflow.lastroom


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codexflow.lastroom.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    private val newWordActivityRequestCode = 1
    private lateinit var userviewm : UserViewModel
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userviewm = ViewModelProvider(this).get(UserViewModel::class.java)

        activityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )


        val recyclerView = activityMainBinding.recyclerview
        val adapter = UserListAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

    /*    activityMainBinding.something =userviewm
        activityMainBinding.setVariable(1,userviewm)
        */


      userviewm.allstore.observe(this, Observer { words ->
          words?.let { adapter.setWords(it) }
      })


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.let { data ->
                val word = User("inanç", "yilmaz", 465)

                userviewm.insert(word)
                Unit
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }


}
