package lnbti.charithgtp01.codetest1.db

import androidx.lifecycle.LiveData
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
    fun getAllContacts(): LiveData<List<Contact>>

    @Update
    suspend fun updateContact(contact:Contact)

    @Query("DELETE FROM contact_table WHERE id = :id")
    suspend fun deleteContact(id:Int)

    @Query("DELETE FROM contact_table")
    suspend fun deleteAllContacts()
}