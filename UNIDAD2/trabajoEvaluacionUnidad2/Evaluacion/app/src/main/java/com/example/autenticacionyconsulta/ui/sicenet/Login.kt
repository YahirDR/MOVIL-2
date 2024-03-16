package com.example.autenticacionyconsulta.ui.sicenet

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.autenticacionyconsulta.R
import com.example.autenticacionyconsulta.ViewModel.AppView
import com.example.autenticacionyconsulta.ViewModel.WorkerAccessState
import com.example.autenticacionyconsulta.navigation.AppNavigation
import com.example.autenticacionyconsulta.navigation.AppScreens
import com.example.autenticacionyconsulta.ui.theme.AutenticacionYConsultaTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket
import java.time.LocalDate

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AutenticacionYConsultaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewmodel: AppView= viewModel(factory = AppView.Factory)
                    AppNavigation(viewmodel)
                }
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun loginApp(
    navController: NavController,
    viewmodel:AppView
){
    val uiState by viewmodel.workerUiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val scope= rememberCoroutineScope()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(40.dp)
            .fillMaxSize()

    ) {
        if(viewmodel.errorLogin){
            Spacer(modifier = Modifier.height(45.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Icon(
                    painter = painterResource(id = R.drawable.warning),
                    contentDescription = "",
                    modifier=Modifier.size(35.dp),
                    tint = Color(255,124,112))
                Text(
                    text = "Contraseña o usuario incorrecto",
                    fontSize = 14.sp,
                    color=Color(255,158,142))

            }
        }

        OutlinedTextField(
            value = viewmodel.matricula,
            label = { Text(text = "Introducce tu número de control") },
            onValueChange = {
                viewmodel.updateMatricula(it)
                viewmodel.updateErrorLogin(false)
                viewmodel.updateErrorInternet(false)
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(25.dp))

        OutlinedTextField(
            value = viewmodel.password,
            label = { Text(text = "Introduce tu contraseña") },
            onValueChange = {
                viewmodel.updatePassword(it)
                viewmodel.updateErrorLogin(false)
                viewmodel.updateErrorInternet(false)
            },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
        )
        Spacer(modifier = Modifier.height(35.dp))

        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {

            ExposedDropdownMenuBox(
                expanded = viewmodel.expandido,
                onExpandedChange = {viewmodel.updateExpandido(it)}
            ) {
                OutlinedTextField(
                    value = viewmodel.tipoUsuario,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text(stringResource(id = R.string.tipoUsuario)) },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = false) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                )
                ExposedDropdownMenu(
                    expanded = viewmodel.expandido,
                    onDismissRequest = { viewmodel.expandido = false })
                {
                    var alumno = stringResource(id = R.string.alumno)
                    var docente = stringResource(id = R.string.docente)
                    DropdownMenuItem(
                        text = { Text(alumno) },
                        onClick = {
                            viewmodel.updateTipoUsuario(alumno)
                            viewmodel.expandido=false
                            viewmodel.updateErrorLogin(false)
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(docente) },
                        onClick = {
                            viewmodel.updateTipoUsuario(docente)
                            viewmodel.expandido=false
                            viewmodel.updateErrorLogin(false)
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(25.dp))
        
        Row(
            modifier=Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            //ACCEDER
            Button(
                onClick = {
                    if (!viewmodel.matricula.equals("") && !viewmodel.password.equals("")){
                        viewmodel.updateDaClicBoton(true)
                        scope.launch {
                            if(existeConexionInternet(context)){
                                viewmodel.updateInternetDisponible(true)
                                viewmodel.getAccess()
                            } else{
                                viewmodel.updateInternetDisponible(false)
                                if(viewmodel.getAccessDB()){
                                    navController.navigate(route = AppScreens.Info.route)
                                }
                            }
                        }
                    }else{
                        viewmodel.updateErrorLogin(true)
                    }
                },

            ) {
                Text(text = "Acceder",
                    modifier=Modifier.width(120.dp),
                    textAlign = TextAlign.Center)
            }
        }

        if(viewmodel.errorInternet){
            Spacer(modifier = Modifier.height(45.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Icon(
                    painter = painterResource(id = R.drawable.warning),
                    contentDescription = "",
                    modifier=Modifier.size(35.dp),
                    tint = Color(255,124,112))
                Text(
                    text = "no tienes internet",
                    fontSize = 14.sp,
                    color=Color(255,158,142))
            }
        }
        when (uiState) {
            is WorkerAccessState.Default -> {
            }
            is WorkerAccessState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.padding(10.dp))
            }
            is WorkerAccessState.Complete -> {
                if(viewmodel.internetDisponible){
                    if(viewmodel.proporcionarAcceso((uiState as WorkerAccessState.Complete).outputUno, (uiState as WorkerAccessState.Complete).outputDos)){
                        viewmodel.updateAcceso(true)
                        viewmodel.updateFecha(LocalDate.now().toString())
                        pasarAventana(viewmodel, navController)
                    }else{
                        viewmodel.updateErrorLogin(true)
                    }
                }
            }
        }

    }
}

fun pasarAventana(viewmodel: AppView,navController: NavController){
    if(viewmodel.daClicBoton && viewmodel.acceso){
        viewmodel.updateDaClicBoton(false)
        viewmodel.updateAcceso(false)
        viewmodel.updateOffline(false)
        navController.navigate(route = AppScreens.Info.route)
    }
}
//Deterctar si hay internet
suspend fun existeConexionInternet(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

    if (networkCapabilities != null) {
        // Verifica si hay conexión a través de Wi-Fi
        if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ) {
            return verificaAccesoInternet()
        }
    }
    return false
}

suspend fun verificaAccesoInternet(): Boolean {
    return withContext(Dispatchers.IO) {
        try {

            val timeoutMs = 15000 // Tiempo de espera en milisegundos
            val socket = Socket()
            socket.connect(InetSocketAddress("google.com", 80), timeoutMs)
            socket.close()
            true
        } catch (e: IOException) {
            false
        }
    }
}



