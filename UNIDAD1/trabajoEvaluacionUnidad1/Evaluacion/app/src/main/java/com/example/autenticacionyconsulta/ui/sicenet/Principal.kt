package com.example.autenticacionyconsulta.ui.sicenet

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.autenticacionyconsulta.R
import com.example.autenticacionyconsulta.ViewModel.AppView
import com.example.autenticacionyconsulta.ViewModel.WorkerAccessState
import com.example.autenticacionyconsulta.navigation.AppScreens
import com.example.autenticacionyconsulta.ui.theme.AutenticacionYConsultaTheme
import kotlinx.coroutines.launch

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
    viewmodel: AppView
){
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
                    text = "¡Bienvenido!",
                    fontSize = 19.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold)
                Text(
                    text = viewmodel.infoAlumno.nombre,
                    fontSize = 21.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(5.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Fecha de reinscripción",
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold)
                    Text(text = ""+viewmodel.infoAlumno.fechaReins)
                    Text(
                        text = "Modulo educativo:",
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold)
                    Text(text = ""+viewmodel.infoAlumno.modEducativo)
                    Text(
                        text = "Adeudo:",
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold)
                    Text(text = ""+ validarCampos(viewmodel.infoAlumno.adeudo))
                    Text(
                        text = "Adeudo descriptivo:",
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold)
                    Text(text = ""+ validarCampos(viewmodel.infoAlumno.adeudoDescripcion))
                    Text(
                        text = "Inscrito:",
                        fontSize = 15.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold)
                    Text(text = ""+ validarCampos(viewmodel.infoAlumno.inscrito.toString()))
                    Text(
                        text = "Estatus:",
                        fontSize = 15.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold)
                    Text(text = ""+viewmodel.infoAlumno.estatus)
                    Text(
                        text = "Semestre:",
                        fontSize = 15.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold)
                    Text(text = ""+viewmodel.infoAlumno.semActual)
                    Text(
                        text = "Cre. acumulados::",
                        fontSize = 15.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold)
                    Text(text = ""+viewmodel.infoAlumno.cdtosAcumulados)
                    Text(
                        text = "Cre. actuales:",
                        fontSize = 15.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold)
                    Text(text = ""+viewmodel.infoAlumno.cdtosActuales)
                    Text(
                        text = "Carrera:",
                        fontSize = 15.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold)
                    Text(text = ""+viewmodel.infoAlumno.carrera)
                    Text(
                        text = "Número de control:",
                        fontSize = 15.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold)
                    Text(text = ""+viewmodel.infoAlumno.matricula)
                }
                Spacer(modifier = Modifier.height(30.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Especialidad: ",
                        fontSize = 17.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold)
                    Text(text = viewmodel.infoAlumno.especialidad,
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
    viewmodel: AppView
){
    val uiStateFinales by viewmodel.workerUiStateFinales.collectAsStateWithLifecycle()
    val uiStateParciales by viewmodel.workerUiStateParciales.collectAsStateWithLifecycle()
    val uiStateHorario by viewmodel.workerUiStateHorario.collectAsStateWithLifecycle()
    val uiStateKardex by viewmodel.workerUiStateKardex.collectAsStateWithLifecycle()
    val uiStateKardex2 by viewmodel.workerUiStateKardex2.collectAsStateWithLifecycle()
    val uiStateKardexR by viewmodel.workerUiStateKardexResumen.collectAsStateWithLifecycle()
    val scope= rememberCoroutineScope()
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.principal)
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
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Green)

            )
        },
        //ACEDER A KARDEX, CARGA ACADEMICA, CALIFICACIONES PARCIALES Y FINALES
        bottomBar = {
            NavigationBar(modifier = Modifier.background(Color.Blue)) {
                NavigationBarItem(
                    selected = false,
                    onClick = {
                        if(viewmodel.internetDisponible){
                            viewmodel.getKardex()
                            viewmodel.updateDaClicKardex(true)
                        }else{
                            scope.launch {
                                viewmodel.getKardexDB()
                            }

                            navController.navigate(route = AppScreens.Kardex.route)
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Info ,
                            contentDescription = "",
                            modifier = Modifier.size(32.dp)
                        )
                    },
                    label ={
                        Text(
                            text = stringResource(id = R.string.kardex),
                            fontSize = 13.sp,
                            color = Color.Black)
                    },
                    modifier = Modifier.background(Color.Green)
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {
                        if(viewmodel.internetDisponible){
                            viewmodel.getHorario()
                            viewmodel.updateDaClicHorario(true)
                        }else{
                            scope.launch {
                                viewmodel.getHorarioDB()
                            }
                            navController.navigate(route = AppScreens.Horario.route)
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.DateRange ,
                            contentDescription = "",
                            modifier = Modifier.size(32.dp)
                        )
                    },
                    label ={
                        Text(
                            text = stringResource(id = R.string.horario),
                            fontSize = 13.sp,
                            color = Color.Black)
                    },
                    modifier = Modifier.background(Color.Green)
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {
                        if(viewmodel.internetDisponible){
                            viewmodel.getParciales()
                            viewmodel.updateDaClicParciales(true)
                        }else{
                            scope.launch {
                                viewmodel.getParcialesDB()
                            }
                            navController.navigate(route = AppScreens.Parciales.route)
                        }

                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.List ,
                            contentDescription = "",
                            modifier = Modifier.size(32.dp)
                        )
                    },
                    label ={
                        Text(
                            text = stringResource(id = R.string.calificacionParcial),
                            fontSize = 13.sp,
                            color = Color.Black)
                    },
                    modifier = Modifier.background(Color.Green)
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {
                        if(viewmodel.internetDisponible){
                            viewmodel.getFinales()
                            viewmodel.updateDaClicFinales(true)
                        }else{
                            scope.launch {
                                viewmodel.getFinalesDB()
                            }
                            navController.navigate(route = AppScreens.Finales.route)
                        }

                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Menu ,
                            contentDescription = "",
                            modifier = Modifier.size(32.dp)
                        )
                    },
                    label ={
                        Text(
                            text = stringResource(id = R.string.calificacionFinal),
                            fontSize = 13.sp,
                            color = Color.Black
                        )
                    },
                    modifier = Modifier.background(Color.Green)
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {navController.popBackStack()},
                    icon = {
                        Icon(
                            imageVector = Icons.Default.ExitToApp ,
                            contentDescription = "",
                            modifier = Modifier.size(32.dp)
                        )
                    },
                    label ={
                        Text(
                            text = "Salir",
                            fontSize = 13.sp,
                            color = Color.Black
                        )
                    },
                    modifier = Modifier.background(Color.Red)
                )

            }
        }
    ){
        dataStudent(viewmodel)
        //logica de los diferentes estados de los workers
        when (uiStateFinales) {
            // Si el estado del Worker es Default, no se realiza ninguna acción.
            is WorkerAccessState.Default -> {
            }
            is WorkerAccessState.Loading -> {
            }
            is WorkerAccessState.Complete -> {
                if(viewmodel.daClicFinales){
                    viewmodel.updateDaClicFinales(false)
                    viewmodel.actualizarFinales((uiStateFinales as WorkerAccessState.Complete).outputUno)
                    viewmodel.updateSinFinales(false)
                    navController.navigate(route = AppScreens.Finales.route)
                }
            }
        }
        //Estudiar
        when (uiStateParciales) {
            is WorkerAccessState.Default -> {
            }
            is WorkerAccessState.Loading -> {
            }
            is WorkerAccessState.Complete -> {
                if(viewmodel.daClicParciales){
                    viewmodel.updateDaClicParciales(false)

                    viewmodel.actualizarParciales((uiStateParciales as WorkerAccessState.Complete).outputUno)
                    viewmodel.updateSinParciales(false)
                    navController.navigate(route = AppScreens.Parciales.route)
                }
            }
        }
        when (uiStateHorario) {
            is WorkerAccessState.Default -> {
            }
            is WorkerAccessState.Loading -> {
            }
            is WorkerAccessState.Complete -> {
                if(viewmodel.daClicHorario){
                    viewmodel.updateDaClicHorario(false)
                    viewmodel.actualizarHorario((uiStateHorario as WorkerAccessState.Complete).outputUno)
                    viewmodel.updateSinHorario(false)
                    navController.navigate(route = AppScreens.Horario.route)
                }
            }
        }
        when (uiStateKardex) {
            is WorkerAccessState.Default -> {
            }
            is WorkerAccessState.Loading -> {
            }
            is WorkerAccessState.Complete -> {
                if(viewmodel.daClicKardex){
                    if(viewmodel.kardex1Actualizado==false) {
                        viewmodel.limpiarKardex()
                        viewmodel.actualizarKardex1((uiStateKardex as WorkerAccessState.Complete).outputUno)
                        viewmodel.updateSinKardex(false)
                    }
                }
            }
        }
        when (uiStateKardex2) {
            is WorkerAccessState.Default -> {
            }
            is WorkerAccessState.Loading -> {
            }
            is WorkerAccessState.Complete -> {
                if(viewmodel.kardex1Finalizado){
                    if(viewmodel.kardex2Actualizado==false){
                        viewmodel.actualizarKardex2((uiStateKardex2 as WorkerAccessState.Complete).outputUno)
                    }

                }
            }
        }
        when (uiStateKardexR) {
            is WorkerAccessState.Default -> {
            }
            is WorkerAccessState.Loading -> {
            }
            is WorkerAccessState.Complete -> {
                viewmodel.actualizarResumenKardex((uiStateKardexR as WorkerAccessState.Complete).outputUno)
                if(viewmodel.kardex1Finalizado && viewmodel.kardex2Finalizado && viewmodel.daClicKardex){
                    viewmodel.updateDaClicKardex(false)
                    navController.navigate(route = AppScreens.Kardex.route)
                }
            }
        }
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
                    text = "",
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

fun validarCampos(dato:String):String{
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