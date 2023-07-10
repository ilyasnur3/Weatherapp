package com.example.myweatherapplication

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForecastScreen(){
    val forecastItems = List(16) {
        DayForecast(
            date = System.currentTimeMillis() + it * 24 * 60 * 60 * 1000L,
            sunrise = System.currentTimeMillis() + it * 24 * 60 * 60 * 1000L + 6 * 60 * 60 * 1000L,
            sunset = System.currentTimeMillis() + it * 24 * 60 * 60 * 1000L + 18 * 60 * 60 * 1000L,
            temp = ForecastTemp(day = 72f + it, min = 65f + it, max = 80f + it),
            pressure = 1000f + it,
            humidity = 50 + it
        )
    }
    Scaffold(topBar = {
        TopAppBar(
            title = { Text("Forecast") },
            colors = TopAppBarDefaults.largeTopAppBarColors(Color.Green), // Set the background color of the TopAppBar
            modifier = Modifier.fillMaxWidth()
        )
    }) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentPadding = PaddingValues(all = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(forecastItems) { forecast ->
                // convert timestamps to formatted date and time
                val date = Instant.ofEpochMilli(forecast.date).atZone(ZoneId.systemDefault()).toLocalDate()
                val sunrise = Instant.ofEpochMilli(forecast.sunrise).atZone(ZoneId.systemDefault()).toLocalDateTime()
                val sunset = Instant.ofEpochMilli(forecast.sunset).atZone(ZoneId.systemDefault()).toLocalDateTime()
                val dateFormatter = DateTimeFormatter.ofPattern("MMM dd")
                val timeFormatter = DateTimeFormatter.ofPattern("h:mma")

                Column(modifier = Modifier.fillMaxHeight().padding(0.dp, 0.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(0.dp, 0.dp),
                        horizontalArrangement = Arrangement.End) {
                        Text("Temp: ${forecast.temp.day}°")
                        Spacer(modifier = Modifier.width(80.dp))
                        Text("Sunrise: ${sunrise.format(timeFormatter)}")
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth().padding(0.dp, 0.dp),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.sun),
                            contentDescription = "Sun Icon",
                            modifier = Modifier
                                .size(50.dp)
                                .align(Alignment.CenterVertically)
                        )

                        Text(
                            text = date.format(dateFormatter)
                        )
                    }

                    Row(modifier = Modifier.fillMaxWidth().padding(0.dp, 0.dp),
                        horizontalArrangement = Arrangement.End) {
                        Text("High: ${forecast.temp.max}°\tLow: ${forecast.temp.min}°")
                        Spacer(modifier = Modifier.width(20.dp))
                        Text("Sunset: ${sunset.format(timeFormatter)}")
                    }
                }

                Spacer(modifier = Modifier.height(50.dp))
            }
        }
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview(showBackground = true)
fun ForecastScreenPreview()
{
    ForecastScreen()
}