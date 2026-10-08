package com.example.navegacaodefluxodetelas

import android.R.attr.name
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
                       startDestination = "LOGIN",
                       exitTransition = {
                           slideOutOfContainer(
                               towards = AnimatedContentTransitionScope.SlideDirection.Up,
                               animationSpec = tween (1000)
                           ) //+ fadeOut(animationSpec = tween (1000))
                       }
                   ){
                       composable(route = "LOGIN"){ LoginScreen(modifier = Modifier.padding(innerPadding), navController) }

                       composable(route = "MENU"){ MenuScreen(navController = navController,  )}

                       composable(route = "PERFIL/{nome}/{idade}",
                           arguments = listOf(
                               navArgument(name = "nome"){
                                   type = NavType.StringType
                               },
                               navArgument(name = "idade") {
                                   type = NavType.IntType
                               })
                           ) {

                           val nome = it.arguments?.getString("nome")
                           val idade = it.arguments?.getInt("idade")

                           PerfilScreen(
                               navController = navController,
                               nome = nome!!,
                               idade = idade!!
                           )
                       }

                       composable(
                           route = "PEDIDOS?numeroPedido={numeroPedido}",
                           arguments = listOf(navArgument(name = "numeroPedido"){
                               defaultValue = "Sem pedidos"
                           })
                       ) {
                           val numeroPedido = it.arguments?.getString("numeroPedido")

                           PedidosScreen(
                               navController = navController,
                               numeroPedido = numeroPedido!!
                               ) }
                   }

                }
            }
        }
    }
}


