package com.hellguy39.minor_thing.presentation.screen.edit_study_day

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hellguy39.minor_thing.ui.values.Spaces

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSubjectDialog(
    isVisible: Boolean,
    onDone: (subjectName: String) -> Unit,
    onDismiss: () -> Unit
) {
    if (!isVisible) return

    var subjectName by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss
    ) {
        Surface(
            shape = MaterialTheme.shapes.large,
            color = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)
        ) {
            Column(
                modifier = Modifier.padding(Spaces.large),
                verticalArrangement = Arrangement.spacedBy(Spaces.medium),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "New subject")
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = subjectName,
                    onValueChange = { text -> subjectName = text },
                    label = { Text(text = "Subject name") }
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(Spaces.small)
                ) {
                    Spacer(Modifier.weight(1f))
                    TextButton(onClick = { onDismiss() }) {
                        Text(text = "Cancel")
                    }
                    Button(onClick = { onDone(subjectName) }) {
                        Text(text = "Done")
                    }
                }
            }
        }
    }
}
