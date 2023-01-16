package com.assignment.presentaion.viewmodel

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.assginment.domain.common.ResultState
import com.assginment.domain.common.Status
import com.assginment.domain.entity.JokeEntity
import com.assginment.domain.usecases.GetJokeUseCase
import com.assginment.domain.usecases.GetLocalJokeUseCase
import com.assignment.R
import com.assignment.BR
import com.assignment.presentaion.base.BaseViewModel
import com.assignment.presentaion.view.activity.JokeRecyclerViewAdapter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.tatarka.bindingcollectionadapter2.ItemBinding
import me.tatarka.bindingcollectionadapter2.OnItemBind
import me.tatarka.bindingcollectionadapter2.collections.MergeObservableList

class JokesViewModel (val getJokeUseCase: GetJokeUseCase ,val getLocalJokeUseCase: GetLocalJokeUseCase) :BaseViewModel(){
    val items = MergeObservableList<Any>()
val  resultLiveData=  MutableLiveData<ResultState<List<JokeEntity>>>()
    private val multipleItemBindings =
        OnItemBind<Any> { itemBinding, _, item ->

            itemBinding.set(BR.item, R.layout.jokes_list_item)
        }
    val itemBinding = ItemBinding.of(multipleItemBindings)
    val adapter = JokeRecyclerViewAdapter<Any>()
    init {
        resultLiveData.value= ResultState(Status.LOADING,null,null)

        // items.insertItem("test")
        viewModelScope.launch {
            getLocalJokeUseCase.excute().collect{result->
                if(result.data!=null){
                    resultLiveData.value=result
                    adapter.setItems(result.data)
                }
            }
            while (true){
                delay(60000)
                getJokeUseCase.excute().collect{ result->
                    resultLiveData.value=result

                        if(result.data!=null){

                            adapter.setItems(result.data)
                            adapter.notifyDataSetChanged()
                        }
                    }


           // items.insertItem("test1$count")
            }

        }
    }

}