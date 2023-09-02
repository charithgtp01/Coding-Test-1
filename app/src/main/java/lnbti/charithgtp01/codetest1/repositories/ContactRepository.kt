package lnbti.charithgtp01.codetest1.repositories

import androidx.lifecycle.LiveData
import lnbti.charithgtp01.codetest1.db.ContactDao
import lnbti.charithgtp01.codetest1.model.Contact
import javax.inject.Inject

open class ContactRepository @Inject constructor(private val contactDao: ContactDao) {
    fun getAllContacts(): LiveData<List<Contact>> {
        return contactDao.getAllContacts()
    }

    suspend fun insertContact(contact: Contact) {
        contactDao.insertContact(contact)
    }

    suspend fun updateContact(contact: Contact) {
        contactDao.updateContact(contact)
    }

    suspend fun deleteContact(id: Int) {
        contactDao.deleteContact(id)
    }
}
