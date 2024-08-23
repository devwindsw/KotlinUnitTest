package com.devwindsw.kotlinunittest

// From https://blog.logrocket.com/unit-testing-kotlin-projects-with-mockk-vs-mockito/#mockk-vs-mockito
class UserRepositoryImpl constructor(
    private val dataSource: DataSource
) : UserRepository {
    override suspend fun saveUser(user: User) {
        dataSource.save(user)
    }

    override suspend fun getUser(id: String): User {
        return dataSource.get(id) ?: throw IllegalArgumentException("User with id $id not found")
    }

    override suspend fun deleteUser(id: String) {
        dataSource.clear(id)
    }
}