package com.dicoding.merchku.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.merchku.di.Injection
import com.dicoding.merchku.ui.ViewModelFactory

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
) {
    val name by viewModel.name
    val bio by viewModel.bio
    val email by viewModel.email
    val location by viewModel.location
    val image by viewModel.image

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(120.dp))
        CircularImage(imageResId = image)
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = name,
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = bio,
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))
        Divider()
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = email,
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = location,
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun CircularImage(imageResId: Int, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = imageResId),
        contentDescription = "profile image",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(180.dp)
            .clip(CircleShape)
            .border(width = 2.dp, color = Color.Gray, CircleShape)
    )
}