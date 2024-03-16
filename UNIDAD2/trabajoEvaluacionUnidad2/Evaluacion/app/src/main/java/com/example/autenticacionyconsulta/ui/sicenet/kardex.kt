package com.example.autenticacionyconsulta.ui.sicenet

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.autenticacionyconsulta.R
import com.example.autenticacionyconsulta.ViewModel.AppView
import com.example.autenticacionyconsulta.ui.theme.AutenticacionYConsultaTheme

class kardex : ComponentActivity() {
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
fun Kardex(
    navController: NavController,
    viewmodel: AppView
){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.kardex)
                        )
                        if (viewmodel.modoOffline) {
                            Row(
                                horizontalArrangement = Arrangement.End,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Box(
                                    modifier = Modifier
                                        .background(
                                            color = Color(255, 158, 142),
                                            shape = CircleShape
                                        )
                                        .padding(start = 10.dp, end = 10.dp)
                                        .height(25.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {

                                        Icon(
                                            painter = painterResource(id = R.drawable.warning),
                                            contentDescription = "",
                                            modifier = Modifier.padding(end = 4.dp)
                                        )
                                        Text(
                                            text = "Sin conexión",
                                            fontSize = 14.sp
                                        )
                                    }
                                }
                            }
                        }
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Green),

                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.back),
                            contentDescription = "")
                    }
                })
        }
    ) {
        KardexMateria(viewmodel = viewmodel,it)
    }
}


@Composable
private fun KardexMateria(
   viewmodel: AppView,
   paddingValues:PaddingValues
){
    Log.d("kardex",""+viewmodel.kardex.size)
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(5.dp),
        contentPadding = paddingValues
    ){

        items(viewmodel.kardex){

            //CARD
            Card(modifier = Modifier.padding(8.dp)){
                Row(
                    modifier = Modifier.fillMaxWidth().background(Color.DarkGray)
                ){
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .weight(0.9f)
                            .background(Color.Green)
                            .padding(start = 4.dp)
                    ){
                        Text(it.Materia, fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center, fontSize = 20.sp)
                    }
                    Column(
                        modifier = Modifier
                            .weight(0.1f)
                            .background(Color.DarkGray)
                            .fillMaxHeight()
                    ){
                        Text("")
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent)
                        .padding(start = 5.dp)
                ){
                    Row(
                        modifier = Modifier.weight(0.5f)
                    ){
                        Text(text = "Calificacion: " + it.Calif)
                    }
                    Row(
                        modifier = Modifier.weight(0.5f)
                    ){
                        Text(text = "Clave: " + it.ClvMat)
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent)
                        .padding(start = 5.dp)
                ){
                    Row(
                        modifier = Modifier.weight(0.6f)
                    ){
                        Text(text = "Oficial: " + it.ClvOfiMat)
                    }
                    Row(
                        modifier = Modifier.weight(0.4f)
                    ){
                        Text(text = "Cdts: " + it.Cdts)
                    }
                }
            }
        }
        //CARD RESUMEN KARDEX
        item{
            Card{
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 5.dp).background(Color.Blue)
                ){
                    Text("PROMEDIO GENERAL: "+viewmodel.resumenKardex.PromedioGral,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.Green
                        )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 5.dp)
                ){
                    Row(
                        modifier = Modifier.weight(0.5f)
                    ){
                        Text("Creditos acumulados: "+viewmodel.resumenKardex.CdtsAcum)
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 5.dp)
                ){
                    Row(
                        modifier = Modifier.weight(0.5f)
                    ){
                        Text("Materias cursadas: "+viewmodel.resumenKardex.MatCursadas)
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 5.dp)
                ){
                    Row(
                        modifier = Modifier.weight(0.5f)
                    ){
                        Text("Materias aprobadas: "+viewmodel.resumenKardex.MatAprobadas)
                    }
                }
            }
        }
    }
}

