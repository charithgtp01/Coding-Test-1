package lnbti.charithgtp01.codetest1.ui.contacts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import lnbti.charithgtp01.codetest1.ContactDatabase
import lnbti.charithgtp01.codetest1.R
import lnbti.charithgtp01.codetest1.databinding.ActivityMainBinding
import lnbti.charithgtp01.codetest1.model.Contact

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ContactsListViewModel
    private lateinit var contactsListAdapter: ContactsListAdapter
//    private lateinit var allContactsList: List<Contact>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val contactDao = ContactDatabase.getInstance(this).contactDao()

        val repository = ContactRepository(contactDao)

        val viewModelFactory = ContactViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[ContactsListViewModel::class.java]

//        val contact = Contact(1, "John Doe", "john@example.com", "1234567890", "avatar.jpg", "123 Main St", false)
//        viewModel.insertContact(contact)

        binding.vm = viewModel
        binding.lifecycleOwner = this
        viewModel.getContacts()

        viewModelObservers()

    }

    /**
     * Live Data Updates
     */
    private fun viewModelObservers() {
        /* Observer to catch list data
      * Update Recycle View Items using Diff Utils
      */
        viewModel.contactsList.observe(this) {
            /* Initiate Adapter */
            contactsListAdapter =
                ContactsListAdapter(it)
            contactsListAdapter.submitList(it)
            /* Set Adapter to Recycle View */
            binding?.recyclerView.also { it2 ->
                it2?.adapter = contactsListAdapter
            }
        }
    }


    /**
     * Get Server Response and Set values to live data
     */
    private suspend fun getUsersList() {


//        allContactsList = listOf(
//            Contact(
//                1,
//                "Charith Vinodya",
//                "charithvin@gmail.com",
//                "0712919248",
//                "",
//                "Bandarawatta, Gampaha"
//            ),
//            Contact(
//                2,
//                "Charith Vinodya",
//                "charithvin@gmail.com",
//                "0712919248",
//                "",
//                "Bandarawatta, Gampaha"
//            ),
//            Contact(
//                3,
//                "Charith Vinodya",
//                "charithvin@gmail.com",
//                "0712919248",
//                "",
//                "Bandarawatta, Gampaha"
//            ),
//            Contact(
//                4,
//                "Charith Vinodya",
//                "charithvin@gmail.com",
//                "0712919248",
//                "",
//                "Bandarawatta, Gampaha"
//            ),
//            Contact(
//                5,
//                "Charith Vinodya",
//                "charithvin@gmail.com",
//                "0712919248",
//                "",
//                "Bandarawatta, Gampaha"
//            ),
//            Contact(
//                6,
//                "Charith Vinodya",
//                "charithvin@gmail.com",
//                "0712919248",
//                "",
//                "Bandarawatta, Gampaha"
//            ),
//            Contact(
//                7,
//                "Charith Vinodya",
//                "charithvin@gmail.com",
//                "0712919248",
//                "",
//                "Bandarawatta, Gampaha"
//            ),
//        )

//        viewModel.setUsersList(allContactsList)
    }

}

