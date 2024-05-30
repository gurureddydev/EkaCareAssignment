package com.guru.ekacareassignment.ui.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.guru.ekacareassignment.data.UserDatabase
import com.guru.ekacareassignment.data.repository.UserRepository
import com.guru.ekacareassignment.ui.theme.screens.UserFormUi
import com.guru.ekacareassignment.ui.theme.screens.UserListScreen
import com.guru.ekacareassignment.ui.theme.viewmodel.UserViewModel
import com.guru.ekacareassignment.ui.theme.viewmodel.UserViewModelFactory

class MainActivity : ComponentActivity() {
    private val userDatabase by lazy { UserDatabase.getDatabase(this) }
    private val userRepository by lazy { UserRepository(userDatabase.userDao()) }
    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory(userRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            EkaCareAssignmentTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "list_screen") {
                    composable("form_screen") { UserFormUi(navController, userViewModel) }
                    composable("list_screen") { UserListScreen(navController, userViewModel) }
                }
            }
        }
    }
}

