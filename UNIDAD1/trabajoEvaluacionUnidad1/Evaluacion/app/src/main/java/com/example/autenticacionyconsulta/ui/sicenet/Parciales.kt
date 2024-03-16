package com.example.autenticacionyconsulta.ui.sicenet

import android.annotation.SuppressLint
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
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Parciales : ComponentActivity() {
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Parciales(
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
                            text = stringResource(id = R.string.calificacionParcial)
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
        ParcialesP(viewmodel)
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParcialesP(
    viewmodel: AppView
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
        presentacionParciales(viewmodel,it)
    }
}
@Composable
fun presentacionParciales(
    viewmodel: AppView,
    paddingValues: PaddingValues
) {
    LazyColumn(
        contentPadding = paddingValues,
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(viewmodel.parciales){
            Text(
                text =it.Materia,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .background(Color(81, 189, 126, 255))
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(5.dp))
            Card (
                modifier = Modifier
                    .fillMaxWidth(.8f)
                    .padding(10.dp),
                shape = RoundedCornerShape(10.dp),
            ){
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = it.C1,
                        fontSize = 23.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 5.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(7.dp))
            if(it.C2!=""){
                Card (
                    modifier = Modifier
                        .fillMaxWidth(.8f)
                        .padding(10.dp),
                    shape = RoundedCornerShape(10.dp),
                ){
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = it.C2.toString(),
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(7.dp))
            }
            if(it.C3!=""){
                Card (
                    modifier = Modifier
                        .fillMaxWidth(.8f)
                        .padding(10.dp),
                    shape = RoundedCornerShape(10.dp),
                        ){
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = it.C3.toString(),
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(7.dp))
            }
            if(it.C4!=""){
                Card (
                    modifier = Modifier
                        .fillMaxWidth(.8f)
                        .padding(10.dp),
                    shape = RoundedCornerShape(10.dp),
                ){
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = it.C4.toString(),
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(7.dp))
            }
            if(it.C5!=""){
                Card (
                    modifier = Modifier
                        .fillMaxWidth(.8f)
                        .padding(10.dp),
                    shape = RoundedCornerShape(10.dp),
                ){
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = it.C5.toString(),
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(7.dp))
            }
            if(it.C6!=""){
                Card (
                    modifier = Modifier
                        .fillMaxWidth(.8f)
                        .padding(10.dp),
                    shape = RoundedCornerShape(10.dp),
                ){
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = it.C6,
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(7.dp))
            }
            if(it.C7!=""){
                Card (
                    modifier = Modifier
                        .fillMaxWidth(.8f)
                        .padding(10.dp),
                    shape = RoundedCornerShape(10.dp),
                ){
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = it.C7,
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(7.dp))
            }
            if(it.C8!=""){
                Card (
                    modifier = Modifier
                        .fillMaxWidth(.8f)
                        .padding(10.dp),
                    shape = RoundedCornerShape(10.dp),
                ){
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = it.C8,
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(7.dp))
            }
            if(it.C9!=""){
                Card (
                    modifier = Modifier
                        .fillMaxWidth(.8f)
                        .padding(10.dp),
                    shape = RoundedCornerShape(10.dp),
                ){
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = it.C9,
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(7.dp))
            }
            if(it.C10!=""){
                Card (
                    modifier = Modifier
                        .fillMaxWidth(.8f)
                        .padding(10.dp),
                    shape = RoundedCornerShape(10.dp),
                ){
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = it.C10,
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(7.dp))
            }
            if(it.C11!=""){
                Card (
                    modifier = Modifier
                        .fillMaxWidth(.8f)
                        .padding(10.dp),
                    shape = RoundedCornerShape(10.dp),
                ){
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = it.C11,
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(7.dp))
            }
            if(it.C12!=""){
                Card (
                    modifier = Modifier
                        .fillMaxWidth(.8f)
                        .padding(10.dp),
                    shape = RoundedCornerShape(10.dp),
                ){
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = it.C12,
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(7.dp))
            }
            if(it.C13!=""){
                Card (
                    modifier = Modifier
                        .fillMaxWidth(.8f)
                        .padding(10.dp),
                    shape = RoundedCornerShape(10.dp),
                ){
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = it.C13,
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(7.dp))
            }
        }
    }
    if(viewmodel.sinParciales){
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
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
                    Text(text = "No se encontraron datos")

                }
            }
        }
    }
}


