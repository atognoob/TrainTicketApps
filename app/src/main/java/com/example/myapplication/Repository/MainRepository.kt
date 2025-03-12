package com.example.myapplication.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.Domain.TrainModel
import com.example.myapplication.Domain.LocationModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
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
                Log.e("FirebaseError", "Database error: ${error.message}")
            }


        })
        return listData
    }
    fun loadFiltered(from:String, to:String): LiveData<MutableList<TrainModel>>{
        val listData = MutableLiveData<MutableList<TrainModel>>()
        val ref = firebaseDatabase.getReference("Train")
        val query:Query=ref.orderByChild("from").equalTo(from)
        query.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<TrainModel>()
                for(childSnapshot in snapshot.children){
                    val list = childSnapshot.getValue(TrainModel::class.java)
                    if(list!=null){
                        if(list.To == to){
                            lists.add(list)
                        }
                    }

                }
                Log.d("FirebaseData", "So :${lists.size}")
                listData.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseError", "Database error: ${error.message}")
            }

        })
        return listData
    }
}

