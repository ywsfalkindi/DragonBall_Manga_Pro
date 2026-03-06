package com.saiyan.dbmanga.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

@Composable
fun ReaderScreen(volumeNumber: Int) {
    // روابط حقيقية لصفحات مانجا (للتجربة فقط)
    val realMangaPages = listOf(
        "https://imgur.com/a/sZ2XFPy",
        "https://imgur.com/cJFqpgx",
        "https://imgur.com/undefined",
        "https://imgur.com/undefined"
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        items(realMangaPages) { imageUrl ->
            ZoomablePage(imageUrl = imageUrl)
        }
    }
}

// ==========================================
// الدالة السحرية: صفحة مانجا قابلة للتكبير
// ==========================================
@Composable
fun ZoomablePage(imageUrl: String) {
    // 1. الذاكرة الخاصة بهذه الصفحة: كم نسبة التكبير الحالية؟ (1f تعني الحجم الطبيعي)
    var scale by remember { mutableFloatStateOf(1f) }

    // 2. الذاكرة الخاصة بالإزاحة: أين موقع الصورة بعد التكبير؟ (لتحريكها يميناً ويساراً)
    var offset by remember { mutableStateOf(Offset.Zero) }

    // 3. محرك استشعار الأصابع (Pinch Gesture)
    val state = rememberTransformableState { zoomChange, offsetChange, _ ->
        // نضرب الحجم الحالي في نسبة تغيير حركة الإصبع، ونمنع التكبير أكثر من 4 أضعاف أو التصغير أقل من الحجم الأصلي
        scale = (scale * zoomChange).coerceIn(1f, 4f)

        // إذا كان المستخدم قد كبّر الصورة، نسمح له بتحريكها، وإلا نعيدها لمكانها الطبيعي
        if (scale > 1f) {
            offset += offsetChange
        } else {
            offset = Offset.Zero
        }
    }

    AsyncImage(
        model = imageUrl,
        contentDescription = "Manga Page",
        modifier = Modifier
            .fillMaxWidth()
            // 4. تطبيق الأرقام (التي حسبناها في الأعلى) على الصورة لتبدو مكبرة ومزاحة
            .graphicsLayer(
                scaleX = scale,
                scaleY = scale,
                translationX = offset.x,
                translationY = offset.y
            )
            // 5. ربط الصورة بمحرك استشعار الأصابع
            .transformable(state = state),
        contentScale = ContentScale.FillWidth
    )
}