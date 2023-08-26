package lnbti.charithgtp01.codetest1.ui.contacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import lnbti.charithgtp01.codetest1.model.Contact
import lnbti.charithgtp01.codetest1.repositories.ContactRepository
import javax.inject.Inject

/**
 * Contacts Page View Model
 */
@HiltViewModel
class ContactsListViewModel @Inject constructor(private val repository: ContactRepository) : ViewModel() {

    private val _contactsList = MutableLiveData<List<Contact>>()
    val contactsList: LiveData<List<Contact>> get() = _contactsList
    lateinit var allUsersList: List<Contact>

    init {

    }

    /**
     * @param searchString Search View entered value
     * @return Data list filtered by user's full name
     */
    private fun filterList(searchString: String): List<Contact>? {
        // to get the result as list
        return allUsersList?.filter { s ->
            s.name?.contains(searchString) == true
        }
    }

    /**
     * Search View on text change listener
     * @param searchString Entering value
     */
    fun onSearchViewTextChanged(searchString: CharSequence?) {
        val value = searchString.toString()
        if (value.isNullOrBlank()) {
            _contactsList.value = allUsersList
        } else {
            _contactsList.value = filterList(value)
        }
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

    fun deleteContact(contact: Contact) {
        viewModelScope.launch {
            repository.deleteContact(contact)
        }
    }

    fun getContacts() {
        viewModelScope.launch {
            allUsersList = repository.getAllContacts()
            _contactsList.value = allUsersList
        }
    }

}