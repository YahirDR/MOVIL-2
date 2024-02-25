package com.example.evaluacionU1

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.evaluacionU1.ViewModel.LoginView
import com.example.evaluacionU1.navigation.AppNavigation
import com.example.evaluacionU1.navigation.AppPantallas
import com.example.evaluacionU1.ui.theme.AutenticacionYConsultaTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AutenticacionYConsultaTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun loginApp(
    navController: NavController,
    viewmodel: LoginView= viewModel(factory = LoginView.Factory)
){
    val scope= rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green) // Color de fondo
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopAppBar(
                title = { Text(text = "Sicenet") },
                modifier = Modifier.fillMaxWidth().background(color = Color.White)
            )
            Column (
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ){
                Image(
                    painter = painterResource(id = R.drawable.mexico),
                    contentDescription = "Icono de usuario" ,
                    modifier = Modifier.size(width = 480.dp, height = 280.dp)
                )
                Spacer(modifier = Modifier.height(25.dp))
                if(viewmodel.errorLogin){
                    Spacer(modifier = Modifier.height(45.dp))
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ){
                        Text(
                            text = "Error: Contraseña o Usuario incorrectos",
                            fontSize = 20.sp,
                            color = Color.Red
                        )

                    }
                }
                Spacer(modifier = Modifier.height(25.dp))
                Text(
                    text = "Número de control:",
                    fontSize = 30.sp,
                    fontFamily = FontFamily.Cursive,
                    fontWeight = FontWeight.Black,
                    color = Color.Black
                )

            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.LightGray)
            ){
                OutlinedTextField(
                    value = viewmodel.matricula,

                    onValueChange = {
                        viewmodel.updateMatricula(it)
                        viewmodel.updateErrorLogin(false)
                    },
                    modifier = Modifier.fillMaxWidth().border(
                        width = 7.dp, // Grosor del borde
                        color = Color.Black, // Color del borde
                        shape = RoundedCornerShape(8.dp) // Forma de los bordes
                    )
                )
            }
            Spacer(modifier = Modifier.height(25.dp))
            Row (
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ){
                Text(
                    text = "Contraseña:",
                    fontSize = 30.sp,
                    fontFamily = FontFamily.Cursive,
                    fontWeight = FontWeight.Black,
                    color = Color.Red
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.LightGray) // Cambia Color.LightGray por el color que desees
            ){
                OutlinedTextField(
                    value = viewmodel.password,

                    onValueChange = {
                        viewmodel.updatePassword(it)
                        viewmodel.updateErrorLogin(false)
                    },
                    modifier = Modifier.fillMaxWidth().border(
                        width = 7.dp, // Grosor del borde
                        color = Color.Black, // Color del borde
                        shape = RoundedCornerShape(8.dp) // Forma de los bordes
                    )
                )
            }
            Spacer(modifier = Modifier.height(25.dp))
            Row(
                modifier=Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        if (!viewmodel.matricula.equals("") && !viewmodel.password.equals("")){
                            scope.launch {
                                if(viewmodel.getAccess(viewmodel.matricula,viewmodel.password)){
                                    viewmodel.updateErrorLogin(false)
                                    var info=viewmodel.getInfo()
                                    var encodedInfo = Uri.encode(info)
                                    navController.navigate(route = AppPantallas.Info.route+encodedInfo)
                                }else{
                                    viewmodel.updateErrorLogin(true)
                                }
                            }

                        }
                    },

                    ) {
                    Text(text = "Iniciar Sesión",
                        modifier=Modifier.width(120.dp),
                        textAlign = TextAlign.Center)
                }
            }


        }
    }

}




