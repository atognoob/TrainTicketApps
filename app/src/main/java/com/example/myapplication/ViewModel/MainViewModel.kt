package com.example.myapplication.ViewModel

import androidx.lifecycle.LiveData
import com.example.myapplication.Domain.LocationModel
import com.example.myapplication.Domain.TrainModel
import com.example.myapplication.Repository.MainRepository

class MainViewModel {
    private val repository = MainRepository()

    fun loadLocations(): LiveData<MutableList<LocationModel>>{
        return repository.loadLocation()
    }

    fun loadFiltered(from:String, to: String):
            LiveData<MutableList<TrainModel>>{
        return repository.loadFiltered(from,to)
    }
}