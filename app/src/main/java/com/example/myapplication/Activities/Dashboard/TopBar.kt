package com.example.myapplication.Activities.Dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.myapplication.R


@Composable
@Preview
fun Topbar(){

    Spacer(modifier = Modifier.height(40.dp))
    ConstraintLayout (
        modifier = Modifier
            .padding(horizontal = 115.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ){
        val(find, glass) = createRefs()

        Image(
            painter = painterResource(R.drawable.glass),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .constrainAs (glass){
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
        )

        Text(
            text = "Поиск",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp,
            modifier = Modifier.constrainAs (find){
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
            }
        )
    }
}