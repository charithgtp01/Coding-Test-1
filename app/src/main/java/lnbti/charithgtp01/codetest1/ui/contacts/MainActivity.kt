package lnbti.charithgtp01.codetest1.ui.contacts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import lnbti.charithgtp01.codetest1.R
import lnbti.charithgtp01.codetest1.databinding.ActivityMainBinding
import lnbti.charithgtp01.codetest1.model.Contact

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ContactsListViewModel
    private lateinit var contactsListAdapter: ContactsListAdapter
    private lateinit var allContactsList:List<Contact>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this)[ContactsListViewModel::class.java]
        binding?.vm = viewModel
        binding?.lifecycleOwner = this

        getUsersList()
        initiateAdapter()
        viewModelObservers()

    }

    /**
     * Recycle View data configuration
     */
    private fun initiateAdapter() {
        /* Initiate Adapter */
        contactsListAdapter =
            ContactsListAdapter(allContactsList)

        /* Set Adapter to Recycle View */
        binding?.recyclerView.also { it2 ->
            it2?.adapter = contactsListAdapter
        }
    }

    /**
     * Live Data Updates
     */
    private fun viewModelObservers() {
        /* Observer to catch list data
      * Update Recycle View Items using Diff Utils
      */
        viewModel.contactsList.observe(this) {
            contactsListAdapter.submitList(it)
        }
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

        viewModel.setUsersList(allContactsList)
    }

}

