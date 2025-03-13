package com.example.myapplication.Activities.TicketDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.myapplication.R

@Composable
fun TicketDetailHeader(
    onBackClick:() -> Unit,
    modifier: Modifier
){
    ConstraintLayout (modifier = Modifier
        .fillMaxWidth()
        .background(color = colorResource(R.color.white))
        .padding(top=36.dp, start = 16.dp, end = 16.dp)
    ) {
        val (backBtn, headerTitle)=createRefs()
        Image(painter = painterResource(R.drawable.back),
            contentDescription = null,
            modifier = Modifier
                .clickable{onBackClick()}
                .constrainAs (backBtn){
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
                .size(50.dp)
        )
        Text(
            text = "Билет",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(start = 8.dp)
                .constrainAs(headerTitle){
                    start.linkTo(backBtn.end, margin = 8.dp)
                    top.linkTo(backBtn.top)
                    bottom.linkTo(backBtn.bottom)
                }
        )
    }
}