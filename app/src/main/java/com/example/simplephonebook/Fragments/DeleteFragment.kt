package com.example.simplephonebook.Fragments

import android.app.AlertDialog
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
import com.example.simplephonebook.databinding.FragmentDeleteBinding
import com.example.simplephonebook.databinding.FragmentUpdateBinding


class DeleteFragment : Fragment() {
    private var _binding: FragmentDeleteBinding? = null
    private lateinit var contactViewModel: ContactViewModel
    private val args by navArgs<DeleteFragmentArgs>()
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDeleteBinding.inflate(inflater, container, false)
        binding.deleteName.text = args.contactList.name.toEditable()
        binding.deleteNumber.text = args.contactList.number.toEditable()
        contactViewModel = ViewModelProvider(this).get(ContactViewModel::class.java)


        binding.buttonDelete.setOnClickListener{
            val name = binding.deleteName.text.toString()
            val number = binding.deleteNumber.text.toString()
            if(!(name.isEmpty() && number.isEmpty()))
            {
                val builder = AlertDialog.Builder(requireContext())
                builder.setPositiveButton("yes") { _, _ ->
                    val contact = Contact(args.contactList.id, name, number)
                    contactViewModel.deleteContact(contact)
                    findNavController().navigate(R.id.action_deleteFragment_to_listFragment)
                    Toast.makeText(requireContext(), "Deleted Successfully", Toast.LENGTH_LONG)
                        .show()
                }
                builder.setNegativeButton("No"){_,_ -> }
                builder.setTitle("Delete ${args.contactList.name}?")
                builder.setMessage("Are you sure,you want to delete ${args.contactList.name}?")
                builder.create().show()
            }
            else{
                Toast.makeText(requireContext(),"provide input", Toast.LENGTH_LONG).show()
            }
        }
        return binding.root

    }
    fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)


}