package lnbti.charithgtp01.codetest1.repositories

import androidx.lifecycle.LiveData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import lnbti.charithgtp01.codetest1.db.ContactDao
import lnbti.charithgtp01.codetest1.model.Contact
import javax.inject.Inject
import javax.inject.Singleton

class ContactRepositoryImpl @Inject constructor(private val contactDao: ContactDao) :
    ContactRepository {

    val allContacts: LiveData<List<Contact>> = contactDao.getAllContacts()

    override suspend fun insertContact(contact: Contact) {
        contactDao.insertContact(contact)
    }

    suspend fun updateContact(contact: Contact) {
        contactDao.updateContact(contact)
    }

    suspend fun deleteContact(id: Int) {
        contactDao.deleteContact(id)
    }
}
