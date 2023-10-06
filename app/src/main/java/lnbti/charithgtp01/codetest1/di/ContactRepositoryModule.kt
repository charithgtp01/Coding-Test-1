package lnbti.charithgtp01.codetest1.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import lnbti.charithgtp01.codetest1.db.ContactDao
import lnbti.charithgtp01.codetest1.repositories.ContactRepository
import lnbti.charithgtp01.codetest1.repositories.ContactRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
object ContactRepositoryModule {

    @Provides
    fun provideContactRepository(contactDao: ContactDao): ContactRepository {
        return ContactRepositoryImpl(contactDao) // Replace with your actual implementation
    }
}
