package lnbti.charithgtp01.codetest1.ui.editcontact

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import lnbti.charithgtp01.codetest1.LiveDataTestUtil
import lnbti.charithgtp01.codetest1.db.ContactDao
import lnbti.charithgtp01.codetest1.db.ContactDatabase
import lnbti.charithgtp01.codetest1.repositories.ContactRepository
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EditContactViewModelTest{
    @get:Rule
    val rule = InstantTaskExecutorRule()
    private lateinit var viewModel: EditContactViewModel
    private lateinit var contactRepository: ContactRepository
    lateinit var contactDatabase: ContactDatabase
    lateinit var contactDao: ContactDao

    @Before
    fun setup() {
        contactDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ContactDatabase::class.java
        ).allowMainThreadQueries().build()
        contactDao=contactDatabase.contactDao()
        contactRepository= ContactRepository(contactDao)
        viewModel = EditContactViewModel(contactRepository)
    }

    //Validate all data entered Test Case
    @Test
    fun testValidateFields_WithValidData() {
        // Set up ViewModel fields with valid data
        viewModel.name = "Charith Vinodya"
        viewModel.contact = "0712919248"
        viewModel.email = "charith@gmail.com"
        viewModel.address = "123 Main St"

        // Call the validateFields function
        viewModel.validateFields()

        // Assert that the LiveData state indicates valid data
        val formState = LiveDataTestUtil.getValue(viewModel.registerForm)
        formState?.let { assertTrue(it.isDataValid) }
        assertNull(formState?.nameError)
        assertNull(formState?.contactError)
        assertNull(formState?.emailError)
        assertNull(formState?.addressError)
    }


    //Validate name Empty Test Case
    @Test
    fun testValidateFields_WithEmptyName() {
        // Arrange: Set up the ViewModel's fields with an empty name
        viewModel.name = ""
        viewModel.contact = "0712193984"
        viewModel.email = "john@example.com"
        viewModel.address = "123 Main St"

        // Act: Call the validateFields function
        viewModel.validateFields()

        // Assert: Verify that the LiveData emitted the expected EditContactFormState
        val formState = viewModel.registerForm.value
        assertNotNull(formState)
        assertFalse(formState!!.isDataValid)
        assertNotNull(formState.nameError)
        assertNull(formState.contactError)
        assertNull(formState.emailError)
        assertNull(formState.addressError)
    }

    //Remove any LiveData observers to avoid memory leaks.
    @After
    fun teardown() {
        // Clean up any LiveData observers to prevent leaks
        viewModel.registerForm.removeObserver { }
    }
}