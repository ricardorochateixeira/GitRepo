package com.example.gitrepo.app.presentation

import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.gitrepo.R
import com.example.gitrepo.app.theme.GitRepoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GitRepoTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "list_repos_screen"
                ) {
                    composable("list_repos_screen") {
                        RepoListScreen(navController = navController)
                    }
                    composable("repo_details_screen/{repoName}",
                        arguments = listOf(
                            navArgument("repoName") {
                                type = NavType.StringType
                            }
                        )
                    ) {
                        val repoName = remember {
                            it.arguments?.getString("repoName")
                        }
                        RepoDetailScreen()
                    }
                }
            }
        }
    }
}

