package lnbti.charithgtp01.codetest1.ui.newcontact

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.util.Util
import dagger.hilt.android.AndroidEntryPoint
import lnbti.charithgtp01.codetest1.R
import lnbti.charithgtp01.codetest1.databinding.ActivityNewContactBinding
import lnbti.charithgtp01.codetest1.utils.UIUtils.Companion.initiateActionBarWithoutHomeButton
import lnbti.charithgtp01.codetest1.utils.UIUtils.Companion.normalState
import lnbti.charithgtp01.codetest1.utils.Utils

@AndroidEntryPoint
class NewContactActivity : AppCompatActivity() {

    private lateinit var registerViewModel: NewContactViewModel
    private lateinit var binding: ActivityNewContactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initiateDataBinding()
        initiateView()
        viewModelObservers()
    }

    private fun initiateDataBinding() {
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_new_contact)

        //Data binding
        registerViewModel = ViewModelProvider(this)[NewContactViewModel::class.java]
        binding.vm = registerViewModel
        binding.lifecycleOwner = this
    }

    private fun initiateView() {
        initiateActionBarWithoutHomeButton(
            binding.actionBar.mainLayout,
            getString(R.string.add_contact)
        ) { onBackPressed() }

        binding.btnSubmit.setOnClickListener {
            registerViewModel.validateFields()
        }

    }

    private fun viewModelObservers() {
        //If all fields are correct call the change password api
        registerViewModel.registerForm.observe(this@NewContactActivity, Observer {
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
                registerViewModel.save()
            }
        })


        //Waiting for response
        registerViewModel.response.observe(this@NewContactActivity) {
            Utils.goToHomeActivity(this)
        }
    }
}

