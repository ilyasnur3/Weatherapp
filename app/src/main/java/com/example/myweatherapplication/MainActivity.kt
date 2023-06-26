package com.example.myweatherapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.unit.dp
import com.example.myweatherapplication.ui.theme.MyWeatherApplicationTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyWeatherApplicationTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = { Text("Weather App") },
                                colors = TopAppBarDefaults.largeTopAppBarColors(Color.Yellow), // Set the background color of the TopAppBar
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    ) {
                            WeatherApp(it)
                    }
                }
            }
        }
    }
}

@Composable
fun WeatherApp(padding: PaddingValues) {
    Column(
        modifier = Modifier.fillMaxSize().padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Text(text = stringResource(id = R.string.location), style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center

        ) {
            Column(
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text(text = stringResource(id = R.string.temperature), style = MaterialTheme.typography.headlineLarge)
                Text(text = stringResource(id = R.string.feels_like), style = MaterialTheme.typography.bodySmall)
            }

            Spacer(modifier = Modifier.width(100.dp))
            Image(
                painter = painterResource(id = R.drawable.sun),
                contentDescription = "Sun Icon",
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(50.dp,0.dp),
            horizontalArrangement = Arrangement.Start) {
            Column{
                Text(text = stringResource(id = R.string.low), style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = stringResource(id = R.string.high), style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = stringResource(id = R.string.humidity), style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = stringResource(id = R.string.pressure), style = MaterialTheme.typography.bodyMedium)
            }
        }


    }
}

