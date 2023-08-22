package lnbti.charithgtp01.codetest1

import android.app.Application

class MyApp : Application() {

    companion object {

        //Room
        lateinit var userDatabase: ContactDatabase
    }

    override fun onCreate() {
        super.onCreate()

        //Room
        userDatabase=ContactDatabase.getInstance(applicationContext)
    }
}