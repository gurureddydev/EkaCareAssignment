package com.guru.ekacareassignment.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.guru.ekacareassignment.data.User
import com.guru.ekacareassignment.ui.theme.viewmodel.UserViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import com.guru.ekacareassignment.R

@Composable
fun UserListScreen(navController: NavController, userViewModel: UserViewModel) {
    val users by userViewModel.allUsers.observeAsState(emptyList())
    userViewModel.fetchAllUser()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(users) { user ->
            UserCard(user = user, onDeleteClick = { userViewModel.delete(it) })
            Spacer(modifier = Modifier.height(8.dp))
        }
    }

    if (users.isEmpty()) {
        Text(
            text = stringResource(id = R.string.no_users_available),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(16.dp)
        )
    }

    AddUserFab(navController)
}

@Composable
fun AddUserFab(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        ExtendedFloatingActionButton(
            onClick = { navController.navigate("form_screen") },
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add User"
            )
            Text(text = "Add User")
        }
    }
}


@Composable
fun UserCard(user: User, onDeleteClick: (User) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Name: ${user.name}",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Age: ${user.age}")
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "DOB: ${user.dob}")
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Address: ${user.address}")
            }
            IconButton(onClick = { onDeleteClick(user) }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete User",
                    tint = Color.White
                )
            }
        }
    }
}
