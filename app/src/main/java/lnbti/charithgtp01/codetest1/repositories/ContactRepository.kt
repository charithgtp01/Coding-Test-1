package lnbti.charithgtp01.codetest1.repositories

import lnbti.charithgtp01.codetest1.model.Contact

interface ContactRepository {
    suspend fun insertContact(contact: Contact)
}