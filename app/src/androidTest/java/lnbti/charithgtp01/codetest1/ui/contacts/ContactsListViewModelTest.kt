package lnbti.charithgtp01.codetest1.ui.contacts

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import lnbti.charithgtp01.codetest1.LiveDataTestUtil.getOrAwaitValue
import lnbti.charithgtp01.codetest1.db.ContactDao
import lnbti.charithgtp01.codetest1.db.ContactDatabase
import lnbti.charithgtp01.codetest1.model.Contact
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ContactsListViewModelTest {

    //Code is Synchronously executing
    @get:Rule
    val instantExecutorRule= InstantTaskExecutorRule()

    lateinit var contactDatabase: ContactDatabase
    lateinit var contactDao: ContactDao

    // Create an instance of your ViewModel
    private lateinit var viewModel: ContactsListViewModel

    @Before
    fun setup() {
        contactDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ContactDatabase::class.java
        ).allowMainThreadQueries().build()
        contactDao=contactDatabase.contactDao()
//        // Initialize the ViewModel with a mocked repository
//        viewModel = ContactsListViewModel(contactRepository)

    }

    @Test
    fun addition_isCorrect() {
        assertEquals(5, 2 + 3)
    }

    @Test
    fun searchViewTextChange_isCorrect() {
        runBlocking {
            val allContacts = listOf(
                Contact(
                    id = 1,
                    name = "John",
                    "charithvin@gmail.com",
                    "0712919249",
                    "Gampaha",
                    false
                ),
                Contact(
                    id = 2,
                    name = "Alice",
                    "charithvin@gmail.com",
                    "0712919249",
                    "Gampaha",
                    false
                ),
                Contact(
                    id = 3,
                    name = "Bob",
                    "charithvin@gmail.com",
                    "0712919249",
                    "Gampaha",
                    false
                )
            )

            // Filter the list by a specific value
            val searchString = "John"

            val filteredList = allContacts?.filter { it.name.contains(searchString, true) }

            // Verify the size of the filtered list
            assertEquals(1, filteredList?.size)

            // Verify that the filtered list contains the expected items
            assertEquals("John", filteredList?.get(0)?.name)
        }

    }

    @Test
    fun searchViewTextChangeFromDB_isCorrect() {
        runBlocking {
            val contact1 = Contact(
                id = 0,
                name = "John",
                "charithvin@gmail.com",
                "0712919249",
                "Gampaha",
                false
            )
            val contact2= Contact(
                id = 0,
                name = "Charith",
                "charithvin@gmail.com",
                "0712919249",
                "Gampaha",
                false
            )

            contactDao.insertContact(contact1)
            contactDao.insertContact(contact2)

            //Block the code until get the live data
            val result=contactDao.getAllContacts().getOrAwaitValue()
            // Filter the list by a specific value
            val searchString = ""

            val filteredList = result.filter { it.name.contains(searchString, true) }

            // Verify the size of the filtered list
            assertEquals(2, filteredList?.size)

            // Verify that the filtered list contains the expected items
            assertEquals("John", filteredList?.get(0)?.name)
        }

    }

    @After
    fun tearDown(){
        contactDatabase.close()
    }
}