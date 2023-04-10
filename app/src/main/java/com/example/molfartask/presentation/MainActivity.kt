package com.example.molfartask.presentation

import android.os.Bundle
import android.widget.ImageButton
import com.example.molfartask.R
import com.example.molfartask.base.BaseActivity
import com.example.molfartask.presentation.subliminal.fragment.SubliminalFragment
import com.example.molfartask.presentation.subliminal.fragment.SubliminalFragment.Companion.SUBLIMINAL_FRAGMENT_TAG


class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setToolbars()
        if (savedInstanceState == null) {
            replaceFragment(
                SubliminalFragment.getInstance(),
                R.id.fcv_container,
                SUBLIMINAL_FRAGMENT_TAG
            )
        }
    }

    private fun setToolbars() {
        findViewById<ImageButton>(R.id.btn_search).setOnClickListener {
            makeToast(getString(R.string.coming_soon))
        }
        findViewById<ImageButton>(R.id.btn_info).setOnClickListener {
            makeToast(getString(R.string.coming_soon))
        }
        findViewById<ImageButton>(R.id.ib_favourites).setOnClickListener {
            makeToast(getString(R.string.coming_soon))

        }
        findViewById<ImageButton>(R.id.ib_journal).setOnClickListener {
            makeToast(getString(R.string.coming_soon))
        }
    }
}