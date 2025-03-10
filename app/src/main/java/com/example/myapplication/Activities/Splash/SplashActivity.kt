package com.example.myapplication.Activities.Splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.myapplication.Activities.Dashboard.DashboardActivity
import com.example.myapplication.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
enableEdgeToEdge()
        setContent {
            SplashScreen(onGetStartedClick = {
                startActivity(Intent(this, DashboardActivity::class.java))
            })
        }
    }
}

@Composable
@Preview
fun SplashScreen(onGetStartedClick: () -> Unit = {}) {
    StatusTopBarColor()
    Column(modifier = Modifier.fillMaxSize()) {
        ConstraintLayout {
            val (backgroundImg, startbtn) = createRefs()

            Image(
                painter = painterResource(R.drawable.splash),
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(backgroundImg) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
                    .fillMaxSize()
            )

            Box(modifier = Modifier.constrainAs(startbtn) {
                bottom.linkTo(parent.bottom)
            }) {
                GradientButton(onClick = onGetStartedClick, text = "Get Started", padding = 32)
            }
        }
    }
}

@Composable
fun StatusTopBarColor(){
    val  systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color= Color.Transparent,
            darkIcons = false
        )
    }
}