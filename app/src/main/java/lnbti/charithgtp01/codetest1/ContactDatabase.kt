package lnbti.charithgtp01.codetest1

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import lnbti.charithgtp01.codetest1.model.Contact

@Database(entities = [Contact::class], version = 5)
abstract class ContactDatabase : RoomDatabase() {

    abstract fun contactDao(): ContactDao
}