package com.example.autenticacionyconsulta.ui.sicenet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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

class Horario : ComponentActivity() {
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
fun Horario(
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
                            text = stringResource(id = R.string.horario)
                        )
                        if(viewmodel.modoOffline) {
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
                                            text = "Sin conexion",
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
        presentacion(viewmodel = viewmodel,it)
    }
}

@Composable
fun presentacion(
    viewmodel: AppView,
    paddingValues: PaddingValues
){
    if(viewmodel.sinHorario==false) {
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = "LUNES",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .background(Color(81, 189, 126, 255))
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(5.dp))
            }
            items(viewmodel.lunes) {
                Card(
                    modifier = Modifier.fillMaxWidth(.8f).padding(10.dp),
                    shape = RoundedCornerShape(10.dp),

                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth().background(Color.LightGray)
                    ) {
                        Text(
                            text = it.Materia,
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                        Text(
                            text = it.Lunes,
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                        Text(
                            text = it.Docente,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(7.dp))
            }
            item {
                Text(
                    text = "MARTES",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .background(Color(81, 189, 126, 255))
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(5.dp))
            }
            items(viewmodel.martes) {
                Card(
                    modifier = Modifier.fillMaxWidth(.8f).padding(10.dp).background(Color.White),
                    shape = RoundedCornerShape(10.dp),
                ) {
                    Column(

                        modifier = Modifier.fillMaxWidth().background(Color.LightGray)
                    ) {
                        Text(
                            text = it.Materia,
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                        Text(
                            text = it.Martes,
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                        Text(
                            text = it.Docente,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(7.dp))
            }
            item {
                Text(
                    text = "MIERCOLES",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .background(Color(81, 189, 126, 255))
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(5.dp))
            }
            items(viewmodel.miercoles) {
                Card(
                    modifier = Modifier.fillMaxWidth(.8f).padding(10.dp).background(Color.White),
                    shape = RoundedCornerShape(10.dp),
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth().background(Color.LightGray)
                    ) {
                        Text(
                            text = it.Materia,
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                        Text(
                            text = it.Miercoles,
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                        Text(
                            text = it.Docente,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(7.dp))
            }
            item {
                Text(
                    text = "JUEVES",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .background(Color(81, 189, 126, 255))
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(5.dp))
            }
            items(viewmodel.jueves) {
                Card(
                    modifier = Modifier.fillMaxWidth(.8f).padding(10.dp).background(Color.White),
                    shape = RoundedCornerShape(10.dp),
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth().background(Color.LightGray)
                    ) {
                        Text(
                            text = it.Materia,
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                        Text(
                            text = it.Jueves,
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                        Text(
                            text = it.Docente,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(7.dp))
            }
            item {
                Text(
                    text = "VIERNES",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .background(Color(81, 189, 126, 255))
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(5.dp))
            }
            items(viewmodel.viernes) {
                Card(
                    modifier = Modifier.fillMaxWidth(.8f).padding(10.dp).background(Color.White),
                    shape = RoundedCornerShape(10.dp),
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth().background(Color.LightGray)
                    ) {
                        Text(
                            text = it.Materia,
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                        Text(
                            text = it.Viernes,
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                        Text(
                            text = it.Docente,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(7.dp))
            }
        }
    }else{
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            item {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.warning),
                        contentDescription = "",
                        modifier=Modifier.size(75.dp),
                        tint = Color(255,124,112))
                    Text(
                        text = "No se encontro ningun horario",
                        fontSize = 14.sp,
                        color=Color(255,158,142))

                }
            }
        }
    }
}



@Composable
fun presentacionHorario(paddingValues: PaddingValues) {
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item{
                Text(
                    text = "Dia",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .background(Color(81, 189, 126, 255))
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(5.dp))
            }
            items(9) {
                Card (
                    modifier = Modifier.fillMaxWidth(.8f)
                ){
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ){
                        Text(
                            text = "Materia",
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                        Text(
                            text = "Hora y aula",
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                        Text(
                            text = "Profesor",
                            fontSize = 23.sp,
                            modifier = Modifier.padding(start = 5.dp)
                        )

                    }
                }
                Spacer(modifier = Modifier.height(7.dp))
            }
        }
}

