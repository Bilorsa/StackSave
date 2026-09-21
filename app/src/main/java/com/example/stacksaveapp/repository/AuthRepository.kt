package com.example.stacksaveapp.repository

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await

class AuthRepository {
    private val auth: FirebaseAuth = Firebase.auth

    // Returns the current Firebase User, or null if logged out
    val currentUser get() = auth.currentUser

    suspend fun loginWithEmail(email: String, pass: String): Result<String> {
        return try {
            val result = auth.signInWithEmailAndPassword(email, pass).await()
            val userId = result.user?.uid ?: throw Exception("User ID is null")
            Result.success(userId)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun registerWithEmail(email: String, pass: String): Result<String> {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, pass).await()
            val userId = result.user?.uid ?: throw Exception("User ID is null")
            Result.success(userId)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}