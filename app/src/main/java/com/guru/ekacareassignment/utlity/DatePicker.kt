package com.guru.ekacareassignment.utlity

import android.app.DatePickerDialog
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import java.util.*

@Composable
fun DatePicker(onDateSelected: (String) -> Unit) {
    val context = LocalContext.current

    Button(onClick = {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(
            context,
            { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                onDateSelected(selectedDate)
                Toast.makeText(context, "Selected Date: $selectedDate", Toast.LENGTH_SHORT).show()
            },
            year,
            month,
            day
        ).show()
    }) {
        Text(text = "Pick a Date")
    }
}
