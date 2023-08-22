package lnbti.charithgtp01.codetest1.ui.contacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import lnbti.charithgtp01.codetest1.ContactDatabase
import lnbti.charithgtp01.codetest1.model.Contact

/**
 * Contacts Page View Model
 */
class ContactsListViewModel(private val repository: ContactRepository) : ViewModel() {

    private val _contactsList = MutableLiveData<List<Contact>>()
    val contactsList: LiveData<List<Contact>> get() = _contactsList
    lateinit var allUsersList: List<Contact>

    init {

    }

    /**
     * Get Server Response and Set values to live data
     */
    fun setUsersList(list: List<Contact>) {
        allUsersList = list
        _contactsList.value = list
    }

    /**
     * @param searchString Search View entered value
     * @return Data list filtered by user's full name
     */
    private fun filterList(searchString: String): List<Contact>? {
        // to get the result as list
        return allUsersList?.filter { s ->
            (s.name.lowercase()).contains(
                searchString.lowercase()
            )
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