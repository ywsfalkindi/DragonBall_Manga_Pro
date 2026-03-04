package com.saiyan.dbmanga

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.saiyan.dbmanga.ui.screens.HomeScreen
import com.saiyan.dbmanga.ui.screens.SplashScreen
import com.saiyan.dbmanga.ui.theme.DragonBallMangaProTheme

// --- الأسطر السحرية الجديدة لحل مشكلة الملاحة ---
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // انظر كيف أصبح الكود نظيفاً وقصيراً الآن!
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "splash_screen" // البداية دائماً من شاشة الترحيب
            ) {
                // المحطة الأولى
                composable("splash_screen") {
                    SplashScreen(onTimeout = {
                        // عندما ينتهي الوقت، انتقل للشاشة الرئيسية وامسح شاشة البداية من الذاكرة
                        navController.navigate("home_screen") {
                            popUpTo("splash_screen") { inclusive = true }
                        }
                    })
                }

                // المحطة الثانية
                composable("home_screen") {
                    HomeScreen()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DragonBallMangaProTheme {
        Greeting("Android")
    }
}