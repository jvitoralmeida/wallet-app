package br.com.fiap.wallet.rest

import br.com.fiap.wallet.model.dto.LoginDTO
import br.com.fiap.wallet.model.dto.SignUpDTO
import br.com.fiap.wallet.models.SignUp
import okhttp3.ResponseBody
import okhttp3.internal.http.HttpHeaders
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST

interface RetrofitService {

    @POST("/user/signup")
    fun signUp(signUp: SignUp): Call<SignUpDTO>

    @POST("/login")
    fun login(loginDTO: LoginDTO): Call<ResponseBody>

    companion object {
        private const val BASE_URL = "http://3.140.201.92"
        val retrofitService: RetrofitService by lazy {
            Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build().create(RetrofitService::class.java)
        }

        fun getnstance(): RetrofitService {
            return retrofitService
        }
    }

}