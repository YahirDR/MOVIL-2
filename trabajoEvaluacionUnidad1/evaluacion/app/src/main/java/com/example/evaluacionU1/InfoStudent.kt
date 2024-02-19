package com.example.evaluacionU1

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.evaluacionU1.ui.theme.AutenticacionYConsultaTheme

class InfoStudent : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AutenticacionYConsultaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun dataStudent(
    navController: NavController,
    text:String?
){
    val alumnoInfo=text?.split("(",")")?.get(1)?.split(",")
    Log.d("info",""+alumnoInfo)

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(7.dp)
    ){
        Card (
            modifier = Modifier.fillMaxSize()
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxHeight(.91f)
                    .fillMaxWidth()
                    .padding(3.dp)
                    .background(color = Color.Green)
            ) {
                TopAppBar(
                    title = { Text(text = "Bienvenido alumno:") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.Blue)
                )
                Text(
                    text = ""+alumnoInfo?.get(13)?.split("=")?.get(1),
                    fontSize = 21.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(5.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(text = "Adeudo: "+alumnoInfo?.get(2)?.split("=")?.get(1),
                        fontSize = 24.sp,
                        fontFamily = FontFamily.SansSerif,
                        color = Color.Black
                    )

                    Text(text = "Inscrito: "+alumnoInfo?.get(5)?.split("=")?.get(1),
                        fontSize = 24.sp,
                        fontFamily = FontFamily.SansSerif,
                        color = Color.Black)
                    Text(text = "Estatus: "+alumnoInfo?.get(6)?.split("=")?.get(1),
                        fontSize = 24.sp,
                        fontFamily = FontFamily.SansSerif,
                        color = Color.Black
                    )
                    Text(text = "Semestre actual: "+alumnoInfo?.get(7)?.split("=")?.get(1),
                        fontSize = 24.sp,
                        fontFamily = FontFamily.SansSerif,
                        color = Color.Black)
                    Text(text = "Creditos acumulados: "+alumnoInfo?.get(8)?.split("=")?.get(1),
                        fontSize = 24.sp,
                        fontFamily = FontFamily.SansSerif,
                        color = Color.Black)
                    Text(text = "Creditos actuales: "+alumnoInfo?.get(9)?.split("=")?.get(1),
                        fontSize = 24.sp,
                        fontFamily = FontFamily.SansSerif,
                        color = Color.Black)
                    Text(text = "Carrera: "+alumnoInfo?.get(11)?.split("=")?.get(1),
                        fontSize = 24.sp,
                        fontFamily = FontFamily.SansSerif,
                        color = Color.Black)
                    Text(text = "Numero de control: "+alumnoInfo?.get(14)?.split("=")?.get(1),
                        fontSize = 24.sp,
                        fontFamily = FontFamily.SansSerif,
                        color = Color.Black)
                    Text(text = "Especialidad :"+alumnoInfo?.get(10)?.split("=")?.get(1),
                        fontSize = 24.sp,
                        fontFamily = FontFamily.SansSerif,
                        color = Color.Black)
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = { navController.popBackStack()}
                    ) {
                        Text(text = "Cerrar Sesión")
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))

            }

        }
    }
}

