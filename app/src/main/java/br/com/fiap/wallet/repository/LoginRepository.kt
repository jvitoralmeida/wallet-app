package br.com.fiap.wallet.repository

import br.com.fiap.wallet.model.dto.LoginDTO
import br.com.fiap.wallet.models.SignUp
import br.com.fiap.wallet.rest.RetrofitService

class LoginRepository constructor(val retrofitService: RetrofitService) {

    //fun signUp(signUp: SignUp) = retrofitService.signUp(signUp)
    fun login(login: LoginDTO) = retrofitService.login(login)
}