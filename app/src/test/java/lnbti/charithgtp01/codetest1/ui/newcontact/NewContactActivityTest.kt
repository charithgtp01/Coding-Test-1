package lnbti.charithgtp01.codetest1.ui.newcontact

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import lnbti.charithgtp01.codetest1.repositories.ContactRepositoryImpl
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import lnbti.charithgtp01.codetest1.R
import lnbti.charithgtp01.codetest1.repositories.ContactRepository
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.never
import org.mockito.Mockito.verify

class NewContactActivityTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: NewContactViewModel

    @Mock
    lateinit var formStateObserver: Observer<NewContactFormState>

    @Mock
    lateinit var contactRepository: ContactRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = NewContactViewModel(contactRepository)
        viewModel.registerForm.observeForever(formStateObserver)

    }

    //Validate name Empty Test Case
    @Test
    fun testValidateFieldsWithEmptyName() {
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
//    @After
//    fun tearDown() {
    // Clean up any LiveData observers to prevent leaks
//        viewModel.registerForm.removeObserver { }
//    }
}