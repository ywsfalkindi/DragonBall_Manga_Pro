package com.saiyan.dbmanga.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import androidx.compose.foundation.background

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    // Scaffold هو الهيكل الأساسي (يحتوي على شريط علوي ومساحة بالأسفل)
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("مكتبة دراغون بول", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1E1E1E), // رمادي غامق فخم
                    titleContentColor = Color(0xFFFF9800) // برتقالي
                )
            )
        },
        containerColor = Color.Black // خلفية التطبيق سوداء
    ) { paddingValues ->

        // الشبكة الصاروخية لعرض المجلدات
        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // نريد عرض عمودين بجانب بعض
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp), // المسافة الأفقية بين المجلدات
            verticalArrangement = Arrangement.spacedBy(10.dp) // المسافة العمودية
        ) {
            // نصنع 20 مجلداً وهمياً للتجربة (لاحقاً سنجلبها من السيرفر)
            items(20) { index ->
                MangaCard(volumeNumber = index + 1)
            }
        }
    }
}

// تصميم بطاقة المانجا الواحدة
@Composable
fun MangaCard(volumeNumber: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp), // زدنا الطول قليلاً ليناسب بوستر المانجا
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2C2C2C))
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // المحرك الصاروخي لتحميل الصور
            AsyncImage(
                model = "https://placehold.co/400x600/orange/white?text=DB+Vol+$volumeNumber", // رابط صورة مؤقت
                contentDescription = "Manga Cover",
                modifier = Modifier.fillMaxSize(),
                contentScale = androidx.compose.ui.layout.ContentScale.Crop // قص الصورة لتملأ المساحة بجمال
            )

            // صندوق صغير شفاف في الأسفل لعرض رقم المجلد
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(Color(0xAA000000)) // خلفية سوداء شفافة
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Vol $volumeNumber",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}