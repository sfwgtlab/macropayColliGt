package com.eduardocolli.Macropay.Activities


import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import com.eduardocolli.Macropay.R
import com.eduardocolli.Macropay.ViewModels.loginViewModel
import com.eduardocolli.Macropay.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var loginVM : loginViewModel
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_MacropayColliGt)
        Thread.sleep(2000)
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this);
        window.statusBarColor = resources.getColor(R.color.primary)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loginVM = ViewModelProvider(this).get(loginViewModel::class.java)
        auth = Firebase.auth

        if (validateInfo("user", "islogged")) launchHome()


        binding.btnLogin.setOnClickListener {

            binding.pbLogin.visibility = ProgressBar.VISIBLE
            if(binding.edtPassLogin.text.toString().isEmpty() && binding.edtUserLogin.text.toString().isEmpty()){
                showAlert("Los campos son obligatorios")
                binding.pbLogin.visibility = ProgressBar.INVISIBLE
            }else{

                loginVM.login(binding.edtUserLogin.text.toString().trim(), binding.edtPassLogin.text.toString().trim() )


                loginVM.logueo.observe(this, Observer { response ->

                    if(response != null){
                        saveData("true", "user", "islogged")
                        launchHome()
                    }else {
                       showAlert("Credenciales incorrectas")
                    }
                    loginVM.onCleared().apply {  }

                })

            }
        }

    }

    fun launchHome(){
        binding.pbLogin.visibility = ProgressBar.INVISIBLE
        val intent = Intent(this, Home::class.java)
        startActivity(intent)
        finish()
    }

    fun showAlert(mensaje: String){
        binding.pbLogin.visibility = ProgressBar.INVISIBLE
        binding.edtUserLogin.setText("")
        binding.edtPassLogin.setText("")
        val builder = AlertDialog.Builder(this)
        builder.setMessage(mensaje)
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }


    fun validateInfo(nameSp: String, spContain: String): Boolean{
        val sp = getSharedPreferences( nameSp, MODE_PRIVATE);
        var content: Boolean = false;
        if(sp.contains(spContain)){
            content = true;
        }
        return content;
    }

    fun saveData(data: Any, nameSP: String, nameEditor: String ){
        val sp = getSharedPreferences(nameSP, MODE_PRIVATE);
        val editor: SharedPreferences.Editor =  sp.edit();

        editor.putString(nameEditor, data.toString());
        editor.apply();
    }
}