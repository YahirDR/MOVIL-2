package com.example.autenticacionyconsulta

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.autenticacionyconsulta.navigation.AppScreens
import com.example.autenticacionyconsulta.ui.theme.AutenticacionYConsultaTheme

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

@Composable
fun dataStudent(
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
            ) {
                Text(
                    text = "--- DATOS DEL ALUMNO ---",
                    fontSize = 19.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold)
                Text(
                    text = ""+alumnoInfo?.get(13)?.split("=")?.get(1),
                    fontSize = 21.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold)
                Image(
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = "",
                    modifier = Modifier.size(120.dp))
                Spacer(modifier = Modifier.height(5.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Fecha de reinscripción: "+alumnoInfo?.get(0)?.split("=")?.get(1))
                    Text(text = "Mod. Educativo: "+alumnoInfo?.get(1)?.split("=")?.get(1))
                    Text(text = "Adeudo: "+ validarCampos(alumnoInfo?.get(2)?.split("=")?.get(1)))
                    Text(text = "Adeudo descripción: "+ validarCampos(alumnoInfo?.get(4)?.split("=")?.get(1)))
                    Text(text = "Inscrito: "+ validarCampos(alumnoInfo?.get(5)?.split("=")?.get(1)))
                    Text(text = "Estatus: "+alumnoInfo?.get(6)?.split("=")?.get(1))
                    Text(text = "Semestre actual: "+alumnoInfo?.get(7)?.split("=")?.get(1))
                    Text(text = "Creditos acumulados: "+alumnoInfo?.get(8)?.split("=")?.get(1))
                    Text(text = "Creditos actuales: "+alumnoInfo?.get(9)?.split("=")?.get(1))
                    Text(text = "Carrera: "+alumnoInfo?.get(11)?.split("=")?.get(1))
                    Text(text = "Matricula: "+alumnoInfo?.get(14)?.split("=")?.get(1))
                }
                Spacer(modifier = Modifier.height(30.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Especialidad: ",
                        fontSize = 17.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold)
                    Text(text = ""+alumnoInfo?.get(10)?.split("=")?.get(1),
                        fontSize = 13.sp,
                        fontFamily = FontFamily.SansSerif)
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun principalHome(
    navController: NavController,
    text:String?
){
    val infoAlumno=text?.split("(",")")?.get(1)?.split(",")
    val lineamiento=infoAlumno?.get(12)?.split("=")?.get(1)
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.principal))
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.log_out),
                            contentDescription = "")
                    }
                })
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(route = AppScreens.Kardex.route+lineamiento)},
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.kardex) ,
                            contentDescription = "",
                            modifier = Modifier.size(32.dp)
                        )
                    },
                    label ={
                        Text(
                            text = stringResource(id = R.string.kardex),
                            fontSize = 13.sp)
                    }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(route = AppScreens.Horario.route) },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.horario) ,
                            contentDescription = "",
                            modifier = Modifier.size(32.dp)
                        )
                    },
                    label ={
                        Text(
                            text = stringResource(id = R.string.horario),
                            fontSize = 13.sp)
                    }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {  },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.parciales) ,
                            contentDescription = "",
                            modifier = Modifier.size(32.dp)
                        )
                    },
                    label ={
                        Text(
                            text = stringResource(id = R.string.calificacionParcial),
                            fontSize = 13.sp)
                    }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {  },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.finales) ,
                            contentDescription = "",
                            modifier = Modifier.size(32.dp)
                        )
                    },
                    label ={
                        Text(
                            text = stringResource(id = R.string.calificacionFinal),
                            fontSize = 13.sp)
                    }
                )
            }
        }
    ){
        dataStudent(text)
    }
}

@Preview
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun principalHomePreview(

){
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.principal))
                },
                navigationIcon = {
                    IconButton(
                        onClick = {  }) {
                        Icon(
                            painter = painterResource(id = R.drawable.log_out),
                            contentDescription = "")
                    }
                })
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = false,
                    onClick = {  },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.kardex) ,
                            contentDescription = "",
                            modifier = Modifier.size(32.dp)
                        )
                    },
                    label ={
                        Text(
                            text = stringResource(id = R.string.kardex),
                            fontSize = 13.sp)
                    }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {  },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.horario) ,
                            contentDescription = "",
                            modifier = Modifier.size(32.dp)
                        )
                    },
                    label ={
                        Text(
                            text = stringResource(id = R.string.horario),
                            fontSize = 13.sp)
                    }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {  },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.parciales) ,
                            contentDescription = "",
                            modifier = Modifier.size(32.dp)
                        )
                    },
                    label ={
                        Text(
                            text = stringResource(id = R.string.calificacionParcial),
                            fontSize = 13.sp)
                    }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {  },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.finales) ,
                            contentDescription = "",
                            modifier = Modifier.size(32.dp)
                        )
                    },
                    label ={
                        Text(
                            text = stringResource(id = R.string.calificacionFinal),
                            fontSize = 13.sp)
                    }
                )
            }
        }
    ){
        dataStudentPreview()
    }
}

@Composable
fun dataStudentPreview(){
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
            ) {
                Text(
                    text = "JOSE MARTIN GARCIA MARTINEZ",
                    fontSize = 21.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold)
                Image(
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = "",
                    modifier = Modifier.size(120.dp))
                Spacer(modifier = Modifier.height(5.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Fecha de reinscripción: ")
                    Text(text = "Mod. Educativo: ")
                    Text(text = "Adeudo: ")
                    Text(text = "Adeudo descripción: ")
                    Text(text = "Inscrito: ")
                    Text(text = "Estatus: ")
                    Text(text = "Semestre actual: ")
                    Text(text = "Creditos acumulados: ")
                    Text(text = "Creditos actuales: ")
                    Text(text = "Carrera: ")
                    Text(text = "Matricula: ")
                }
                Spacer(modifier = Modifier.height(30.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Especialidad: ",
                        fontSize = 17.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold)
                    Text(text = "FSHJFHJDGFHDGSFHGDFHDGF",
                        fontSize = 13.sp,
                        fontFamily = FontFamily.SansSerif)
                }

            }
        }
    }
}

fun validarCampos(dato:String?):String?{
    if(dato.equals("")){
        return "Ninguno"
    }else if(dato.equals("true")){
        return "Si"
    }else if(dato.equals("false")){
        return "No"
    }else{
        return dato
    }
}