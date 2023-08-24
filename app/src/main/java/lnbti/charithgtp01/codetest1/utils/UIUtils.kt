package lnbti.charithgtp01.codetest1.utils

import android.R
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import com.google.android.material.textfield.TextInputLayout
import lnbti.charithgtp01.codetest1.databinding.ActionBarWithoutHomeLayoutBinding
import lnbti.charithgtp01.codetest1.interfaces.ActionBarWithoutHomeListener
import java.util.Locale

class UIUtils {
   companion object{
       /**
        * Action Bar Initiation without Home Button
        * @param parent Parent layout of the action bar layout
        * @param activityTitle Action Bar Title String
        * @param actionBarListener Action Bar image button listener
        */
       fun initiateActionBarWithoutHomeButton(
           parent: ViewGroup,
           activityTitle: String,
           actionBarListener: ActionBarWithoutHomeListener
       ) {
           // Inflate the layout using DataBindingUtil
           val binding =
               ActionBarWithoutHomeLayoutBinding.inflate(
                   LayoutInflater.from(parent.context),
                   parent,
                   true
               )
           binding.tvTitle.text = activityTitle.uppercase(Locale.getDefault())
           binding.backBtn.setOnClickListener {
               actionBarListener.backPressed()
           }
       }

       fun normalState(inputLayout: TextInputLayout?) {
           inputLayout?.isHelperTextEnabled = false
           inputLayout?.isErrorEnabled = false
           inputLayout?.endIconMode = TextInputLayout.END_ICON_NONE
       }

       fun validState(inputLayout: TextInputLayout?, icon: Int) {
           val myColorStateList = ColorStateList(
               arrayOf(intArrayOf(R.attr.state_activated), intArrayOf(R.attr.state_enabled)),
               intArrayOf(
                   Color.parseColor("#FF71BDC5"),  //1
                   Color.parseColor("#FF71BDC5")
               )
           )
           inputLayout?.isErrorEnabled = false
           inputLayout?.endIconMode = TextInputLayout.END_ICON_CUSTOM
           inputLayout?.setEndIconDrawable(icon)
           inputLayout?.setEndIconTintList(myColorStateList)
       }
   }
}