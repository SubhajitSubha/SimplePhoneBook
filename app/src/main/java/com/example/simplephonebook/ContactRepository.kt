package com.example.phonebook

import androidx.lifecycle.LiveData
import com.example.simplephonebook.Contact.Contact
import com.example.simplephonebook.Contact.ContactDao

class ContactRepository(private val contactDao: ContactDao) {
    val findAllData:LiveData<List<Contact>> = contactDao.findContact()

    suspend fun insertContact(contact: Contact){
        contactDao.insertContact(contact)
    }
    suspend fun upDateContact(contact:Contact){
        contactDao.updateContact(contact)
    }
    suspend fun deleteContact(contact: Contact){
        contactDao.deleteContact(contact)
    }
}