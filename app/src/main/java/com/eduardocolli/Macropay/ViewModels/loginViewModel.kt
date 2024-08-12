package com.eduardocolli.Macropay.ViewModels


import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eduardocolli.Macropay.Models.loginResponse
import com.eduardocolli.Macropay.Models.user
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.log

class loginViewModel: ViewModel() {

    private val _dataLiveData = MutableLiveData<loginResponse?>()
    val logueo: LiveData<loginResponse?> get() = _dataLiveData
    private lateinit var auth: FirebaseAuth

    fun login(user: String, pass: String){
        auth = Firebase.auth
        //eduardo@gmail.com admin10
            auth.signInWithEmailAndPassword(user, pass)
                .addOnCompleteListener{
                    if(it.isSuccessful){
                        _dataLiveData.value =  loginResponse(email = user, name = "generico")
                    }else{
                        _dataLiveData.value = null
                    }
                }





    }

    public override fun onCleared() {
        super.onCleared()

    }


}