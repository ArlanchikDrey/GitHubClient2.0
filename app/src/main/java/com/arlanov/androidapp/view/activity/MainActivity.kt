package com.arlanov.androidapp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arlanov.androidapp.R
import com.arlanov.androidapp.view.fragments.ListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(R.id.fragments_container, ListFragment())
                .commit()
        }
    }
}
