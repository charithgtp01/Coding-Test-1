package lnbti.charithgtp01.codetest1.ui.contacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import lnbti.charithgtp01.codetest1.model.Contact

/**
 * Contacts Page View Model
 */
class ContactsViewModel : ViewModel() {

    private val _contactsList = MutableLiveData<List<Contact>>()
    val contactsList: LiveData<List<Contact>> get() = _contactsList

    lateinit var allContactsList: List<Contact>

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
        allContactsList = listOf(
            Contact(
                1,
                "Charith Vinodya",
                "charithvin@gmail.com",
                "0712919248",
                "",
                "Bandarawatta, Gampaha"
            ),
            Contact(
                2,
                "Charith Vinodya",
                "charithvin@gmail.com",
                "0712919248",
                "",
                "Bandarawatta, Gampaha"
            ),
            Contact(
                3,
                "Charith Vinodya",
                "charithvin@gmail.com",
                "0712919248",
                "",
                "Bandarawatta, Gampaha"
            ),
            Contact(
                4,
                "Charith Vinodya",
                "charithvin@gmail.com",
                "0712919248",
                "",
                "Bandarawatta, Gampaha"
            ),
            Contact(
                5,
                "Charith Vinodya",
                "charithvin@gmail.com",
                "0712919248",
                "",
                "Bandarawatta, Gampaha"
            ),
            Contact(
                6,
                "Charith Vinodya",
                "charithvin@gmail.com",
                "0712919248",
                "",
                "Bandarawatta, Gampaha"
            ),
            Contact(
                7,
                "Charith Vinodya",
                "charithvin@gmail.com",
                "0712919248",
                "",
                "Bandarawatta, Gampaha"
            ),
        )
        _contactsList.value = allContactsList
    }


}