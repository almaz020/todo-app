package com.example.todoapp.feature_tasks.presentation.create

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.todoapp.feature_tasks.presentation.list.utils.formatOnlyTime
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

        // 🔙 Назад
        Text(
            text = "← Назад",
            modifier = Modifier
                .clickable { onBack() }
                .padding(bottom = 8.dp)
        )

        Text(
            text = "Создать задачу",
            style = MaterialTheme.typography.headlineSmall
        )

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

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Время",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = formatDate(viewModel.dateStart),
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .clickable {
                    pickDate(context, viewModel.dateStart) { newDate ->
                        // сохраняем время, меняем только день
                        val startCal = Calendar.getInstance().apply {
                            timeInMillis = viewModel.dateStart
                        }
                        val newCal = Calendar.getInstance().apply {
                            timeInMillis = newDate
                            set(Calendar.HOUR_OF_DAY, startCal.get(Calendar.HOUR_OF_DAY))
                            set(Calendar.MINUTE, startCal.get(Calendar.MINUTE))
                        }

                        val diff = viewModel.dateEnd - viewModel.dateStart

                        viewModel.onDateStartChange(newCal.timeInMillis)
                        viewModel.onDateEndChange(newCal.timeInMillis + diff)
                    }
                }
                .background(Color.LightGray)
                .padding(12.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = formatOnlyTime(viewModel.dateStart),
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .clickable {
                        pickTime(context, viewModel.dateStart) {
                            viewModel.onDateStartChange(it)
                        }
                    }
                    .background(Color.LightGray)
                    .padding(12.dp)
            )

            Text(" - ", modifier = Modifier.padding(horizontal = 8.dp))

            Text(
                text = formatOnlyTime(viewModel.dateEnd),
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .clickable {
                        pickTime(context, viewModel.dateEnd) {
                            viewModel.onDateEndChange(it)
                        }
                    }
                    .background(Color.LightGray)
                    .padding(12.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

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
            newCal.timeInMillis = initial

            newCal.set(Calendar.YEAR, year)
            newCal.set(Calendar.MONTH, month)
            newCal.set(Calendar.DAY_OF_MONTH, day)
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