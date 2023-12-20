package com.dicoding.soulsupport.ui.auth.register

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.liveData
import com.dicoding.soulsupport.data.Result
import com.dicoding.soulsupport.data.remote.response.RegisterResponse
import com.dicoding.soulsupport.data.repository.AuthRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RegisterViewModelTest {

    @Mock
    private lateinit var repository: AuthRepository

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `registerUser return Result_Success when registration is success`() {
        val viewModel = RegisterViewModel(repository)
        val name = "Murphy"
        val email = "murphy123@gmail.com"
        val password = "12345678"
        val confirmPassword = "12345678"

        runBlocking {
            val sussessResponse = RegisterResponse("Registration success")
            Mockito.`when`(repository.register(name, email, password, confirmPassword))
                .thenReturn(liveData { emit(Result.Success(sussessResponse)) })
        }
        val result = viewModel.registerUser(name, email, password, confirmPassword)
        Assert.assertNotNull(result)
    }
}

