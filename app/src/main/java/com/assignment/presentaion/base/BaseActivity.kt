package com.assignment.presentaion.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import kotlin.reflect.KClass


abstract class BaseActivity< D : ViewDataBinding>() :
    AppCompatActivity() {
    protected lateinit var dataBinding: D

    @get:LayoutRes
    protected abstract val layoutRes: Int

    abstract val bindingVariable: Int


    protected abstract fun getViewModel(): ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, layoutRes)
        dataBinding.lifecycleOwner = this
        dataBinding.setVariable(bindingVariable, getViewModel())
        dataBinding.executePendingBindings()




    }

}
