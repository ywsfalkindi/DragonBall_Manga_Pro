package com.saiyan.dbmanga.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

@Composable
fun ReaderScreen(volumeNumber: Int) {
    // نصنع 15 صفحة وهمية (مؤقتاً) تمثل فصل المانجا
    val dummyPages = List(15) { index ->
        "https://placehold.co/800x1200/1E1E1E/FF9800?text=Dragon+Ball\nVolume+$volumeNumber\nPage+${index + 1}"
    }

    // LazyColumn هو السر في السرعة! يحمل فقط الصفحات التي تراها عينك حالياً
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black) // خلفية سوداء سينمائية لراحة العين
    ) {
        items(dummyPages) { imageUrl ->
            AsyncImage(
                model = imageUrl,
                contentDescription = "Manga Page",
                modifier = Modifier.fillMaxWidth(), // الصورة تملأ عرض الشاشة بالكامل
                contentScale = ContentScale.FillWidth // قص ذكي لملء العرض دون تشويه
            )
        }
    }
}