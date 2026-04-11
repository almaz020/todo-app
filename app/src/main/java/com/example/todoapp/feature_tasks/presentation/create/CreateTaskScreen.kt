package com.example.todoapp.feature_tasks.presentation.create

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.todoapp.feature_tasks.presentation.list.utils.formatTime
import org.koin.androidx.compose.koinViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun CreateTaskScreen(
    onBack: () -> Unit,
    viewModel: CreateTaskViewModel = koinViewModel()
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text("Создать задачу", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = viewModel.name,
            onValueChange = viewModel::onNameChange,
            label = { Text("Название") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = viewModel.description,
            onValueChange = viewModel::onDescriptionChange,
            label = { Text("Описание") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Колонка для начала
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Начало: ${formatDate(viewModel.dateStart)}",
                    modifier = Modifier.clickable {
                        pickDate(context, viewModel.dateStart) {
                            viewModel.onDateStartChange(it)
                        }
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Время: ${formatTime(viewModel.dateStart, viewModel.dateStart)}",
                    modifier = Modifier.clickable {
                        pickTime(context, viewModel.dateStart) {
                            viewModel.onDateStartChange(it)
                        }
                    }
                )
            }

            // Колонка для окончания
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Окончание: ${formatDate(viewModel.dateEnd)}",
                    modifier = Modifier.clickable {
                        pickDate(context, viewModel.dateEnd) {
                            viewModel.onDateEndChange(it)
                        }
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Время: ${formatTime(viewModel.dateEnd, viewModel.dateEnd)}",
                    modifier = Modifier.clickable {
                        pickTime(context, viewModel.dateEnd) {
                            viewModel.onDateEndChange(it)
                        }
                    }
                )
            }
        }


        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.createTask {
                    onBack()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Сохранить")
        }
    }
}

fun pickDate(
    context: Context,
    initial: Long,
    onSelected: (Long) -> Unit
) {
    val cal = Calendar.getInstance()
    cal.timeInMillis = initial

    DatePickerDialog(
        context,
        { _, year, month, day ->
            val newCal = Calendar.getInstance()
            newCal.set(year, month, day)
            onSelected(newCal.timeInMillis)
        },
        cal.get(Calendar.YEAR),
        cal.get(Calendar.MONTH),
        cal.get(Calendar.DAY_OF_MONTH)
    ).show()
}

fun pickTime(
    context: Context,
    initial: Long,
    onSelected: (Long) -> Unit
) {
    val cal = Calendar.getInstance()
    cal.timeInMillis = initial

    TimePickerDialog(
        context,
        { _, hour, minute ->
            val newCal = Calendar.getInstance()
            newCal.timeInMillis = initial
            newCal.set(Calendar.HOUR_OF_DAY, hour)
            newCal.set(Calendar.MINUTE, minute)
            onSelected(newCal.timeInMillis)
        },
        cal.get(Calendar.HOUR_OF_DAY),
        cal.get(Calendar.MINUTE),
        true
    ).show()
}
fun formatDate(timestamp: Long): String {
    val formatter = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    return formatter.format(Date(timestamp))
}