package com.example.gitrepo.app.presentation

import android.util.Log
import android.widget.EditText
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.gitrepo.app.framework.network.GitRepoApiModel
import com.example.gitrepo.app.framework.network.GitReposApi
import com.example.gitrepo.app.framework.viewmodels.ListReposViewModel
import kotlinx.coroutines.delay

const val TAG = "reposdebug_screen"

@Composable
fun RepoListScreen(
    navController: NavController,
    viewModel: ListReposViewModel = hiltNavGraphViewModel()
) {

    var reposMutableState by remember {
        mutableStateOf(emptyList<GitRepoApiModel>())
    }

    Column {
        SearchBar(
            hint = "Search repo...",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            if (it.isEmpty()) {
                Log.d(TAG, reposMutableState.toString())
                viewModel.clearList()
            } else {
                viewModel.getRepos(it)
                reposMutableState = viewModel.listRepos
                Log.d(TAG, it)
                Log.d(TAG, reposMutableState.toString())
            }
        }
        RepoList(navController = navController, repos = reposMutableState)
    }
}

@Composable
fun RepoList(
    navController: NavController,
    repos: List<GitRepoApiModel>,
    modifier: Modifier = Modifier
) {
    Column {
        LazyColumn {
            items(items = repos) { repo ->
                RepoCard(
                    entry = repo,
                    onClick = {
                        navController.navigate("repo_details_screen/${repo.name!!}")
                    }
                )
            }
        }
    }
}

@Composable
fun RepoListTest(
    navController: NavController,
    repos: List<GitRepoApiModel>,
    modifier: Modifier = Modifier
) {
    Column {
        LazyColumn {
            items(items = repos) { repo ->
                RepoCard(
                    entry = repo,
                    onClick = {
                        navController.navigate("repo_details_screen/${repo.name!!}")
                    }
                )
            }
        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String,
    onSearch: (String) -> Unit = {}
) {
    var text by remember {
        mutableStateOf("")
    }

    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }

    Box(modifier = modifier) {
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(
                    horizontal = 20.dp,
                    vertical = 20.dp
                )
                .onFocusChanged {
                    isHintDisplayed = it != FocusState.Active
                }
        )
        if (isHintDisplayed) {
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier
                    .padding(
                        horizontal = 20.dp,
                        vertical = 20.dp
                    )
            )
        }
    }
}

@Composable
fun RepoCard(
    entry: GitRepoApiModel,
    onClick: () -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                bottom = 6.dp,
                top = 6.dp,
                start = 6.dp,
                end = 6.dp
            )
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .padding(
                    bottom = 6.dp,
                    top = 6.dp,
                    start = 6.dp,
                    end = 6.dp
                )
                .fillMaxWidth()
        ) {
            Text(text = entry.name!!)
            Text(text = entry.forksCount!!.toString())
        }
    }
}

