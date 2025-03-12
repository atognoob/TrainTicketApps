package com.example.myapplication.Domain

import java.io.Serializable

data class TrainModel(
    var TrainName:String = "",
    var ArriveTime:String = "",
    var Date: String="",
    var From: String="",
    var NumberSeat: Int=0,
    var Price: Double=0.0,
    var Passenger:String="",
    var Seats:String="",
    var ReservedSeats: String="",
    var Time:String="",
    var To:String="",
    var TimeEnd:String=""


): Serializable
