package lnbti.charithgtp01.codetest1.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import lnbti.charithgtp01.codetest1.model.Contact

@Dao
interface ContactDao {
    @Insert
    suspend fun insertContact(user: Contact): Long

    @Query("SELECT * FROM contact_table")
    suspend fun getAllContacts():List<Contact>

    @Update
    suspend fun updateContact(contact:Contact)

    @Delete
    suspend fun deleteContact(contact:Contact)

    @Query("DELETE FROM contact_table")
    suspend fun deleteAllContacts()
}