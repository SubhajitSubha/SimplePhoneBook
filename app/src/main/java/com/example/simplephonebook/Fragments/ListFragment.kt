package com.example.simplephonebook.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.simplephonebook.Contact.Contact
import com.example.phonebook.ContactViewModel
import com.example.simplephonebook.Contact.ContactList
import com.example.simplephonebook.MyItemRecyclerViewAdapter
import com.example.simplephonebook.R

/**
 * A fragment representing a list of Items.
 */
class ListFragment : Fragment() {


    private lateinit var contactViewModel: ContactViewModel
    private lateinit var recyclerView: RecyclerView
    private var columnCount = 3
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list_list, container, false)

        contactViewModel = ViewModelProvider(this).get(ContactViewModel::class.java)
        // Set the adapter
        val adapter= MyItemRecyclerViewAdapter(ContactList.contactList)
        recyclerView = view.findViewById(R.id.recycler_view)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        contactViewModel.findAllData.observe(viewLifecycleOwner, Observer { contact->
            adapter.setData(contact as ArrayList<Contact>)
        })

        return view
    }



    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            ListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}