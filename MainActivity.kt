package com.example.uts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private val PrimaryColor = Color(0xFF880E4F)
private val PageBackground = Color(0xFFFCE4EC)

data class Jadwal(
    val waktu: String,
    val mataKuliah: String,
    val ruangan: String
)

private val daftarJadwal = mapOf(
    "Senin" to listOf(
        Jadwal("08.00 - 09.40", "Pemrograman Mobile", "Ruang Lab 2"),
        Jadwal("10.00 - 11.40", "Basis Data", "Ruang 304")
    ),
    "Selasa" to listOf(
        Jadwal("09.00 - 10.40", "Pemrograman Web", "Ruang 202"),
        Jadwal("13.00 - 14.40", "Struktur Data", "Ruang 305")
    ),
    "Rabu" to listOf(
        Jadwal("08.00 - 09.40", "Sistem Operasi", "Ruang Lab 1"),
        Jadwal("10.00 - 11.40", "Matematika Diskrit", "Ruang 301")
    ),
    "Kamis" to listOf(
        Jadwal("09.00 - 10.40", "Jaringan Komputer", "Ruang Lab 3"),
        Jadwal("13.00 - 14.40", "Rekayasa Perangkat Lunak", "Ruang 302")
    )
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = PageBackground
                ) {
                    JadwalKuliahApp()
                }
            }
        }
    }
}

@Composable
fun JadwalKuliahApp() {

    var hariTerpilih by remember {
        mutableStateOf("Senin")
    }

    val daftarHari = listOf("Senin", "Selasa", "Rabu", "Kamis")

    val jadwalHari = daftarJadwal[hariTerpilih] ?: emptyList()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Jadwal Kuliah",
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = PrimaryColor,
            textAlign = TextAlign.Center
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            daftarHari.forEach { hari ->

                val terpilih = hari == hariTerpilih

                Surface(
                    modifier = Modifier.clickable {
                        hariTerpilih = hari
                    },
                    shape = RoundedCornerShape(10.dp),
                    color = if (terpilih) PrimaryColor else Color.White
                ) {
                    Text(
                        text = hari,
                        modifier = Modifier.padding(
                            horizontal = 14.dp,
                            vertical = 10.dp
                        ),
                        color = if (terpilih) Color.White else PrimaryColor,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(jadwalHari) { jadwal ->

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 2.dp
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = jadwal.waktu,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryColor
                        )

                        Text(
                            text = jadwal.mataKuliah,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold
                        )

                        Text(
                            text = jadwal.ruangan,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun JadwalKuliahPreview() {
    MaterialTheme {
        Surface(color = PageBackground) {
            JadwalKuliahApp()
        }
    }
}