package com.devwindsw.kotlinunittest

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.slot
import java.util.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

//From https://blog.logrocket.com/unit-testing-kotlin-projects-with-mockk-vs-mockito/#mockk-vs-mockito

@OptIn(ExperimentalCoroutinesApi::class)
class UserRepositoryImplTest {

    private val dataSource = mockk<DataSource>(relaxed = true)
    private val sut = UserRepositoryImpl(dataSource)

    @Test
    fun `verify correct user params are used`() = runTest {
        val user = buildUser()

        sut.saveUser(user)

        val captor = slot<User>()

        coVerify { dataSource.save(capture(captor)) }

        Assert.assertEquals(user.email, captor.captured.email)
    }

    @Test
    fun `verify correct user is retrieved`() = runTest {
        val email = "enyasonjnr@gmail.com"

        coEvery { dataSource.get(any()) } returns buildUser()

        val user = sut.getUser(email)

        Assert.assertEquals(email, user.email)
    }

    @Test
    fun `verify user is deleted`() = runTest {
        val email = "enyasonjnr@gmail.com"
        sut.deleteUser(email)

        coVerify { dataSource.clear(any()) }
    }

    companion object {
        fun buildUser() = User(
            id = UUID.randomUUID().toString(),
            email = "enyasonjnr@gmail.com",
            fullName = "Emmanuel Enya",
            verificationStatus = User.VerificationStatus.Verified,
            memberShipStatus = User.MemberShipStatus.Free
        )
    }
}