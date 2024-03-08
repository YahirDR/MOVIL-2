package com.example.autenticacionyconsulta

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
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
    modoEducativo:String?,
    viewmodel: AppView = viewModel(factory = AppView.Factory)
){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.calificacionFinal)
                    )
                },
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
        LaunchedEffect(Unit){
            viewmodel.getFinales(modoEducativo)
        }
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
                            .background(colorMateria)
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
}

@Preview
@Composable
private fun CalificacionFinalP(
    modifier: Modifier = Modifier
){
    val listaCalificaciones = mutableListOf<Final>()
    listaCalificaciones.add(Final("0",
        "Ordinario",
        "TI1A",
        "PROGRAMACION WEB II",
        ""
    ))
    listaCalificaciones.add(Final("0",
        "Ordinario",
        "2S7A",
        "CONMUTACION Y ENRUTAMIENTO DE REDES DE DATOS fsfdfds",
        ""
    ))
    listaCalificaciones.add(Final("0",
        "Ordinario",
        "TI4A",
        "INTERNET DE LAS COSAS",
        ""
    ))
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(5.dp)
    ){
        items(listaCalificaciones){
            var color1= colorResource(id = R.color.desempenioE)
            var color2= colorResource(id = R.color.acreditacionO)
            if(it.calificacion.toInt()<96){
                color1= colorResource(id = R.color.desempenioN)
            }
            if(it.calificacion.toInt()<90){
                color1= colorResource(id = R.color.desempenioB)
            }
            if(it.calificacion.toInt()<70){
                color1= colorResource(id = R.color.desempenioE)
            }
            if(it.acreditacion=="Repetición"){
                color2= colorResource(id = R.color.acreditacionR)
            }else if(it.acreditacion=="Especial"){
                color2= colorResource(id = R.color.acreditacionE)
            }
            Card{
                Row(
                    modifier = Modifier.fillMaxWidth()
                ){
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color1)
                    ){
                        Text(it.materia, fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center)
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
                        Text(text = "Calificación: " + it.calificacion)
                    }
                    Row(
                        modifier = Modifier.weight(0.6f)
                    ){
                        Text(text = "Acreditación: " + it.acreditacion)
                    }
                }
            }
        }
    }
}

data class Final(
    var calificacion:String,
    var acreditacion:String,
    var grupo:String,
    var materia:String,
    var observaciones:String
)