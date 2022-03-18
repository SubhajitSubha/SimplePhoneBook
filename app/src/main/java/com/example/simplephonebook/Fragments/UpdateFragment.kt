package com.example.simplephonebook.Fragments

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.phonebook.ContactViewModel
import com.example.simplephonebook.Contact.Contact
import com.example.simplephonebook.R
import com.example.simplephonebook.databinding.FragmentUpdateBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class UpdateFragment : Fragment() {

    private var _binding: FragmentUpdateBinding? = null
    private lateinit var contactViewModel: ContactViewModel
    private val args by navArgs<UpdateFragmentArgs>()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        contactViewModel = ViewModelProvider(this).get(ContactViewModel::class.java)

        binding.updateName.text = args.contactList.name.toEditable()
        binding.updateNumber.text = args.contactList.number.toEditable()

        binding.buttonUpdate.setOnClickListener{

            val name = binding.updateName.text.toString()
            val number = binding.updateNumber.text.toString()
            if(!(name.isEmpty() && number.isEmpty()))
            {
                val contact = Contact(args.contactList.id,name,number)
                contactViewModel.updateContact(contact)
                findNavController().navigate(R.id.action_updateFragment_to_listFragment)
                Toast.makeText(requireContext(),"Updated Successfully", Toast.LENGTH_LONG).show()

            }
            else{
                Toast.makeText(requireContext(),"provide input", Toast.LENGTH_LONG).show()
            }
        }
        return binding.root

    }
    fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}