package com.example.sr_blooms.AuthViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel : ViewModel() {
    // Firebase authentication instance
    private val auth : FirebaseAuth = FirebaseAuth.getInstance()
    // LiveData to observe authentication state changes
    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState
    // Check authentication status when ViewModel is initialized
    init {
        checkAuthStatus()
    }

//     Updates authState accordingly.
    fun checkAuthStatus(){
        if(auth.currentUser==null){
            _authState.value = AuthState.unauthenticated
        }else
            _authState.value = AuthState.Authenticated

    }
    fun login(email: String, password: String) {
        // Validate input fields
        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error("Email or password cannot be empty")
            return // Ensure it stops execution
        }

        // Set loading state
        _authState.value = AuthState.Loading
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _authState.value = AuthState.Authenticated
                } else {
                    _authState.value = AuthState.Error(task.exception?.message ?: "Something went wrong")
                }
            }
    }


    fun signup(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error("Email or password cannot be empty")
            return  // Stop execution here
        }

        _authState.value = AuthState.Loading
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _authState.value = AuthState.Authenticated
                } else {
                    _authState.value = AuthState.Error(task.exception?.message ?: "Something went wrong")
                }
            }
    }


    fun signout(navController: NavController){
        auth.signOut()
        _authState.value = AuthState.unauthenticated
        navController.navigate("LoginScreen")

    }
}
/**
 * Sealed class representing different authentication states.
 */
sealed class  AuthState{
    object Authenticated : AuthState()
    object unauthenticated : AuthState()
    object Loading : AuthState()
    data class Error(val message : String) : AuthState()
}