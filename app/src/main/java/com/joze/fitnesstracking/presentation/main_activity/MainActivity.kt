package com.joze.fitnesstracking.presentation.main_activity

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.joze.fitnesstracking.presentation.home_screen.HomeScreen
import com.joze.fitnesstracking.presentation.nav_graph.NavGraph
import com.joze.fitnesstracking.presentation.onBoarding.OnBoardingScreenViewModel
import com.joze.fitnesstracking.presentation.onBoarding.onBoardingScreen
import com.joze.fitnesstracking.ui.theme.FitnessTrackingTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainActivityViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition(condition = { viewModel.splashCondition.value })
        }
        setContent {
            FitnessTrackingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.background)
                            .fillMaxSize()
                    )
//                    NavGraph(startDestination = viewModel.startDestination.value)
//                    HomeScreen()
                    MultiplePhotoPickerScreen()
                }
            }
        }
    }
}

@Composable
fun MultiplePhotoPickerScreen() {

    var selectedImageUris by remember {
        mutableStateOf<List<Uri?>>(emptyList())
    }

    val multiplePhotosPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(
            maxItems = 2
        ),
        onResult = {
            selectedImageUris = it
        }
    )


    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Text(
                text = "Choose some Beautiful Photos",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )
            )

            Spacer(modifier = Modifier.size(20.dp))

            Button(modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0073E6),
                    contentColor = Color.White
                ),
                onClick = {
                    multiplePhotosPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }) {

                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_input_add),
                        contentDescription = "Add Image"
                    )
                    Text(
                        text = "Pick Photos",
                        style = TextStyle(
                            fontSize = 18.sp
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.size(20.dp))

            LazyColumn {
                items(selectedImageUris){ selectedImageUri->
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(240.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .padding(vertical = 4.dp),
                        model = selectedImageUri,
                        contentDescription = null,
                        contentScale = ContentScale.Fit

//                                In the code, we have something called AsyncImage, inside that, we have contentScale, you can change the values there with
//
//                                contentScale = ContentScale.Fit
//                                contentScale = ContentScale.Crop
//                                contentScale = ContentScale.Inside
//
//                                and use the one which suits
                    )
                }
            }

        }

    }
}
