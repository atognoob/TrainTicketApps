package com.example.myapplication.Activities.SeatSelect

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.Activities.Splash.StatusTopBarColor
import com.example.myapplication.Activities.TicketDetail.TicketDetailActivity
import com.example.myapplication.Domain.TrainModel
import com.example.myapplication.R

class SeatSelectActivity : AppCompatActivity() {
    private lateinit var train: TrainModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        train = intent.getSerializableExtra("train") as TrainModel

        setContent {
            StatusTopBarColor()
            SeatListScreen(
                train = train,
                onBackClick = {
                    finish()
                }, onConfirm = {
                    val intent = Intent(this, TicketDetailActivity::class.java).apply {
                        putExtra("train", train)
                    }
                    startActivity(intent,null)
                }

            )
        }
    }
}