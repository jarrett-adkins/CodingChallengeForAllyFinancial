package com.example.codingchallenge.model

class VoteTotal(
    val AK: State = State(),
    val AL: State = State(),
    val AR: State = State()
) {
    fun getStates(): List<State> {
        return listOf(AK, AL, AR)
    }
}