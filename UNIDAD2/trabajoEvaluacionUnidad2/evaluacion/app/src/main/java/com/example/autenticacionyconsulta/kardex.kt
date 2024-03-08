package com.example.autenticacionyconsulta

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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.autenticacionyconsulta.ViewModel.AppView
import com.example.autenticacionyconsulta.ui.theme.AutenticacionYConsultaTheme
import kotlinx.coroutines.launch

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
    lineamiento:String?,
    viewmodel: AppView = viewModel(factory = AppView.Factory)
){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.kardex)
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
            viewmodel.getKardex(lineamiento)
        }
        KardexMateria(viewmodel = viewmodel,it)
    }
}

@Preview
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KardexPreview(
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
                        Spacer(modifier = Modifier.weight(.1f))
                        Box (
                            modifier= Modifier
                                .background(
                                    color = Color(255, 158, 142),
                                    shape = CircleShape
                                )
                                .padding(start = 10.dp, end = 10.dp)
                        ){
                            Text(
                                text = "Información de 12/02/23",
                                fontSize = 14.sp
                            )
                        }

                    }

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

    }
}

@Composable
private fun KardexMateria(
   viewmodel: AppView,
   paddingValues:PaddingValues
){
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(10.dp),
        contentPadding = paddingValues
    ){
        var colorFondo= Color(2,2,2)
        var colorAcreditacion= Color(2,2,2)
        var colorDesempeno= Color(2,2,2)
        items(viewmodel.kardex){
            if(it.Calif>=95){//excelente
                colorFondo= colorResource(id = R.color.fondoE)
                colorDesempeno= colorResource(id = R.color.desempenioE)
            }else if(it.Calif<95 && it.Calif>=90){//bueno
                colorFondo= colorResource(id = R.color.fondoB)
                colorDesempeno= colorResource(id = R.color.desempenioB)
            }else if(it.Calif<90 && it.Calif>=80){//normal
                colorFondo= colorResource(id = R.color.fondoN)
                colorDesempeno= colorResource(id = R.color.desempenioN)
            }else{//regular
                colorFondo= colorResource(id = R.color.fondoR)
                colorDesempeno= colorResource(id = R.color.desempenioR)
            }


            //Diseño de car
            Card{
                Row(
                    modifier = Modifier.fillMaxWidth().heightIn(min = 25.dp)
                ){
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .weight(0.9f)
                            .background(colorDesempeno)
                            .padding(start = 4.dp)
                    ){
                        Text(it.Materia, fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center, fontSize = 24.sp)
                    }
                    Column(
                        modifier = Modifier
                            .weight(0.1f)
                            .background(colorAcreditacion)
                            .fillMaxHeight()
                    ){
                        Text("")
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colorFondo)
                        .heightIn(min = 60.dp)
                        .padding(start = 5.dp)
                ){
                    Row(
                        modifier = Modifier.weight(0.9f)
                    ){
                        Text(text = "Calificación: " + it.Calif,
                            fontSize = 21.sp,
                            fontStyle = FontStyle.Italic)
                    }
                    Row(
                        modifier = Modifier.weight(0.9f)
                    ){
                        Text(text = "Clave: " + it.ClvMat,
                            fontSize = 21.sp,
                            fontStyle = FontStyle.Italic )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colorFondo)
                        .padding(start = 5.dp)
                ){
                    Row(
                        modifier = Modifier.weight(0.9f)
                    ){
                        Text(text = "Oficial: " + it.ClvOfiMat,
                            fontSize = 21.sp,
                            fontStyle = FontStyle.Italic)
                    }
                    Row(
                        modifier = Modifier.weight(0.9f)
                    ){
                        Text(text = "Cdts: " + it.Cdts,
                            fontSize = 21.sp,
                            fontStyle = FontStyle.Italic)
                    }
                }
            }
        }
        item{
            Card(){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 5.dp)
                        .background(Color.Green)
                ){
                    Text("PROMEDIO GENERAL: "+viewmodel.resumenKardex.PromedioGral,
                        fontWeight = FontWeight.Bold,fontSize = 25.sp)
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 5.dp)
                ){
                    Row(
                        modifier = Modifier.weight(0.5f)
                    ){
                        Text("Creditos Terminadas: "+viewmodel.resumenKardex.CdtsAcum,fontSize = 18.sp)
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
                        Text("Materias cursadas: "+viewmodel.resumenKardex.MatCursadas,fontSize = 18.sp)
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
                        Text("Materias aprobadas: "+viewmodel.resumenKardex.MatAprobadas,fontSize = 18.sp)
                    }
                }
            }
        }
    }
}




//DESEMPEÑO:
//EXCELENTE: VERDE(46,204,33) (130,224,170)
//NOTABLE: AMARILLO(241,196,15) (247,220,111)
//BUENO: NARANJA(243,156,18) (248,196,113)
//Repeticion: #7D3C98
//Especial: #5D6D7E

//ACREDITACIÓN
//ORDINARIO: AZUL(46,134,193) / REPETICIÓN: MORADO(125,60,152)
//ESPECIAL: GRIS(93,109,126)