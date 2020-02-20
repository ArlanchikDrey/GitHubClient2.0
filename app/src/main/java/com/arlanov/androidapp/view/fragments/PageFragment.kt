package com.arlanov.androidapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.arlanov.androidapp.R
import com.arlanov.androidapp.databinding.PageFragmentBinding

class PageFragment: Fragment() {

    companion object {
        const val TAG_REP_NAME = "TAG_REP_NAME"
        const val TAG_REP_WATCH = "TAG_REP_WATCH"

        fun newInstance(name: String, watch: Int): PageFragment {
            val fragment = PageFragment()

            val bundle = Bundle()
            bundle.putString(TAG_REP_NAME, name)
            bundle.putInt(TAG_REP_WATCH, watch)
            fragment.arguments = bundle

            return fragment
        }
    }

    private lateinit var view: PageFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = PageFragmentBinding.inflate(inflater, container, false)
        return view.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            val anim = AnimationUtils.loadAnimation(activity, R.anim.from)
            initView()
            initAnimation(anim)
        }
    }

    private fun initView() {
        val name  = arguments?.getString(TAG_REP_NAME)
        val watch_count = arguments?.getInt(TAG_REP_WATCH).toString()

        view.toolbarTitle.text = name
        view.tvRepositoryName.text = name
        view.tvWatchers.text = watch_count
    }

    private fun initAnimation(anim: Animation) {
        view.containerPage.animation = anim
    }
}
