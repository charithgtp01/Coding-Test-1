package lnbti.charithgtp01.codetest1.bindingadapters

import android.view.View
import androidx.databinding.BindingAdapter

object ContactListItemBindingAdapter {
    @BindingAdapter("onClick")
    @JvmStatic
    fun setOnClick(view: View, clickListener: View.OnClickListener) {
        view.setOnClickListener(clickListener)
    }
}