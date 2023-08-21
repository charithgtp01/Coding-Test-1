package lnbti.charithgtp01.codetest1.ui.contacts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import lnbti.charithgtp01.codetest1.R
import lnbti.charithgtp01.codetest1.databinding.LayoutContactListBinding
import lnbti.charithgtp01.codetest1.model.Contact

/**
 * Contacts List Adapter
 */
class ContactsListAdapter constructor(private val itemClickListener: OnItemClickListener) :
    ListAdapter<Contact, ContactsListAdapter.ContactsAdapterListViewHolder>(diffUtil) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContactsAdapterListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutContactListBinding.inflate(inflater, parent, false)
        return ContactsAdapterListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactsAdapterListViewHolder, position: Int) {
        val pendingApproval = getItem(position)
        holder.binding.nameView.text =
            pendingApproval.name
        /* Show profile icon using Glide */
        Glide.with(holder.itemView.rootView).load(R.mipmap.users_icon)
            .into(holder.binding.iconView)
        holder.itemView.setOnClickListener {
            itemClickListener.itemClick(pendingApproval)
        }
    }

    /**
     * On Item Click Listener
     */
    interface OnItemClickListener {
        fun itemClick(item: Contact)
    }

    inner class ContactsAdapterListViewHolder(val binding: LayoutContactListBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }
}

/**
 * Diff Util Interface
 */
val diffUtil = object : DiffUtil.ItemCallback<Contact>() {
    override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem == newItem
    }
}

