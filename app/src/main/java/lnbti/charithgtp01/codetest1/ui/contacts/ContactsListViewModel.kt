package lnbti.charithgtp01.codetest1.ui.contacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import lnbti.charithgtp01.codetest1.model.Contact
import lnbti.charithgtp01.codetest1.repositories.ContactRepositoryImpl
import javax.inject.Inject

/**
 * Contacts Page View Model
 */
@HiltViewModel
class ContactsListViewModel @Inject constructor(private val repository: ContactRepositoryImpl) :
    ViewModel() {

    val allContacts: LiveData<List<Contact>> = repository.allContacts

    // LiveData for filtered contacts
    private val _filteredContacts = MutableLiveData<List<Contact>>()
    val filteredContacts: LiveData<List<Contact>> = _filteredContacts

    init {

    }

    /**
     * @param searchString Search View entered value
     * @return Data list filtered by user's full name
     */
    private fun filterList(searchString: String): List<Contact>? {
        // to get the result as list
        return allContacts.value?.filter { s ->
            s.name?.contains(searchString) == true
        }
    }

    /**
     * Function to update filteredContacts based on search query
     * @param searchString Entering value
     */
    fun onSearchViewTextChanged(searchString: CharSequence) {
        val value = searchString.toString()
        if (value.isNullOrBlank()) {
//            _contactsList.value = allContacts.value
        } else {
//            _contactsList.value = filterList(value)
        }

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