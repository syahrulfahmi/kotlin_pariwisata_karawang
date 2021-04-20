package com.sf.krw.utils

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * بِسْمِ اللهِ الرَّحْمٰنِ الرَّحِيْمِ
 * Created By Fahmi on 20/03/21
 */

abstract class BaseActivityBinding<T : ViewBinding> : AppCompatActivity() {
    private var _binding: ViewBinding? = null
    abstract val bindingInflater: (LayoutInflater) -> T

    @Suppress("UNCHECKED_CAST")
    protected val binding: T
        get() = _binding as T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater.invoke(layoutInflater)

        setContentView(binding.root)
        setUpView(binding)
    }

    abstract fun setUpView(binding: T)

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}