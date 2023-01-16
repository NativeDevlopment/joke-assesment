package com.assignment.presentaion.view.activity

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import com.assginment.domain.common.Status
import com.assignment.BR
import com.assignment.R
import com.assignment.databinding.ActivityJokesBinding
import com.assignment.presentaion.base.BaseActivity
import com.assignment.presentaion.viewmodel.JokesViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class JokesActivity : BaseActivity<ActivityJokesBinding>() {

    private val myViewModel: JokesViewModel by viewModel()

    override val layoutRes: Int
        get() = R.layout.activity_jokes
    override val bindingVariable: Int
        get() = BR.viewModel

    override fun getViewModel(): ViewModel {
       return myViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setActionBar(dataBinding.toolbar)
        myViewModel.resultLiveData.observeForever {
          when (
              it.status)
          {
              Status.LOADING->{

                  dataBinding.pbbar.visibility=View.VISIBLE

              }
              Status.SUCCESS->{
                  dataBinding.pbbar.visibility=View.INVISIBLE

              } Status.ERROR->{
              dataBinding.pbbar.visibility=View.INVISIBLE
              it.message?.let { message ->
                  Snackbar.make(dataBinding.root,
                      message,Snackbar.LENGTH_SHORT).show()
              }

          }

              else -> {

              }
          }
        }
    }
}