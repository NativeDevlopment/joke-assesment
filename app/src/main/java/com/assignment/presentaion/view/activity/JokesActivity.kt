package com.assignment.presentaion.view.activity

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.assignment.BR
import com.assignment.R
import com.assignment.databinding.ActivityJokesBinding
import com.assignment.presentaion.base.BaseActivity
import com.assignment.presentaion.viewmodel.JokesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class JokesActivity : BaseActivity<ActivityJokesBinding>() {
    private val myViewModel: JokesViewModel by viewModel()

    override val layoutRes: Int
        get() = R.layout.activity_jokes
    override val bindingVariable: Int
        get() = BR.viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun getViewModel(): ViewModel {
       return myViewModel
    }

}