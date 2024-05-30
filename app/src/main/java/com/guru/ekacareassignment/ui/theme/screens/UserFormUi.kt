package com.guru.ekacareassignment.ui.theme.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.guru.ekacareassignment.data.User
import com.guru.ekacareassignment.utlity.DatePicker
import com.guru.ekacareassignment.ui.theme.viewmodel.UserViewModel

@Composable
fun UserFormUi(navController: NavController, viewModel: UserViewModel) {
    var name by remember { mutableStateOf(TextFieldValue()) }
    var age by remember { mutableStateOf(TextFieldValue()) }
    var dob by remember { mutableStateOf("") }
    var address by remember { mutableStateOf(TextFieldValue()) }
    var errorText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = age,
            onValueChange = { age = it },
            label = { Text("Age") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(8.dp))

        DatePicker(onDateSelected = { selectedDate ->
            dob = selectedDate
        })
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = address,
            onValueChange = { address = it },
            label = { Text("Address") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (errorText.isNotEmpty()) {
            Text(
                text = errorText,
                color = Color.Red,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        Button(onClick = {
            if (name.text.isNotEmpty() && age.text.isNotEmpty() && dob.isNotEmpty() && address.text.isNotEmpty()) {
                try {
                    val user = User(
                        name = name.text,
                        age = age.text.toInt(),
                        dob = dob,
                        address = address.text
                    )
                    viewModel.insert(user)
                    navController.popBackStack()
                } catch (e: NumberFormatException) {
                    errorText = "Please enter a valid age"
                }
            } else {
                errorText = "Please fill in all the fields"
            }
        }) {
            Text("Save")
        }
    }
}
