package com.example.myweatherapplication

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myweatherapplication.ui.theme.MyWeatherApplicationTheme

class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyWeatherApplicationTheme {
                navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SetupNavGraph(navController = navController)
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun SetupNavGraph(navController: NavHostController){

        NavHost(
            navController = navController,
            startDestination = Screen.Home.route
        ){
            composable(
                route = Screen.Home.route
            ){
                WeatherApp(navController = navController)
            }
            composable(
                route = Screen.Forecast.route
            ){
                ForecastScreen()
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun WeatherApp(navController: NavHostController) {
        Scaffold(topBar = {
            TopAppBar(
                title = { Text("Weather App") },
                colors = TopAppBarDefaults.largeTopAppBarColors(Color.Green), // Set the background color of the TopAppBar
                modifier = Modifier.fillMaxWidth()
            )
        }) {
            padding ->
                Column(
                    modifier = Modifier.fillMaxSize().padding(padding),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = stringResource(id = R.string.location),
                        fontSize = 20.sp,
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center

                    ) {
                        Column(
                            modifier = Modifier.align(Alignment.CenterVertically)
                        ) {
                            Text(
                                text = stringResource(id = R.string.temperature),

                                fontSize = 50.sp,
                                style = MaterialTheme.typography.headlineLarge

                            )
                            Text(
                                text = stringResource(id = R.string.feels_like),
                                style = MaterialTheme.typography.bodySmall
                            )
                        }

                        Spacer(modifier = Modifier.width(100.dp))
                        Image(
                            painter = painterResource(id = R.drawable.sun),
                            contentDescription = "Sun Icon",
                            modifier = Modifier
                                .size(100.dp)
                                .align(Alignment.CenterVertically)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth().padding(60.dp, 0.dp),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Column {
                            Text(text = stringResource(id = R.string.low), style = MaterialTheme.typography.bodyMedium)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = stringResource(id = R.string.high), style = MaterialTheme.typography.bodyMedium)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = stringResource(id = R.string.humidity),
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = stringResource(id = R.string.pressure),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = {
                            navController.navigate(route = Screen.Forecast.route)
                        },
                        colors = ButtonDefaults.buttonColors(Color.Green)
                        ) {
                        Text(text = "Forecast")
                    }

                }
            }
        }
    @Composable
    @Preview(showBackground = true)
    fun WeatherAppPreview()
    {
        WeatherApp(navController = rememberNavController())
    }
}