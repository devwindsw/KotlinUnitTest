package com.devwindsw.kotlinunittest

interface UserRepository {

    suspend fun saveUser(user: User)

    suspend fun getUser(id: String): User

    suspend fun deleteUser(id: String)
}