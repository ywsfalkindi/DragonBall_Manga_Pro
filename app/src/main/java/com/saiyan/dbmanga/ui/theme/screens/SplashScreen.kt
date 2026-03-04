package com.saiyan.dbmanga.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    // 1. حالة الحركة (هل بدأت الحركة أم لا؟)
    var startAnimation by remember { mutableStateOf(false) }

    // 2. محرك الحركة: يجعل الشفافية تتغير من 0 (مخفي) إلى 1 (ظاهر) خلال ثانيتين
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(durationMillis = 2000),
        label = "Splash Animation"
    )

    // 3. المؤقت الزمني (يعمل مرة واحدة عند فتح الشاشة)
    LaunchedEffect(key1 = true) {
        startAnimation = true // ابدأ حركة الظهور
        delay(3000) // انتظر 3 ثوانٍ (3000 جزء من الثانية)
        onTimeout()
        // لاحقاً سنضع هنا كود الانتقال للشاشة الرئيسية!
    }

    // 4. التصميم والشكل (صندوق أسود يملأ الشاشة)
    Box(
        modifier = Modifier
            .fillMaxSize() // املأ الشاشة بالكامل
            .background(Color.Black), // لون الخلفية أسود
        contentAlignment = Alignment.Center // ضع كل شيء في المنتصف
    ) {
        // 5. النص الذي سيظهر في المنتصف
        Text(
            text = "Dragon Ball Manga",
            color = Color(0xFFFF9800), // كود اللون البرتقالي المميز
            fontSize = 36.sp, // حجم الخط كبير
            fontWeight = FontWeight.Bold, // خط عريض
            modifier = Modifier.alpha(alphaAnim.value) // ربط شفافية النص بمحرك الحركة
        )
    }
}