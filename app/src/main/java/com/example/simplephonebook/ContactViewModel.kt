package com.example.phonebook

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.simplephonebook.Contact.Contact
import com.example.simplephonebook.Contact.ContactDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactViewModel(application: Application) : AndroidViewModel(application) {
    // TODO: Implement the ViewModel
    val findAllData:LiveData<List<Contact>>
    private val repository:ContactRepository
    init {
        val contactDao = ContactDataBase.getInstance(application).contactDao()
        repository = ContactRepository(contactDao)
        findAllData = repository.findAllData
    }
    fun addContact(contact: Contact){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertContact(contact)
        }
    }
    fun updateContact(contact: Contact){
        viewModelScope.launch (Dispatchers.IO){
            repository.upDateContact(contact)
        }
    }
    fun deleteContact(contact: Contact){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteContact(contact)
        }
    }

}