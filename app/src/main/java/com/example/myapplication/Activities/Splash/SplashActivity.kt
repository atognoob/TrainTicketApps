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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.myapplication.MainActivity
import com.example.myapplication.R



@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
enableEdgeToEdge()
        setContent {
            SplashScreen(onGetStartedClick = {
                startActivity(Intent(this, MainActivity::class.java))
            })
        }
    }
}

@Composable
@Preview
fun SplashScreen(onGetStartedClick: () -> Unit = {}) {
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
