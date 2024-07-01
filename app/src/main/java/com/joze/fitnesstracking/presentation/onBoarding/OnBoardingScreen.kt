package com.joze.fitnesstracking.presentation.onBoarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun onBoardingScreen(
    //onEvents: (OnBoardingScreenEvents) -> Unit,

    
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(120.dp))
        onBoardingScreenTitle(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(90.dp))

        onBoardingScreenData()
        
    }

}
@Composable
fun onBoardingScreenTitle(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center

    ) {
        Column {
            Text(
                text = "Welcome To",
                fontSize = 32.sp,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,

                )
            Text(
                text = "       Gym Tracker",
                fontSize = 32.sp,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic
            )

        }

    }
}

@Composable
fun onBoardingScreenData(
    userDetailsViewModel: OnBoardingScreenViewModel = hiltViewModel()

) {
    var name by remember { mutableStateOf(TextFieldValue(userDetailsViewModel.userDetails?.name ?: "")) }
    var age by remember { mutableStateOf(TextFieldValue(userDetailsViewModel.userDetails?.age?.toString() ?: "")) }
    var weight by remember { mutableStateOf(TextFieldValue(userDetailsViewModel.userDetails?.weight?.toString() ?: "")) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            placeholder = { Text("Enter your name") },
            modifier = Modifier.padding(bottom = 16.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            maxLines = 1
        )
        OutlinedTextField(
            value = age,
            onValueChange = { age = it },
            label = { Text("Age") },
            placeholder = { Text("Enter your age") },
            modifier = Modifier.padding(bottom = 16.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            )
        )
        OutlinedTextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text("Weight") },
            placeholder = { Text("Enter your weight") },
            modifier = Modifier.padding(bottom = 16.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            )
        )
        FloatingActionButton(
            onClick = {
                userDetailsViewModel.saveUserProfile(name.text, age.text.toInt(), weight.text.toFloat())
            },
            containerColor = Color.Black,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clip(RoundedCornerShape(15.dp))
        ) {
            Icon(
                Icons.Outlined.KeyboardArrowRight,
                tint = Color.White,
                contentDescription = "Localized description"
            )
        }
    }
}

@Preview
@Composable
fun onBoardingScreenTitlePrev() {


}