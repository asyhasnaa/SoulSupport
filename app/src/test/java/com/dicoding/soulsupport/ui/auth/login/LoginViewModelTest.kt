package com.dicoding.soulsupport.ui.auth.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.dicoding.soulsupport.data.Result
import com.dicoding.soulsupport.data.model.AuthModel
import com.dicoding.soulsupport.data.pref.AuthPreferences
import com.dicoding.soulsupport.data.remote.response.LoginResponse
import com.dicoding.soulsupport.data.repository.AuthRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.junit.Before
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import kotlin.math.log

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class LoginViewModelTest{

    @Mock
    private lateinit var repository: AuthRepository
    private lateinit var loginViewModel: LoginViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        loginViewModel = LoginViewModel(repository)
    }

    @Test
    fun `login return Result_Success when login is success`() {
        val viewModel = LoginViewModel(repository)
        val email = "murphy123@gmail.com"
        val password = "12345678"

        runBlocking {
            val sussesResponse = LoginResponse("Login success")
            `when`(repository.login(email, password))
                .thenReturn(liveData { emit(Result.Success(sussesResponse)) })
        }
        val result = viewModel.loginUser(email, password)
        assertNotNull(result)
        Assert.assertNotNull(result)
    }

}