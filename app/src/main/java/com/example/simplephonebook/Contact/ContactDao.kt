package com.example.simplephonebook.Contact

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.simplephonebook.Contact.Contact

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(contact: Contact)

    @Update
    suspend fun updateContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Query("SELECT * FROM contact" )
    fun findContact(): LiveData<List<Contact>>
}