package lnbti.charithgtp01.codetest1.ui.contacts

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.runBlocking
import lnbti.charithgtp01.codetest1.db.ContactDao
import lnbti.charithgtp01.codetest1.db.ContactDatabase
import lnbti.charithgtp01.codetest1.model.Contact
import lnbti.charithgtp01.codetest1.repositories.ContactRepository
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ContactsListViewModelTest {

    // Create a mock repository
    private lateinit var contactRepository: ContactRepository
    private lateinit var mockContactDao: ContactDao
    private lateinit var dataBase: ContactDatabase

    // Create a mock Observer for LiveData
//    private val observer = mock<Observer<List<Contact>>>()

    // Create an instance of your ViewModel
    private lateinit var viewModel: ContactsListViewModel

    @Before
    fun setup() {
//        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val context: Context = ApplicationProvider.getApplicationContext()
        dataBase = Room.inMemoryDatabaseBuilder(context, ContactDatabase::class.java).build()
//
//        // Initialize the repository with a mocked ContactDao
        mockContactDao = dataBase.contactDao()
        contactRepository = ContactRepository(mockContactDao)
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

            val allContacts = contactRepository.getAllContacts()
            // Filter the list by a specific value
            val searchString = "Charith"

            val filteredList = allContacts.value?.filter { it.name.contains(searchString, true) }

            // Verify the size of the filtered list
            assertEquals(1, filteredList?.size)

            // Verify that the filtered list contains the expected items
            assertEquals("John", filteredList?.get(0)?.name)
        }

    }
}