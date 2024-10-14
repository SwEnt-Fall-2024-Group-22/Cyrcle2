package com.github.se.cyrcle.model.user

import com.github.se.cyrcle.model.parking.Location
import com.github.se.cyrcle.model.parking.Parking
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UserRepositoryFirestore(private val db: FirebaseFirestore) : UserRepository {
    override fun init(onSuccess: () -> Unit) {
        Firebase.auth.addAuthStateListener {
            if (it.currentUser != null) {
                onSuccess()
            }
        }
    }

    override fun getUserById(
        userId: String,
        onSuccess: (User) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        db.collection("users")
            .document(userId)
            .get()
            .addOnSuccessListener { document ->
                val user = document.data?.let { deserializeUser(it) }
                if (user != null) {
                    onSuccess(user)
                } else {
                    onFailure(Exception("User not found"))
                }
            }
            .addOnFailureListener { onFailure(it) }
    }



    override fun getAllUsers(onSuccess: (List<User>) -> Unit, onFailure: (Exception) -> Unit) {
        TODO("Not yet implemented and not sure I should anyways")
    }

    override fun addUser(user: User, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        db.collection("users")
            .document(user.userId)
            .set(serializeUser(user))
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure(it) }
    }

    override fun updateUser(user: User, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        db.collection("users")
            .document(user.userId)
            .set(serializeUser(user))
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure(it) }
    }

    override fun deleteUserById(
        userId: String,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        TODO("Not yet implemented")
    }

   override fun login(
        email: String,
        password: String,
        onSuccess: (User) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        TODO("Not yet implemented and not sure I should anyways")
    }

    override fun logout(userId: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        TODO("Not yet implemented and not sure I should anyways")
    }

    override fun updateUserLocation(
        userId: String,
        location: Location,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun getUserLocationHistory(
        userId: String,
        onSuccess: (List<Location>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun addPreferredLocation(
        userId: String,
        location: Location,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun removePreferredLocation(
        userId: String,
        location: Location,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun getPreferredLocations(
        userId: String,
        onSuccess: (List<Location>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun getUserContributedSpots(
        userId: String,
        onSuccess: (List<Parking>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        TODO("Not yet implemented and not sure how to anyways")
    }



    private fun serializeUser(user: User): Map<String, Any> {
        val gson = Gson()
        val type = object : TypeToken<Map<String, Any>>() {}.type
        return gson.fromJson(gson.toJson(user), type)
    }

    private fun deserializeUser(data: Map<String, Any>): User {
        val gson = Gson()
        return gson.fromJson(gson.toJson(data), User::class.java)
    }
}