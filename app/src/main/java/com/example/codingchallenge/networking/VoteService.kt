package com.example.codingchallenge.networking

import com.example.codingchallenge.model.Payload
import retrofit2.http.GET

interface VoteService {
    @GET("2012.json")
    suspend fun getVotes(): Payload
}