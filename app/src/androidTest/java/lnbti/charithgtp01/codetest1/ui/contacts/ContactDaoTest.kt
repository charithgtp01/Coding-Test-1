package lnbti.charithgtp01.codetest1.ui.contacts

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.runBlocking
import lnbti.charithgtp01.codetest1.LiveDataTestUtil.getOrAwaitValue
import lnbti.charithgtp01.codetest1.db.ContactDao
import lnbti.charithgtp01.codetest1.db.ContactDatabase
import lnbti.charithgtp01.codetest1.model.Contact
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ContactDaoTest {

    //Code is Synchronously executing
    @get:Rule
    val instantExecutorRule= InstantTaskExecutorRule()

    lateinit var contactDatabase: ContactDatabase
    lateinit var contactDao: ContactDao

    @Before
    fun setup() {
        contactDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ContactDatabase::class.java
        ).allowMainThreadQueries().build()
        contactDao=contactDatabase.contactDao()
    }

    @Test
    fun insertContact_expectedSingleContact()= runBlocking{
        val contact = Contact(
            id = 0,
            name = "John",
            "charithvin@gmail.com",
            "0712919249",
            "Gampaha",
            false
        )

        contactDao.insertContact(contact)

        //Block the code until get the live data
        val result=contactDao.getAllContacts().getOrAwaitValue()
        Assert.assertEquals(1,result.size)
        Assert.assertEquals("John",result[0].name)
    }

    @After
    fun tearDown(){
        contactDatabase.close()
    }

}