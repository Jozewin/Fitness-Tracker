package com.joze.fitnesstracking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.joze.fitnesstracking.presentation.onBoarding.OnBoardingScreenViewModel
import com.joze.fitnesstracking.presentation.onBoarding.onBoardingScreen
import com.joze.fitnesstracking.ui.theme.FitnessTrackingTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitnessTrackingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel : OnBoardingScreenViewModel by viewModels()

//                    val viewModel : OnBoardingScreenViewModel by viewModels()
                    onBoardingScreen(onEvents = viewModel::onEvents)
                }
            }
        }
    }
}
