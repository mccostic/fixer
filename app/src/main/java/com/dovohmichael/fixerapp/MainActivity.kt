package com.dovohmichael.fixerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dovohmichael.fixerapp.ui.theme.FixerAppTheme
import com.dovohmichael.fixerapp.presentation_common.navigation.NavRoutes
import com.dovohmichael.fixerapp.presentation_converter.*
import com.dovohmichael.fixerapp.presentation_history.HistoryScreen

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FixerAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    App(navController = navController)
                }
            }
        }
    }
}


@Composable
fun App(navController: NavHostController) {
    NavHost(navController, startDestination = NavRoutes.Home.route) {
        composable(route = NavRoutes.Home.route) {
            ConverterScreen(hiltViewModel(), modifier = Modifier,navController)
        }
        composable(
            route = NavRoutes.History.route,
            arguments = NavRoutes.History.arguments
        ) {
            HistoryScreen(hiltViewModel(),modifier = Modifier,navController,NavRoutes.History.fromEntry(it))
           /* PostScreen(
                hiltViewModel(),
                NavRoutes.Post.fromEntry(it)
            )*/
        }
    }
}
