package com.example.autenticacionyconsulta

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.autenticacionyconsulta.ui.theme.AutenticacionYConsultaTheme

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
    viewmodel: AppView = viewModel(factory = AppView.Factory)
){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.calificacionParcial)
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
            viewmodel.getParciales()
        }
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
                modifier = Modifier.fillMaxWidth(.8f)
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
            if(it.C2!=null){
                Card (
                    modifier = Modifier.fillMaxWidth(.8f)
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
            if(it.C3!=null){
                Card (
                        modifier = Modifier.fillMaxWidth(.8f)
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
            if(it.C4!=null){
                Card (
                    modifier = Modifier.fillMaxWidth(.8f)
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
            if(it.C5!=null){
                Card (
                    modifier = Modifier.fillMaxWidth(.8f)
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
            if(it.C6!=null){
                Card (
                    modifier = Modifier.fillMaxWidth(.8f)
                ){
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = it.C6.toString(),
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(7.dp))
            }
            if(it.C7!=null){
                Card (
                    modifier = Modifier.fillMaxWidth(.8f)
                ){
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = it.C7.toString(),
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(7.dp))
            }
            if(it.C8!=null){
                Card (
                    modifier = Modifier.fillMaxWidth(.8f)
                ){
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = it.C8.toString(),
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(7.dp))
            }
            if(it.C9!=null){
                Card (
                    modifier = Modifier.fillMaxWidth(.8f)
                ){
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = it.C9.toString(),
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(7.dp))
            }
            if(it.C10!=null){
                Card (
                    modifier = Modifier.fillMaxWidth(.8f)
                ){
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = it.C10.toString(),
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(7.dp))
            }
            if(it.C11!=null){
                Card (
                    modifier = Modifier.fillMaxWidth(.8f)
                ){
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = it.C11.toString(),
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(7.dp))
            }
            if(it.C12!=null){
                Card (
                    modifier = Modifier.fillMaxWidth(.8f)
                ){
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = it.C12.toString(),
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(7.dp))
            }
            if(it.C13!=null){
                Card (
                    modifier = Modifier.fillMaxWidth(.8f)
                ){
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = it.C13.toString(),
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
}


