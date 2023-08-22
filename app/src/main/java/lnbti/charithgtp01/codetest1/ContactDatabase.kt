package lnbti.charithgtp01.codetest1

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import lnbti.charithgtp01.codetest1.model.Contact

@Database(entities = [Contact::class], version = 2)
abstract class ContactDatabase : RoomDatabase() {

    abstract fun contactDao(): ContactDao

    companion object {
        @Volatile
        private var INSTANCE: ContactDatabase? = null

        fun getInstance(context: Context): ContactDatabase {
            return INSTANCE
                ?: synchronized(this) {
                    INSTANCE ?: Room.databaseBuilder(
                        context.applicationContext,
                        ContactDatabase::class.java, "contact_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                        .also { INSTANCE = it }
                }

        }
    }
}