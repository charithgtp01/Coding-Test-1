package lnbti.charithgtp01.codetest1.ui.contacts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import lnbti.charithgtp01.codetest1.BR
import lnbti.charithgtp01.codetest1.Constant
import lnbti.charithgtp01.codetest1.databinding.CollapsedLayoutContactListBinding
import lnbti.charithgtp01.codetest1.databinding.ExpandedLayoutContactListBinding
import lnbti.charithgtp01.codetest1.interfaces.ContactsCallback
import lnbti.charithgtp01.codetest1.model.ContactItem
import javax.inject.Inject

/**
 * Contacts List Adapter
 */
class ContactsListAdapter @Inject constructor(private val dataList: List<ContactItem>, private val callbacks: ContactsCallback) :
    ListAdapter<ContactItem, ContactsListAdapter.ContactsAdapterListViewHolder>(diffUtil) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContactsAdapterListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = if (viewType == 1) {
            CollapsedLayoutContactListBinding.inflate(inflater, parent, false)
        } else {
            ExpandedLayoutContactListBinding.inflate(inflater, parent, false)
        }
        return ContactsAdapterListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactsAdapterListViewHolder, position: Int) {
        holder.binding.root.setOnClickListener {
            getItem(position).isExpanded = !getItem(position).isExpanded
            notifyDataSetChanged()
        }
        val contact = getItem(position)
        holder.bind(contact)
    }

    override fun getItemViewType(position: Int): Int {
        return if (dataList[position].isExpanded) Constant.VIEW_TYPE_EXPANDED else Constant.VIEW_TYPE_NORMAL
    }

    inner class ContactsAdapterListViewHolder(val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ContactItem) {
            binding.setVariable(BR.item, item)
            binding.setVariable(BR.callbacks, callbacks)
            binding.executePendingBindings()
        }
    }
}

/**
 * Diff Util Interface
 */
val diffUtil = object : DiffUtil.ItemCallback<ContactItem>() {
    override fun areItemsTheSame(oldItem: ContactItem, newItem: ContactItem): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: ContactItem, newItem: ContactItem): Boolean {
        return oldItem == newItem
    }
}

