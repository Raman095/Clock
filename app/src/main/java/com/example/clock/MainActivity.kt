package com.example.clock

import android.icu.util.Calendar
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.clock.ui.theme.ClockTheme
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.zIndex
import kotlinx.coroutines.delay
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import android.app.Activity
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {

            var hour by remember { mutableStateOf("0") }
            var minute by remember { mutableStateOf("0") }
            var second by remember { mutableStateOf("0") }
            var amOrPm by remember { mutableStateOf("0") }

            LaunchedEffect(Unit) {
                while (true) {
                    val cal = Calendar.getInstance()
                    hour = cal.get(Calendar.HOUR).run {
                        if (this.toString().length == 1) "0$this" else "$this"
                    }
                    minute = cal.get(Calendar.MINUTE).run {
                        if (this.toString().length == 1) "0$this" else "$this"
                    }
                    second = cal.get(Calendar.SECOND).run {
                        if (this.toString().length == 1) "0$this" else "$this"
                    }
                    amOrPm = (cal.get(Calendar.AM_PM)).run {
                        if (this == Calendar.AM) "AM" else "PM"
                    }

                    delay(1000)
                }
            }

            ClockTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    containerColor = Color(0xFF0E0E0E),
                    bottomBar = { NavigationBarBottom() },
                    contentWindowInsets = WindowInsets.systemBars
                ) {
                    Box(
                        modifier = Modifier
                            .padding(it)
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            HeaderComponent()

                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .fillMaxHeight(),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    AnalogClockComponent(
                                        hour = hour.toInt(),
                                        minute = minute.toInt(),
                                        second = second.toInt()
                                    )
                                    Spacer(modifier = Modifier.height(24.dp))
                                    DigitalClockComponent(
                                        hour = hour,
                                        minute = minute,
                                        amOrPm = amOrPm
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HeaderComponent() {
    Box(modifier = Modifier.padding(vertical = 16.dp)) {
        Text(
            text = "Clock", style = MaterialTheme.typography.titleMedium,
            color = Color.White
        )
    }
}

@Composable
fun NavigationBarBottom() {
    Box {
        // 🔹 Thin top divider line
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .zIndex(1f),     //  <--- Make sures the very thin line at the top of navigation bar appears above the bar.
            thickness = (1.06).dp,
            color = Color.White.copy(alpha = 0.1f)
        )

        NavigationBar(
            containerColor = Color(0xFF202020),
            tonalElevation = 0.dp
        ) {
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_outline_alarm_24),
                        contentDescription = null,
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent,
                    selectedIconColor = Color.White,
                    selectedTextColor = Color.White,
                    unselectedIconColor = Color(0xFF9E9E9E),
                    unselectedTextColor = Color(0xFF9E9E9E)
                ),
                label = { Text("Alarm") },
                selected = false,
                onClick = { }
            )

            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_hourglass_bottom_24),
                        contentDescription = null
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent,
                    selectedIconColor = Color.White,
                    selectedTextColor = Color.White,
                    unselectedIconColor = Color(0xFF9E9E9E),
                    unselectedTextColor = Color(0xFF9E9E9E)
                ),
                label = { Text("Timer") },
                selected = false,
                onClick = { }
            )

            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_outline_access_time_24),
                        contentDescription = null
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent,
                    selectedIconColor = Color.White,
                    selectedTextColor = Color.White,
                    unselectedIconColor = Color(0xFF9E9E9E),
                    unselectedTextColor = Color(0xFF9E9E9E)
                ),
                label = { Text("Clock") },
                selected = true,
                onClick = { }
            )

            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_outline_timer_24),
                        contentDescription = null
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent,
                    selectedIconColor = Color.White,
                    selectedTextColor = Color.White,
                    unselectedIconColor = Color(0xFF9E9E9E),
                    unselectedTextColor = Color(0xFF9E9E9E)
                ),
                label = { Text("Stopwatch") },
                selected = false,
                onClick = { }
            )
        }
    }
}

@Composable
fun DigitalClockComponent(
    hour: String,
    minute: String,
    amOrPm: String
) {
    Text(
        text = "$hour:$minute $amOrPm",
        style = MaterialTheme.typography.titleLarge,
        color = Color.White
    )
    Text(
        text = "Delhi, India",
        style = MaterialTheme.typography.bodyMedium.merge(
            androidx.compose.ui.text.TextStyle(
                color = MaterialTheme.colorScheme.onBackground.copy(
                    0.6f
                )
            )
        ),
        color = Color.White
    )
}