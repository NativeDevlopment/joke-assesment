package com.assignment.presentaion.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.assginment.domain.usecases.GetJokeUseCase
import com.assignment.R
import com.assignment.BR
import com.assignment.presentaion.base.BaseViewModel
import com.assignment.presentaion.view.activity.JokeRecyclerViewAdapter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.tatarka.bindingcollectionadapter2.ItemBinding
import me.tatarka.bindingcollectionadapter2.OnItemBind
import me.tatarka.bindingcollectionadapter2.collections.MergeObservableList

class JokesViewModel (val getJokeUseCase: GetJokeUseCase) :BaseViewModel(){
    val items = MergeObservableList<Any>()

    private val multipleItemBindings =
        OnItemBind<Any> { itemBinding, _, item ->

            itemBinding.set(BR.item, R.layout.jokes_list_item)
        }
    val itemBinding = ItemBinding.of(multipleItemBindings)
    val adapter = JokeRecyclerViewAdapter<Any>()
    init {
       // items.insertItem("test")
        viewModelScope.launch {
            var count=1;
            while (true){
            delay(5000)
                if(count>=10){

                    items.removeItem(items.get(0))
                }
                getJokeUseCase.excute().collect{
                   Log.e("test",""+it)
                    if(it.data!=null){
                    items.insertItem( it.data)
                        count++;
                    }
                }
           // items.insertItem("test1$count")
            }

        }
    }

}