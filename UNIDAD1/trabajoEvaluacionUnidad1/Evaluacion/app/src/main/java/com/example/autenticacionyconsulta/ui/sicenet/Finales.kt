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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.res.colorResource
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

class Finales : ComponentActivity() {
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
fun Finales(
    navController: NavController,
    viewmodel: AppView
){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            text = stringResource(id = R.string.calificacionFinal)
                        )
                        if(viewmodel.modoOffline){
                            Row (
                                horizontalArrangement = Arrangement.End,
                                modifier=Modifier.fillMaxWidth()
                            ){
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
                                    Row (
                                        verticalAlignment = Alignment.CenterVertically
                                    ){

                                        Icon( painter = painterResource(id = R.drawable.warning),
                                            contentDescription = "", modifier = Modifier.padding(end = 4.dp))
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
        CalificacionFinal(it, viewmodel)
    }
}

@Composable
private fun CalificacionFinal(
    paddingValues: PaddingValues,
    viewmodel: AppView
){
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(5.dp),
        contentPadding = paddingValues
    ){
        var colorMateria= Color(2,2,2)
        items(viewmodel.finales){
            if(it.calif>=95){//excelente
                colorMateria= colorResource(id = R.color.desempenioE)
            }else if(it.calif<95 && it.calif>=90){//bueno
                colorMateria= colorResource(id = R.color.desempenioB)
            }else if(it.calif<90 && it.calif>=80){//normal
                colorMateria= colorResource(id = R.color.desempenioN)
            }else if(it.calif<80 && it.calif>0){//regular
                colorMateria= colorResource(id = R.color.desempenioR)
            }else{
                colorMateria= colorResource(id = R.color.desempenioNull)
            }
            Card{
                Row(
                    modifier = Modifier.fillMaxWidth()
                ){
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(colorMateria).padding(start = 4.dp)
                    ){
                        Text(it.materia, fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(horizontal = 5.dp))
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 5.dp)
                ){
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier.weight(0.4f).padding(start = 5.dp)
                    ){
                        Text(text = "Calificación: " + it.calif)
                    }
                    Row(
                        modifier = Modifier.weight(0.6f)
                    ){
                        Text(text = "Acreditación: " + it.acred)
                    }
                }
            }
        }
    }
    if(viewmodel.sinFinales){
        LazyColumn(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize(),
            contentPadding = paddingValues
        ){
            item {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.warning),
                        contentDescription = "",
                        modifier=Modifier.size(35.dp),
                        tint = Color(255,124,112))

                }
            }
        }
    }
}

