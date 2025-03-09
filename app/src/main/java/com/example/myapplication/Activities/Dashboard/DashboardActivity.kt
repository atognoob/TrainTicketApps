package com.example.myapplication.Activities.Dashboard

import android.R
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.Activities.Dashboard.MyBottomBar

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen()
        }
    }
}
@Preview
@Composable
fun MainScreen(){

    Scaffold(
        bottomBar = { MyBottomBar() },
        ){ paddingValues ->
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.white))
                .padding(paddingValues = paddingValues)
        ) {
            item { Topbar() }
        }
    }
}

