package com.example.myapplication.Activities.Dashboard

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.Activities.SearchResult.SearchResultActivity
import com.example.myapplication.Activities.Splash.GradientButton
import com.example.myapplication.Activities.Splash.StatusTopBarColor
import com.example.myapplication.Domain.LocationModel
import com.example.myapplication.R
import com.example.myapplication.ViewModel.MainViewModel

class DashboardActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen()
        }
    }
}
@Composable
@Preview
fun MainScreen(){
    val locations = remember{ mutableStateListOf<LocationModel>() }
    val viewModel = MainViewModel()
    var showLocationLoading by remember { mutableStateOf(true) }
    var from =""
    var to =""
    var passenger: String = ""
    val context = LocalContext.current


    StatusTopBarColor()

    LaunchedEffect (Unit){
        viewModel.loadLocations().observeForever {

            result->locations.clear()
            locations.addAll(result)
            showLocationLoading = false
        }
    }

    Scaffold (
        bottomBar = { MyBottomBar()},
    ) { paddingValues ->
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.white))
                .padding(paddingValues = paddingValues)
        ){
            item { Topbar() }
            item {
                Column (modifier = Modifier
                    .padding(32.dp)
                    .background(colorResource(R.color.blue)
                    , shape = RoundedCornerShape(20.dp)
                    )
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 24.dp)
                ) {
                    //from
                    WhiteTitle("From")
                    val locationNames: List<String> = locations.map{it.Name}
                    DropDownList(
                        items = locationNames,
                        loadingIcon = painterResource(R.drawable.from_inc),
                        hint = "Selected original",
                        showLocationLoading=showLocationLoading
                    ) { selectedItem ->
                        from = selectedItem
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    //to
                    WhiteTitle("To")
                    DropDownList(
                        items = locationNames,
                        loadingIcon = painterResource(R.drawable.from_inc),
                        hint = "Selected Destination",
                        showLocationLoading=showLocationLoading
                    ) { selectedItem ->
                        to = selectedItem
                    }

                    //calendar
                    Spacer(modifier = Modifier.height(16.dp))
                    WhiteTitle("Depature date")
                    DatePickerScreen1(Modifier.weight(1f))

                    Spacer(modifier = Modifier.height(16.dp))

                    WhiteTitle("Return date")
                    DatePickerScreen2(Modifier.weight(1f))

                    //passenger count
                    Spacer(modifier = Modifier.height(16.dp))
                    WhiteTitle("Passengers")
                    Row ( modifier = Modifier.fillMaxWidth()){
                        PassengerCounter(
                            title = "пассажир",
                            modifier = Modifier.weight(1f),
                            onItemSelected = {passenger=it}
                        )

                    }

                    //Search button
                    Spacer(modifier = Modifier.height(16.dp))
                    GradientButton(
                        onClick = {
                            val intent = Intent(context, SearchResultActivity::class.java).apply {
                                putExtra("from",from)
                                putExtra("to", to)
                                putExtra("numPassenger", passenger)
                            }
                            context.startActivity(intent)
                        },
                        text = "Search"
                    )
                }
            }
        }

    }
}

@Composable
fun WhiteTitle(text: String, modifier: Modifier= Modifier){
    Text(
        text=text,
        fontWeight = FontWeight.SemiBold,
        color = colorResource(R.color.white),
        modifier = modifier
    )
}