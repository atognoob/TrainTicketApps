package com.example.myapplication.Activities.TicketDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.myapplication.Activities.Splash.GradientButton
import com.example.myapplication.Domain.TrainModel
import com.example.myapplication.R
@Composable
fun TicketDetailScreen(
    train: TrainModel,
    onBackClick:()-> Unit,
    onDownloadTicketClick:() -> Unit,
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.white))
    ){
        Column (
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .background(colorResource(R.color.white))
        ){
            ConstraintLayout (modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.white))
            ){
                val(topSection,ticketDetail) = createRefs()

                TicketDetailHeader(
                    onBackClick=onBackClick,
                    modifier = Modifier
                        .constrainAs (topSection){
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                )

                TicketDetailContent(train=train, modifier = Modifier.constrainAs (ticketDetail){
                    top.linkTo(parent.top, margin = 110.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)


                })
            }

            GradientButton(onClick = {}, text = "Download Ticket", padding = 32)
        }
    }
}