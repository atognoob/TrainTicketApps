package com.example.myapplication.Activities.SeatSelect

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.example.myapplication.Domain.TrainModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.unit.dp
import com.example.myapplication.R

import androidx.constraintlayout.compose.ConstraintLayout

enum class SeatStatus{
    AVAILABLE,
    SELECTED,
    UNAVAILABLE,
    EMPTY
}

data class Seat(
    var status: SeatStatus,
    var name:String
)

@Composable
fun SeatListScreen(
    train: TrainModel,
    onBackClick:() ->Unit,
    onConfirm:(TrainModel)->Unit
){
    val context = LocalContext.current

    val seatList = remember { mutableStateListOf<Seat>() }
    val selectedSeatNames = remember { mutableStateListOf<String>() }

    var seatCount by remember { mutableStateOf(0) }

    var totalPrice by remember { mutableStateOf(0.0) }

    LaunchedEffect(train) {
        seatList.clear()
        seatList.addAll(generateSeatList(train))
        seatCount = selectedSeatNames.size
        totalPrice = seatCount * train.Price
    }
    fun updatePriceAndCount(){
        seatCount = selectedSeatNames.size
        totalPrice = seatCount * train.Price
    }
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.white))

    ){
        val(topSection,middleSection) = createRefs()

        TopSection(
            modifier = Modifier
                .constrainAs (topSection){
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },onBackClick= onBackClick
        )

        //middle section
        ConstraintLayout(
            modifier = Modifier
                .padding(top = 100.dp)
                .constrainAs (middleSection){
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            val (map, seatGrid,bottomSection) =  createRefs()
            Image(
                painter = painterResource(R.drawable.airple_seat),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .constrainAs(map) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(7),
                modifier = Modifier
                    .padding(top=240.dp)
                    .padding(horizontal = 64.dp)
                    .constrainAs (seatGrid){
                        top.linkTo(parent.top)
                        start.linkTo(map.start)
                        end.linkTo(map.end)
                    }
            ) {
                items(seatList.size){ index ->
                    val seat = seatList[index]

                    SeatItem(
                        seat =seat,
                        onSeatClick = {
                            when(seat.status){
                                SeatStatus.AVAILABLE -> {
                                    seat.status= SeatStatus.SELECTED
                                    selectedSeatNames.add(seat.name)
                                }
                                SeatStatus.SELECTED -> {
                                    seat.status= SeatStatus.AVAILABLE
                                    selectedSeatNames.remove(seat.name)
                                }
                                else -> {

                                }
                            }
                            updatePriceAndCount()
                        }
                    )
                }
            }
            BottomSection(
                seatCount=seatCount,
                selectedSeats = selectedSeatNames.joinToString(","),
                totalPrice = totalPrice,
                onConfirmClick = {
                    if (seatCount>0){
                        train.Passenger=selectedSeatNames.joinToString(",")
                        train.Price = totalPrice
                        onConfirm(train)
                    }else{
                        Toast.makeText(context, "Please select your seat", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .constrainAs(bottomSection){
                        top.linkTo(seatGrid.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )

        }

    }
}

fun generateSeatList(train: TrainModel): List<Seat> {
    val seatList = mutableListOf<Seat>()
    val numberSeat = train.NumberSeat + (train.NumberSeat / 7) + 1
    val seatAplphabetMap = mapOf(
        0 to "A",
        1 to "B",
        2 to "C",
        4 to "D",
        5 to "E",
        6 to "F",

        )
    var row = 0

    for (i in 0 until numberSeat) {
        if (i % 7 == 0) {
            row++
        }
        if (i % 7 == 3) {
            seatList.add(Seat(SeatStatus.EMPTY, row.toString()))
        } else {
            val seatName = seatAplphabetMap[i % 7] + row
            val seatStatus = if (train.ReservedSeats.contains(seatName)) {
                SeatStatus.UNAVAILABLE
            } else {
                SeatStatus.AVAILABLE
            }
            seatList.add(Seat(seatStatus,seatName))
        }
    }
    return seatList
}