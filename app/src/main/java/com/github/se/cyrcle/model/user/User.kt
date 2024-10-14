package com.github.se.cyrcle.model.user

import com.github.se.cyrcle.model.parking.Location
import com.github.se.cyrcle.model.parking.Parking
import com.google.firebase.Timestamp

data class User(
    val userId: String,
    val username: String,
    val firstName: String?,
    val lastName: String?,
    val email: String,
    val profilePictureUrl: String?,
    val homeAddress: String?,
    val favoriteParkingSpots: List<Parking> = emptyList(),
    val lastKnownLocation: Location,
    val locationHistory: List<Location> = emptyList(),
    val lastLoginTime: String?,
    val accountCreationDate: Timestamp,

    //TODO: Add formula for reputation points based on participation in the app, which are spendable in exchange for app cosmetics.
    val numberOfContributedSpots: Int = 0,
    val userReputationScore: Int = 0,


    // POTENTIAL ADDITIONS IN THE FUTURE ?
    //val friendsList: List<User> = emptyList(),
    //val sharedSpotsWithFriends: List<Parking> = emptyList(),


    // NOT SURE IF NEEDED
    // val commentsOnParkingSpots: List<"Review"> = emptyList(),
    // val recentlyViewedSpots: List<Parking ou Location> = emptyList(),
)