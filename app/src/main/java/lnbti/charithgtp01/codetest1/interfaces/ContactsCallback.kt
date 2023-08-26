package lnbti.charithgtp01.codetest1.interfaces

import lnbti.charithgtp01.codetest1.model.Contact
import lnbti.charithgtp01.codetest1.model.ContactItem

interface ContactsCallback {
    fun onEditContactClick(contact: ContactItem)
    fun onDeleteContactClick(contact: ContactItem)
}