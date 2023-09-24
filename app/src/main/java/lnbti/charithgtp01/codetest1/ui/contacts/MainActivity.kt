package lnbti.charithgtp01.codetest1.ui.contacts

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import lnbti.charithgtp01.codetest1.Constant.OBJECT_STRING
import lnbti.charithgtp01.codetest1.R
import lnbti.charithgtp01.codetest1.databinding.ActivityMainBinding
import lnbti.charithgtp01.codetest1.interfaces.ContactsCallback
import lnbti.charithgtp01.codetest1.model.ContactItem
import lnbti.charithgtp01.codetest1.ui.editcontact.EditContactActivity
import lnbti.charithgtp01.codetest1.ui.newcontact.NewContactActivity
import lnbti.charithgtp01.codetest1.utils.Utils

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ContactsListViewModel
    private lateinit var contactsListAdapter: ContactsListAdapter
    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this)[ContactsListViewModel::class.java]
        binding.vm = viewModel
        binding.lifecycleOwner = this

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
        viewModel.allContacts.observe(this) {

            val profileItemList = it.map { item ->
                ContactItem(
                    item.id,
                    item.name,
                    item.email,
                    item.contactNo,
                    item.address,
                    item.isExpanded
                )
            }
            /* Initiate Adapter */
            contactsListAdapter =
                ContactsListAdapter(profileItemList, object : ContactsCallback {
                    override fun onEditContactClick(contact: ContactItem) {
                        val navigationPathMap = HashMap<String, String>()
                        navigationPathMap[OBJECT_STRING] = gson.toJson(contact)
                        Utils.navigateToAnotherActivityWithExtras(
                            this@MainActivity,
                            EditContactActivity::class.java, navigationPathMap
                        )
                    }

                    override fun onDeleteContactClick(contact: ContactItem) {
                        viewModel.deleteContact(contact.id)
                    }
                })
            contactsListAdapter.submitList(profileItemList)
            /* Set Adapter to Recycle View */
            binding?.recyclerView.also { it2 ->
                it2?.adapter = contactsListAdapter
            }
        }

        viewModel.filteredContacts.observe(this, Observer { it ->

            val filteredContacts = it.map { item ->
                ContactItem(
                    item.id,
                    item.name,
                    item.email,
                    item.contactNo,
                    item.address,
                    item.isExpanded
                )
            }
            contactsListAdapter.updateList(filteredContacts)
        })
    }
}

