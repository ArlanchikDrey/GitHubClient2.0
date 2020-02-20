package com.arlanov.androidapp.view.fragments

import android.content.Context
import androidx.fragment.app.Fragment
import com.arlanov.androidapp.view.activity.MainActivity

open class BaseFragment: Fragment() {
    protected lateinit var activity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)

        activity = context as MainActivity
    }

}