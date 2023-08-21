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

      /**
     * Get Server Response and Set values to live data
     */
    fun setUsersList(list: List<Contact>) {
        _contactsList.value = list
    }


}