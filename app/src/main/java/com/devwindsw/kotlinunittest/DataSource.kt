package com.devwindsw.kotlinunittest

class DataSource {

    private val db = mutableMapOf<String, User>()

    fun save(user: User) = db.let { it[user.email] = user }

    fun get(key: String): User? = db[key]

    fun clear(key: String) = db.remove(key)

    fun clearAll() = db.clear()
}
