package com.example.autenticacionyconsulta

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.autenticacionyconsulta.ViewModel.AppView
import com.example.autenticacionyconsulta.modelos.HorarioAlumno
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
    viewmodel: AppView = viewModel(factory = AppView.Factory)
){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.horario)
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
            viewmodel.getHorario()
            mostrarMateriasDia(viewmodel)
        }
        presentacion(viewmodel = viewmodel,it)
    }
}

@Composable
fun presentacion(
    viewmodel: AppView,
    paddingValues: PaddingValues
){

    LazyColumn(
        contentPadding = paddingValues,
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item{
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
            Card (
                modifier = Modifier.fillMaxWidth(.8f)
            ){
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ){
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
        item{
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
            Card (
                modifier = Modifier.fillMaxWidth(.8f)
            ){
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ){
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
        item{
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
            Card (
                modifier = Modifier.fillMaxWidth(.8f)
            ){
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ){
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
        item{
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
            Card (
                modifier = Modifier.fillMaxWidth(.8f)
            ){
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ){
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
        item{
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
            Card (
                modifier = Modifier.fillMaxWidth(.8f)
            ){
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ){
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
}

fun mostrarMateriasDia(
    viewmodel: AppView
){
    if(viewmodel.noValido){
        viewmodel.horario.forEach{
            if(it.Lunes.length>0)
                viewmodel.lunes.add(it)
            if(it.Martes.length>0)
                viewmodel.martes.add(it)
            if(it.Miercoles.length>0)
                viewmodel.miercoles.add(it)
            if(it.Jueves.length>0)
                viewmodel.jueves.add(it)
            if(it.Viernes.length>0)
                viewmodel.viernes.add(it)
        }
    }
}

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HorarioP(

){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.horario)
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {  }) {
                        Icon(
                            painter = painterResource(id = R.drawable.back),
                            contentDescription = "")
                    }
                })
        }
    ) {
        presentacionHorario(it)
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

