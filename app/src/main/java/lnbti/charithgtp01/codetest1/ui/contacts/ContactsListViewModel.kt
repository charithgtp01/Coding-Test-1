package lnbti.charithgtp01.codetest1.ui.contacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import lnbti.charithgtp01.codetest1.model.Contact

/**
 * Contacts Page View Model
 */
class ContactsListViewModel : ViewModel() {

    private val _contactsList = MutableLiveData<List<Contact>>()
    val contactsList: LiveData<List<Contact>> get() = _contactsList
    lateinit var allUsersList: List<Contact>

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

}