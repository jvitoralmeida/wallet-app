package br.com.fiap.wallet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.fiap.wallet.databinding.ActivityMainBinding
import br.com.fiap.wallet.model.dto.LoginDTO
import br.com.fiap.wallet.model.dto.viewmodel.LoginViewModel
import br.com.fiap.wallet.model.dto.viewmodel.LoginViewModelFactory
import br.com.fiap.wallet.repository.LoginRepository
import br.com.fiap.wallet.rest.RetrofitService

class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding
    private lateinit var viewModel: LoginViewModel
    private val retrofitService = RetrofitService.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        viewModel =
            ViewModelProvider(this, LoginViewModelFactory(LoginRepository(retrofitService))).get(
                LoginViewModel::class.java
            )

        observeLoginViewModel()

        Log.d("joao", "to no first aqui")
    }

    private fun observeLoginViewModel() {
        viewModel.liveDataSignUp.observe(this, Observer { myText ->
            if (myText.isEmpty()) {
                Toast.makeText(this, "Token is empty", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, myText, Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun login(view: View) {
        val texto1 = findViewById<EditText>(R.id.edt_email).text.toString();
        val texto2 = findViewById<EditText>(R.id.edt_password).text.toString();

        viewModel.login(LoginDTO(texto1, texto2))
    }
}