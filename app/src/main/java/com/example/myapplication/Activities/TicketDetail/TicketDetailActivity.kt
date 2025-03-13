package com.example.myapplication.Activities.TicketDetail

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.Activities.Splash.StatusTopBarColor
import com.example.myapplication.Domain.TrainModel
import com.example.myapplication.R

class TicketDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val train = intent.getSerializableExtra("train") as TrainModel

        setContent {
            StatusTopBarColor()

            TicketDetailScreen(
                train = train,
                onBackClick = {finish()},
                onDownloadTicketClick = {

                }
            )
        }

    }
}