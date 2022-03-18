package com.example.simplephonebook.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.simplephonebook.Contact.Contact
import com.example.phonebook.ContactViewModel
import com.example.simplephonebook.R
import com.example.simplephonebook.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    private lateinit var contactViewModel:ContactViewModel
    lateinit var binding: FragmentFirstBinding

    // This property is only valid between onCreateView and
    // onDestroyView.
    //private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFirstBinding.inflate(inflater, container, false)
        val view = binding.root
        contactViewModel = ViewModelProvider(this).get(ContactViewModel::class.java)

        binding.buttonFirst.setOnClickListener {

            val name = binding.name.text.toString()
            val number = binding.number.text.toString()
            if(!(name.isEmpty() && number.isEmpty()))
            {
                val contact = Contact(0,name,number)
                contactViewModel.addContact(contact)
                findNavController().navigate(R.id.action_FirstFragment_to_listFragment)
            }
            else{
                Toast.makeText(requireContext(),"provide input",Toast.LENGTH_LONG).show()
            }

        }

        return view

    }


}