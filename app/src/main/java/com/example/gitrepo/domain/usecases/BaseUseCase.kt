package com.example.gitrepo.domain.usecases

interface BaseUseCase<in Parameter, out Result> {
    operator fun invoke(params: Parameter): Result
}