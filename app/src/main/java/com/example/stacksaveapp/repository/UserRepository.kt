package com.example.stacksaveapp.repository

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class UserRepository {
    private val db = Firebase.firestore

    suspend fun createInitialUserProfile(userId: String, email: String, categories: List<String>): Result<Unit> {
        return try {
            val userMap = hashMapOf(
                "email" to email,
                "categories" to categories
            )
            db.collection("users").document(userId).set(userMap).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
