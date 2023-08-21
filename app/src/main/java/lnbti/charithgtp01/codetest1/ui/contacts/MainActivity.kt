package lnbti.charithgtp01.codetest1.ui.contacts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import lnbti.charithgtp01.codetest1.R
import lnbti.charithgtp01.codetest1.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {

    private var binding: ActivityMainBinding? = null
    private lateinit var viewModel: ContactsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this)[ContactsViewModel::class.java]
        binding?.vm = viewModel
        binding?.lifecycleOwner = this
    }
}

