package com.example.todo.RoomDatabaseUse

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jcmodule.AnimatedButton.RotateButton
import com.example.jcmodule.TextFeilds.BubbleTextField
import com.example.jcmodule.TextFeilds.NeonTextField

@Composable
fun AppUi() {

    val vModel: VMClass = viewModel()
    val notes by vModel.items.collectAsState(initial = emptyList())

    val title by vModel.title.collectAsState()
    val description by vModel.description.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
// Input fields
        Text(text = "ADD NOTES",
            modifier=Modifier.padding(4.dp),
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            color = Color.Black
        )
        Spacer(modifier=Modifier.size(20.dp))

        BubbleTextField(
            value = title,
            onValueChange = {vModel.UpdateTitle(it)},
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            label = "Title"
        )

        Spacer(modifier=Modifier.size(10.dp))

        NeonTextField(
            value = description,
            onValueChange = {vModel.UpdateDesc(it)},
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            placeholder = "Description",
            textColor = Color.Black,
            neonColor = Color.Blue,
            textStyle = MaterialTheme.typography.bodyMedium,
            width = 300.dp,
            animationDuration = 800,
            maxLine = 2
        )

        RotateButton(
            onClick = {
                if (title.isNotBlank() || description.isNotBlank()) {
                    vModel.insert(ItemEntity(title = title, description = description))

                    // for reseting the title and description
                    vModel.UpdateTitle(" ")
                    vModel.UpdateDesc( "")
                }
            },
            text = "Add Note",
            textColor = Color.White,
            containerColor = Color.Red,
            disabledContainerColor = Color.Gray.copy(alpha = 0.5f),
            elevation = ButtonDefaults.buttonElevation(),
            shape = MaterialTheme.shapes.medium,
            border = BorderStroke(2.dp, Color.Green),
            icon = { Icon(imageVector = Icons.Default.Add, contentDescription = "Favorite") },
            // icon = null               if you not wanna use icon
            animationDuration = 500,
            rotationDegrees = 360f,
            enabled = true,
            interactionSource = remember { MutableInteractionSource() }
        )


        Spacer(modifier = Modifier.height(16.dp))

  // List of notes
        LazyColumn {
            items(notes) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp) // Correct for Material3
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(item.title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                            Text(item.description, style = MaterialTheme.typography.titleMedium)
                        }
                        IconButton(onClick = { vModel.delete(item) }) {
                            Icon(Icons.Default.Delete, contentDescription = "Delete")
                        }
                    }
                }
            }
        }
    }
}


