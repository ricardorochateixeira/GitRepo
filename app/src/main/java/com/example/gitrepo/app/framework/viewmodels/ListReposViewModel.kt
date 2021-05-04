package com.example.gitrepo.app.framework.viewmodels

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.material.Snackbar
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.gitrepo.app.framework.network.GitRepoApiModel
import com.example.gitrepo.domain.usecases.GetReposUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

const val PAGE_SIZE = 50
const val TAG = "reposdebug_viewmodel"

@HiltViewModel
class ListReposViewModel
@Inject constructor(
    @ApplicationContext val application: Context,
    val getReposUseCase: GetReposUseCase
) : ViewModel() {

    var page = mutableStateOf(1)
    var repoListScrollPosition = 0
    var listRepos = listOf<GitRepoApiModel>()
    private val compositeDisposable = CompositeDisposable()


    @SuppressLint("CheckResult")
    fun getRepos(query: String) {
        Log.d(TAG, query)
        compositeDisposable.add(
            getReposUseCase(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response -> appendRepos(response.items!!) }, { t -> onFailure(t) })
        )
    }

    private fun onFailure(t: Throwable) {
        Toast.makeText(application, t.message.toString(), Toast.LENGTH_SHORT).show()
    }

    fun clearList(){
        this.listRepos = emptyList()
    }

    private fun appendRepos(repos: List<GitRepoApiModel>) {
        this.listRepos = repos
        Log.d(TAG, listRepos.toString())
    }

    private fun incrementPage() {
        page.value = page.value + 1
    }

    fun onChangeRepoScrollPosition(position: Int) {
        repoListScrollPosition = position
    }

    fun nextPage(query: String) {
        if ((repoListScrollPosition + 1) >= (page.value * PAGE_SIZE)) {
            incrementPage()
        }

        if (page.value > 1) {
            compositeDisposable.add(
                getReposUseCase(query)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({ response -> appendRepos(response.items!!) }, { t -> onFailure(t) })
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
