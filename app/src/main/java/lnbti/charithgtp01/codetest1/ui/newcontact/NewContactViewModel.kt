package lnbti.charithgtp01.codetest1.ui.newcontact

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import lnbti.charithgtp01.codetest1.R
import lnbti.charithgtp01.codetest1.model.Contact
import lnbti.charithgtp01.codetest1.ui.contacts.ContactRepository
import javax.inject.Inject

@HiltViewModel
class NewContactViewModel @Inject constructor(
    private val contactRepository: ContactRepository
) :
    ViewModel() {

    var name: String = ""
    var contact: String? = null
    var email: String? = null
    var address: String? = null

    //Form live data
    private val _submitForm = MutableLiveData<NewContactFormState>()
    val registerForm: LiveData<NewContactFormState> = _submitForm

    private val _response = MutableLiveData<Boolean>()
    val response: LiveData<Boolean> = _response


    init {

    }

    fun save() {
        viewModelScope.launch {
            contactRepository.insertContact(
                Contact(
                    name = name, email = email, contactNo = contact, address = address
                )
            )

            _response.value = true
        }
    }

    /**
     * Validate Form Data
     * If all fields are valid call to api
     * Else show an error in the edit text
     */

    fun validateFields() {

        if (name.isNullOrBlank()) {
            _submitForm.value =
                NewContactFormState(nameError = R.string.enter_name)
        } else if (contact.isNullOrBlank()) {
            _submitForm.value =
                NewContactFormState(contactError = R.string.enter_contact)
        } else if (email.isNullOrBlank()) {
            _submitForm.value =
                NewContactFormState(emailError = R.string.enter_email)
        } else if (address.isNullOrBlank()) {
            _submitForm.value =
                NewContactFormState(addressError = R.string.enter_address)
        } else {
            _submitForm.value = NewContactFormState(isDataValid = true)
        }
    }
}