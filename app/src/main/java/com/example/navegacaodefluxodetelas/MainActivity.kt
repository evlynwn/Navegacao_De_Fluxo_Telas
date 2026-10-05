package com.example.navegacaodefluxodetelas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navegacaodefluxodetelas.screens.LoginScreen
import com.example.navegacaodefluxodetelas.screens.MenuScreen
import com.example.navegacaodefluxodetelas.screens.PedidosScreen
import com.example.navegacaodefluxodetelas.screens.PerfilScreen
import com.example.navegacaodefluxodetelas.ui.theme.NavegacaoDeFluxoDeTelasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavegacaoDeFluxoDeTelasTheme {
               Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   val navController = rememberNavController()

                   NavHost(
                       navController = navController,
                       startDestination = ""
                   ){
                       composable(route = "LOGIN"){ LoginScreen() }

                       composable(route = "MENU"){ MenuScreen() }

                       composable(route = "PERFIL"){ PerfilScreen()  }

                       composable(route = "PEDIDOS"){ PedidosScreen() }
                   }
                   LoginScreen()

                }
            }
        }
    }
}


