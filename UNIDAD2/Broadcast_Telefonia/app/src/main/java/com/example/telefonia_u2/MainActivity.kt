package com.example.telefonia_u2

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.telefonia_u2.ui.theme.TelefoniaTheme
import com.example.telefonia_u2.viewModel.AppView

class MainActivity : ComponentActivity() {
    lateinit var view: AppView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TelefoniaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    constestarTelefono()
                    view = ViewModelProvider(this).get(AppView::class.java)
                }
            }
        }
    }
}

object SharedViewModel {
    lateinit var viewModel: AppView
}


@Composable
fun constestarTelefono(
    viewModel: AppView = viewModel(factory = AppView.Factory)
) {
    SharedViewModel.viewModel = viewModel
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ){
        Row{
            Text(text = "Receptor de llamadas")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Telefono:")
        OutlinedTextField(
            value = viewModel.numero,
            label = { Text(text = "Introduce el teléfono") },
            onValueChange = { viewModel.actualizarTelefono(it) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Mensaje:")
        OutlinedTextField(
            value = viewModel.mensaje,
            label = { Text(text = "Mensaje") },
            onValueChange = { viewModel.actualizarMensaje(it) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

