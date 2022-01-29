package br.com.fiap.wallet.model.dto.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fiap.wallet.model.dto.LoginDTO
import br.com.fiap.wallet.model.dto.SignUpDTO
import br.com.fiap.wallet.models.SignUp
import br.com.fiap.wallet.repository.LoginRepository
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel constructor(private val repository: LoginRepository) : ViewModel() {

    val liveDataSignUp = MutableLiveData<String>()
    val errorMessage = MutableLiveData<String>()

    fun login(login: LoginDTO){
        val request = repository.login(login)
        request.enqueue(object : Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                liveDataSignUp.postValue(response.headers().get("Authorization"))
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                errorMessage.postValue(t.message)
            }

        })
    }
}