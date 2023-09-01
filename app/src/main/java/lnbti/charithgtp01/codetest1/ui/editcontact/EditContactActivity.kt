package lnbti.charithgtp01.codetest1.ui.editcontact

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.util.Util
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import lnbti.charithgtp01.codetest1.Constant
import lnbti.charithgtp01.codetest1.R
import lnbti.charithgtp01.codetest1.databinding.ActivityEditContactBinding
import lnbti.charithgtp01.codetest1.databinding.ActivityNewContactBinding
import lnbti.charithgtp01.codetest1.model.Contact
import lnbti.charithgtp01.codetest1.model.ContactItem
import lnbti.charithgtp01.codetest1.utils.UIUtils.Companion.initiateActionBarWithoutHomeButton
import lnbti.charithgtp01.codetest1.utils.UIUtils.Companion.normalState
import lnbti.charithgtp01.codetest1.utils.Utils

@AndroidEntryPoint
class EditContactActivity : AppCompatActivity() {

    private lateinit var editContactViewModel: EditContactViewModel
    private lateinit var binding: ActivityEditContactBinding
    private val gson = Gson()
    private lateinit var selectedContact: Contact
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initiateDataBinding()
        initiateView()
        initData()
        viewModelObservers()
    }

    private fun initData() {
        val selectedContactString = intent.getStringExtra(Constant.OBJECT_STRING)
        selectedContact = gson.fromJson(selectedContactString, Contact::class.java)
        editContactViewModel.setSelectedContact(selectedContact)
    }

    private fun initiateDataBinding() {
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_edit_contact)

        //Data binding
        editContactViewModel = ViewModelProvider(this)[EditContactViewModel::class.java]
        binding.vm = editContactViewModel
        binding.lifecycleOwner = this
    }

    private fun initiateView() {
        initiateActionBarWithoutHomeButton(
            binding.actionBar.mainLayout,
            getString(R.string.edit_contact)
        ) { onBackPressed() }

        binding.btnSubmit.setOnClickListener {
            editContactViewModel.validateFields()
        }

    }

    private fun viewModelObservers() {
        //If all fields are correct call the change password api
        editContactViewModel.registerForm.observe(this@EditContactActivity, Observer {
            val formState = it ?: return@Observer

            if (formState.nameError != null) {
                binding.nameInputText.error =
                    getString(formState.nameError!!)
            } else
                normalState(binding.nameInputText)

            if (formState.contactError != null) {
                binding.contactInputText.error =
                    getString(formState.contactError!!)
            } else
                normalState(binding.contactInputText)

            if (formState.emailError != null) {
                binding.emailInputText.error =
                    getString(formState.emailError!!)
            } else
                normalState(binding.emailInputText)

            if (formState.addressError != null) {
                binding.addressInputText.error =
                    getString(formState.addressError!!)
            } else
                normalState(binding.addressInputText)

            if (formState.isDataValid) {
                editContactViewModel.update(selectedContact.id)
            }
        })


        //Waiting for response
        editContactViewModel.response.observe(this@EditContactActivity) {
            Utils.goToHomeActivity(this)
        }
    }
}

