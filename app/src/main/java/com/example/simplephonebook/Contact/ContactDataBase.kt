package com.example.simplephonebook.Contact

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Contact::class],version = 1)
abstract class ContactDataBase:RoomDatabase() {
    abstract fun contactDao(): ContactDao

    companion object{
        @Volatile
        private var INSTANCE: ContactDataBase? = null
        fun getInstance(context: Context): ContactDataBase {
            val instance = INSTANCE
            if (instance!=null) {
                return instance
            }
            synchronized(this){

                val instance= Room.databaseBuilder(context.applicationContext,
                    ContactDataBase::class.java,
                    "contactDB").build()
                INSTANCE = instance
                return instance
            }

        }
    }
}

