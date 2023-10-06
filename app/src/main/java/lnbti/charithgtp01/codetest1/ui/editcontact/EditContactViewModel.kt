package lnbti.charithgtp01.codetest1.ui.editcontact

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import lnbti.charithgtp01.codetest1.R
import lnbti.charithgtp01.codetest1.model.Contact
import lnbti.charithgtp01.codetest1.repositories.ContactRepositoryImpl
import javax.inject.Inject

@HiltViewModel
class EditContactViewModel @Inject constructor(
    private val contactRepository: ContactRepositoryImpl
) :
    ViewModel() {

    var name: String = ""
    var contact: String? = null
    var email: String? = null
    var address: String? = null

    private val _selectedContact = MutableLiveData<Contact>()
    val selectedContact: LiveData<Contact> get() = _selectedContact

    //Form live data
    private val _submitForm = MutableLiveData<EditContactFormState>()
    val registerForm: LiveData<EditContactFormState> = _submitForm

    private val _response = MutableLiveData<Boolean>()
    val response: LiveData<Boolean> = _response


    init {

    }

    fun update(id: Int) {
        viewModelScope.launch {
            contactRepository.updateContact(
                Contact(
                    id = id, name = name, email = email, contactNo = contact, address = address
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
                EditContactFormState(nameError = R.string.enter_name)
        } else if (contact.isNullOrBlank()) {
            _submitForm.value =
                EditContactFormState(contactError = R.string.enter_contact)
        } else if (email.isNullOrBlank()) {
            _submitForm.value =
                EditContactFormState(emailError = R.string.enter_email)
        } else if (address.isNullOrBlank()) {
            _submitForm.value =
                EditContactFormState(addressError = R.string.enter_address)
        } else {
            _submitForm.value = EditContactFormState(isDataValid = true)
        }
    }

    fun setSelectedContact(selectedContact: Contact) {
        _selectedContact.value = selectedContact
        name = selectedContact.name
        contact = selectedContact.contactNo
        email = selectedContact.email
        address = selectedContact.address

    }
}