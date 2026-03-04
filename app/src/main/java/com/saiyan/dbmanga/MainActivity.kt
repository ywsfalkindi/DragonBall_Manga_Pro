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
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.saiyan.dbmanga.ui.screens.ReaderScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // انظر كيف أصبح الكود نظيفاً وقصيراً الآن!
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "splash_screen"
            ) {
                // المحطة الأولى: البداية
                composable("splash_screen") {
                    SplashScreen(onTimeout = {
                        navController.navigate("home_screen") {
                            popUpTo("splash_screen") { inclusive = true }
                        }
                    })
                }

                // المحطة الثانية: الشاشة الرئيسية
                composable("home_screen") {
                    HomeScreen(onMangaClick = { volume ->
                        // عندما يضغط على مجلد، اذهب لشاشة القراءة وخذ الرقم معك!
                        navController.navigate("reader_screen/$volume")
                    })
                }

                // المحطة الثالثة: شاشة القراءة الجديدة
                composable(
                    route = "reader_screen/{volume}",
                    arguments = listOf(navArgument("volume") { type = NavType.IntType })
                ) { backStackEntry ->
                    // استخراج رقم المجلد من الرابط (وإذا فشل نعتبره 1)
                    val volume = backStackEntry.arguments?.getInt("volume") ?: 1
                    ReaderScreen(volumeNumber = volume)
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