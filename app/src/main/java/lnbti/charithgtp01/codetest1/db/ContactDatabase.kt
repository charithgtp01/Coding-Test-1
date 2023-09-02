package lnbti.charithgtp01.codetest1.db

import androidx.room.Database
import androidx.room.RoomDatabase
import lnbti.charithgtp01.codetest1.db.ContactDao
import lnbti.charithgtp01.codetest1.model.Contact

@Database(entities = [Contact::class], version = 6)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao
}