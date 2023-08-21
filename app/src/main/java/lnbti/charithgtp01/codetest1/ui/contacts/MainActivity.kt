package lnbti.charithgtp01.codetest1.ui.contacts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import lnbti.charithgtp01.codetest1.R
import lnbti.charithgtp01.codetest1.databinding.ActivityMainBinding
import lnbti.charithgtp01.codetest1.model.Contact

class MainActivity : ComponentActivity() {

    private var binding: ActivityMainBinding? = null
    private lateinit var viewModel: ContactsListViewModel
    private lateinit var contactsListAdapter: ContactsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this)[ContactsListViewModel::class.java]
        binding?.vm = viewModel
        binding?.lifecycleOwner = this

        initiateAdapter()
        viewModelObservers()

    }

    /**
     * Recycle View data configuration
     */
    private fun initiateAdapter() {
        /* Initiate Adapter */
        contactsListAdapter =
            ContactsListAdapter(object : ContactsListAdapter.OnItemClickListener {
                override fun itemClick(item: Contact) {
                    when (item.name) {

                    }
                }
            })

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

}

