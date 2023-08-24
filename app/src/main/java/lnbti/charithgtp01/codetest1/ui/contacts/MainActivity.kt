package lnbti.charithgtp01.codetest1.ui.contacts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.util.Util
import dagger.hilt.android.AndroidEntryPoint
import lnbti.charithgtp01.codetest1.ContactDatabase
import lnbti.charithgtp01.codetest1.R
import lnbti.charithgtp01.codetest1.databinding.ActivityMainBinding
import lnbti.charithgtp01.codetest1.model.Contact
import lnbti.charithgtp01.codetest1.ui.newcontact.NewContactActivity
import lnbti.charithgtp01.codetest1.utils.Utils

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ContactsListViewModel
    private lateinit var contactsListAdapter: ContactsListAdapter
//    private lateinit var allContactsList: List<Contact>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = ViewModelProvider(this)[ContactsListViewModel::class.java]

//        val contact = Contact(1, "John Doe", "john@example.com", "1234567890", "avatar.jpg", "123 Main St", false)
//        viewModel.insertContact(contact)

        binding.vm = viewModel
        binding.lifecycleOwner = this
        viewModel.getContacts()

        initView()
        viewModelObservers()

    }

    private fun initView() {
        binding.floatingActionButton.setOnClickListener {
            Utils.navigateToAnotherActivity(this@MainActivity, NewContactActivity::class.java)
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

