package com.example.myapplication.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.Domain.LocationModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainRepository {

    private val firebaseDatabase = FirebaseDatabase.getInstance()

    fun loadLocation(): LiveData<MutableList<LocationModel>>{
        val listData= MutableLiveData<MutableList<LocationModel>>()
        val ref = firebaseDatabase.getReference("Locations")
        ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<LocationModel>()
                for (childSnapshot in snapshot.children){
                    val item = childSnapshot.getValue(LocationModel::class.java)
                    item?.let { list.add(it) }
                }
                Log.d("FirebaseData", "So dia diem:${list.size}")
                listData.value = list
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseError", "Lỗi Firebase: ${error.message}")
            }


        })
        return listData
    }
}

