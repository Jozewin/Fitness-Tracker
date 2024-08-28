package com.joze.fitnesstracking.presentation.home_screen

import android.annotation.SuppressLint
import android.graphics.Outline
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreeen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val openDialog = remember {
            mutableStateOf(false)
        }

        var dateResult by remember { mutableStateOf(DateFormat.getDateInstance().format(Calendar.getInstance().time)) }
        Text(text = "Hi ig")

        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                openDialog.value = true
            }) {
            Text(text = dateResult)
        }
        if (openDialog.value) {
            val datePicker = rememberDatePickerState()
            val confirmEnabled = derivedStateOf {
                datePicker.selectedDateMillis != null
            }

            DatePickerDialog(
                onDismissRequest = {
                    openDialog.value = false
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            openDialog.value = false
                            var date = "No Selection"
                            if(datePicker.selectedDateMillis != null){
                                date = convertMillisToDate(datePicker.selectedDateMillis!!)
                            }
                            dateResult = date
                        },
                        enabled = confirmEnabled.value
                    ) {
                        Text(text = "Ok")
                    }
                }) {
                DatePicker(state = datePicker)

            }
        }

    }

}

fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {


    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),

                title = {
                    Text(text = "Fitness Tracker")
                }
            )
        }
    ) {innerPadding->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val openDialog = remember {
                mutableStateOf(false)
            }

            var dateResult by remember { mutableStateOf(DateFormat.getDateInstance().format(Calendar.getInstance().time)) }
            Text(text = "Hi ig")

            OutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    openDialog.value = true
                }) {
                Text(text = dateResult)
            }
            if (openDialog.value) {
                val datePicker = rememberDatePickerState()
                val confirmEnabled = derivedStateOf {
                    datePicker.selectedDateMillis != null
                }

                DatePickerDialog(
                    onDismissRequest = {
                        openDialog.value = false
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                openDialog.value = false
                                var date = "No Selection"
                                if(datePicker.selectedDateMillis != null){
                                    date = convertMillisToDate(datePicker.selectedDateMillis!!)
                                }
                                dateResult = date
                            },
                            enabled = confirmEnabled.value
                        ) {
                            Text(text = "Ok")
                        }
                    }) {
                    DatePicker(state = datePicker)

                }
            }

        }

    }
}
