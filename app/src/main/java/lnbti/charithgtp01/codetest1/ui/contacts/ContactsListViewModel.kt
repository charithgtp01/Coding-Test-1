package lnbti.charithgtp01.codetest1.ui.contacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import lnbti.charithgtp01.codetest1.model.Contact
import lnbti.charithgtp01.codetest1.repositories.ContactRepository
import lnbti.charithgtp01.codetest1.ui.newcontact.NewContactFormState
import javax.inject.Inject

/**
 * Contacts Page View Model
 */
@HiltViewModel
class ContactsListViewModel @Inject constructor(private val repository: ContactRepository) :
    ViewModel() {

    val allContacts: LiveData<List<Contact>> = repository.getAllContacts()

    // LiveData for filtered contacts
    private val _filteredContacts = MutableLiveData<List<Contact>>()
    val filteredContacts: LiveData<List<Contact>> = _filteredContacts

    /**
     * Function to update filteredContacts based on search query
     * @param searchString Entering value
     */
    fun onSearchViewTextChanged(searchString: CharSequence) {
        _filteredContacts.value = allContacts.value?.filter { it.name.contains(searchString, true) }
    }

    fun insertContact(contact: Contact) {
        viewModelScope.launch {
            repository.insertContact(contact)
        }
    }

    fun updateContact(contact: Contact) {
        viewModelScope.launch {
            repository.updateContact(contact)
        }
    }

    fun deleteContact(id: Int) {
        viewModelScope.launch {
            repository.deleteContact(id)
        }
    }

}