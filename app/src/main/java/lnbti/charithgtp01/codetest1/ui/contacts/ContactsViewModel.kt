package lnbti.charithgtp01.codetest1.ui.contacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * Contacts Page View Model
 */
class ContactsViewModel : ViewModel() {

    private val _contactsList = MutableLiveData<List<String>>()
    val contactsList: LiveData<List<String>> get() = _contactsList

    lateinit var allContactsList: List<String>

    /**
     * This will call when the View Model Created
     */
    init {
        getUsersList()
    }


    /**
     * Get Server Response and Set values to live data
     */
    private fun getUsersList() {
        viewModelScope.launch {

        }
    }


}