package lnbti.charithgtp01.codetest1.ui.contacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import lnbti.charithgtp01.codetest1.BR
import lnbti.charithgtp01.codetest1.Constant
import lnbti.charithgtp01.codetest1.R
import lnbti.charithgtp01.codetest1.databinding.CollapsedLayoutContactListBinding
import lnbti.charithgtp01.codetest1.databinding.ExpandedLayoutContactListBinding
import lnbti.charithgtp01.codetest1.databinding.ItemEmptyBinding
import lnbti.charithgtp01.codetest1.interfaces.ContactsCallback
import lnbti.charithgtp01.codetest1.model.ContactItem
import javax.inject.Inject


/**
 * Contacts List Adapter
 */
class ContactsListAdapter @Inject constructor(
    private var dataList: List<ContactItem>,
    private val callbacks: ContactsCallback
) :
    ListAdapter<ContactItem, ContactsListAdapter.ContactsAdapterListViewHolder>(diffUtil) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContactsAdapterListViewHolder {

        return when (viewType) {
            Constant.VIEW_TYPE_EXPANDED -> {
                val binding = ExpandedLayoutContactListBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                ContactsAdapterListViewHolder(binding)
            }

            Constant.VIEW_TYPE_NORMAL -> {
                val binding = CollapsedLayoutContactListBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                ContactsAdapterListViewHolder(binding)
            }

            Constant.VIEW_TYPE_EMPTY -> {
                val binding = ItemEmptyBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                ContactsAdapterListViewHolder(binding)
            }

            else -> {
                throw IllegalArgumentException("Invalid view type")
            }
        }
    }

    override fun onBindViewHolder(holder: ContactsAdapterListViewHolder, position: Int) {
        //If this logic remove java.lang.IndexOutOfBoundsException: Index: 1, Size: 1 returning
        //This needs to clarify
        if (dataList.size != position)
            if (dataList.isNotEmpty()) {
                val contactItem = dataList[position]
                holder.bind(contactItem)
                holder.binding.root.setOnClickListener {
                    contactItem.isExpanded = !contactItem.isExpanded
                    notifyDataSetChanged()
                }
            }
    }

    fun updateList(newContacts: List<ContactItem>) {
//        val diffCallback = ContactDiffCallback(dataList, newContacts)
//        val diffResult = DiffUtil.calculateDiff(diffCallback)

        dataList = listOf()
        dataList = newContacts
        notifyDataSetChanged()
//        diffResult.dispatchUpdatesTo(this) // 'this' refers to your RecyclerView.Adapter

    }

    override fun getItemViewType(position: Int): Int {
        //If this logic remove java.lang.IndexOutOfBoundsException: Index: 1, Size: 1 returning
        //This needs to clarify
        if (dataList.size != position)
            if (dataList.isNotEmpty())
                return if (dataList[position].isExpanded) {
                    Constant.VIEW_TYPE_EXPANDED
                } else
                    Constant.VIEW_TYPE_NORMAL

        return Constant.VIEW_TYPE_EMPTY
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

class ContactDiffCallback(
    private val oldList: List<ContactItem>,
    private val newList: List<ContactItem>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        // Compare unique identifiers of items to determine if they are the same
        return oldList[oldPosition].id == newList[newPosition].id
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        // Compare contents of items to determine if their content is the same
        return oldList[oldPosition] == newList[newPosition]
    }
}

