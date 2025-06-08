package com.example.sr_blooms.model

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import android.util.Log

class UserProfileViewModel : ViewModel() {

    // Profile image URI state
    val profileImageUri = mutableStateOf<Uri?>(null)

    // User profile data
    val userProfile = mutableStateOf(
        UserProfile(
            username = "Flower Lover",
            email = "user@srbloomsapp.com",
            contact = "+94 77 123 4567",
            address = "Kandy, Sri Lanka"
        )
    )

    // Loading state
    val isLoading = mutableStateOf(false)

    // Update profile image
    fun updateProfileImage(uri: Uri) {
        viewModelScope.launch {
            try {
                isLoading.value = true
                profileImageUri.value = uri
                Log.d("UserProfileViewModel", "Profile image updated: $uri")
            } catch (e: Exception) {
                Log.e("UserProfileViewModel", "Error updating profile image", e)
            } finally {
                isLoading.value = false
            }
        }
    }

    // Update entire user profile
    fun updateProfile(updatedProfile: UserProfile) {
        viewModelScope.launch {
            try {
                isLoading.value = true
                userProfile.value = updatedProfile
                Log.d("UserProfileViewModel", "Profile updated: $updatedProfile")
            } catch (e: Exception) {
                Log.e("UserProfileViewModel", "Error updating profile", e)
            } finally {
                isLoading.value = false
            }
        }
    }

    fun updateUserData(
        username: String,
        email: String,
        contact: String,
        address: String
    ) {
        userProfile.value = userProfile.value.copy(
            username = username,
            email = email,
            contact = contact,
            address = address
        )
    }

}


// User Profile data class
data class UserProfile(
    val username: String = "",
    val email: String = "",
    val contact: String = "",
    val address: String = "",
    val profileImageUrl: String = ""
)
