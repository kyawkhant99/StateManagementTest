package com.kkh.statemanagementtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kkh.statemanagementtest.ui.theme.StateManagementTestTheme
import com.kkh.statemanagementtest.ui.entrance.EntranceScreen
import com.kkh.statemanagementtest.ui.onboarding.OnBoardingScreen
import com.kkh.statemanagementtest.ui.onboarding.OnBoardingViewModel
import com.kkh.statemanagementtest.ui.theme.PRIMARY_BG

class MainActivity : ComponentActivity() {

    private val viewModel:OnBoardingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        WindowCompat.setDecorFitsSystemWindows(window,false)
        setContent {
            StateManagementTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = PRIMARY_BG
                ) {
                    val controller = rememberNavController()
                    NavHost(
                        navController = controller,
                        startDestination = Screen.ENTRANCE.route
                    ) {
                        composable(Screen.ENTRANCE.route) {
                            EntranceScreen(
                                onNavigateToOnBoarding = {
                                    controller.navigate(Screen.ONBOARDING.route)
                                }
                            )
                        }
                        composable(Screen.ONBOARDING.route) {
                            OnBoardingScreen(viewModel, onNavigateBack = {
                                controller.navigateUp()
                            })
                        }
                    }
                }
            }
        }
    }
}
