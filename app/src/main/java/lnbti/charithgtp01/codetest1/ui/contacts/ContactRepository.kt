package lnbti.charithgtp01.codetest1.ui.contacts

import lnbti.charithgtp01.codetest1.ContactDao
import lnbti.charithgtp01.codetest1.model.Contact
import javax.inject.Inject

class ContactRepository @Inject constructor(private val contactDao: ContactDao) {
    suspend fun insertContact(contact: Contact) {
        contactDao.insertContact(contact)
    }

    suspend fun updateContact(contact: Contact) {
        contactDao.updateContact(contact)
    }

    suspend fun deleteContact(contact: Contact) {
        contactDao.deleteContact(contact)
    }

    suspend fun getAllContacts(): List<Contact> {
        return contactDao.getAllContacts()
    }
}
