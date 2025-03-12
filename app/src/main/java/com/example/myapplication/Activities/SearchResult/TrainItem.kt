package com.example.myapplication.Activities.SearchResult

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.myapplication.Activities.SeatSelect.SeatSelectActivity
import com.example.myapplication.Domain.TrainModel
import com.example.myapplication.R



@Composable
fun TrainItem(item: TrainModel, index: Int){
    val context = LocalContext.current

    ConstraintLayout (
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable{
                val intent = Intent(context, SeatSelectActivity::class.java).apply {
                    putExtra("train", item)

                }
                context.startActivity(intent)
            }
            .background(
                color = colorResource(R.color.lightblue),
                shape = RoundedCornerShape(15.dp)
            )
    ){
        val (trainName, timeTxt,trainIcon, dashLine,priceTxt, dateText,timeStart
            ,fromTxt,toTxt,dateText2,timeEnd) = createRefs()

        Text(
            text = item.From,
            fontSize = 20.sp,
            color = colorResource(R.color.black),
            modifier = Modifier
                .padding(16.dp)
                .constrainAs (fromTxt){
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
        )

        Text(
            text = item.To,
            fontSize = 20.sp,
            color = colorResource(R.color.black),
            modifier = Modifier
                .padding(16.dp)
                .constrainAs (toTxt){

                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
        )

        Text(
            text = item.Time,
            fontSize = 30.sp,
            color = colorResource(R.color.black),
            modifier = Modifier
                .padding(16.dp)
                .constrainAs (timeStart){

                    top.linkTo(fromTxt.bottom)
                    //end.linkTo(parent.end)
                }
        )
        Text(
            text = item.TimeEnd,
            fontSize = 30.sp,
            color = colorResource(R.color.black),
            modifier = Modifier
                .padding(16.dp)
                .constrainAs (timeEnd){

                    top.linkTo(fromTxt.bottom)
                    end.linkTo(parent.end)
                }
        )
        Text(
            text = item.Date,
            fontSize = 16.sp,
            color = colorResource(R.color.black),
            modifier = Modifier
                .padding(16.dp)
                .constrainAs (dateText){

                    top.linkTo(timeStart.bottom)
                    //end.linkTo(parent.end)
                }
        )
        Text(
            text = item.Date,
            fontSize = 16.sp,
            color = colorResource(R.color.black),
            modifier = Modifier
                .padding(16.dp)
                .constrainAs (dateText2){

                    top.linkTo(timeStart.bottom)
                    end.linkTo(parent.end)
                }
        )
        Text(
            text = item.TrainName,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = colorResource(R.color.white),
            modifier = Modifier
                .padding(top=8.dp)
                .constrainAs (trainName){
                    start.linkTo(parent.start)
                    top.linkTo(fromTxt.bottom)
                    end.linkTo(parent.end)
                }
        )
        Image(
            painter = painterResource(R.drawable.train_icon),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(trainIcon) {
                    start.linkTo(parent.start)
                    top.linkTo(trainName.bottom)
                    end.linkTo(parent.end)
                }
                .size(50.dp)
        )
        Text(
            text = item.ArriveTime,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = colorResource(R.color.black),
            modifier = Modifier
                .padding(top=8.dp)
                .constrainAs (timeTxt){
                    start.linkTo(parent.start)
                    top.linkTo(trainIcon.bottom)
                    end.linkTo(parent.end)
                }
        )
        Image(
            painter = painterResource(R.drawable.dash_line),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(dashLine) {
                    start.linkTo(parent.start)
                    top.linkTo(timeTxt.bottom)
                    end.linkTo(parent.end)
                }
                .size(10.dp)
        )
        Text(
            text = "от ${String.format("%.2f",item.Price)} руб",
            fontWeight = FontWeight.SemiBold,
            fontSize = 25.sp,
            color = colorResource(R.color.black),
            modifier = Modifier
                .padding(8.dp)
                .constrainAs (priceTxt){
                    top.linkTo(dashLine.bottom)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
        )



    }
}

//val (trainName, timeTxt,trainIcon, textNumberTrain, dashLine,priceTxt,seatIcon,
//classTxt,fromTxt,fromShortTxt,toTxt,toShortTxt) = createRefs()