package com.example.simplephonebook

import android.annotation.SuppressLint
import android.text.Editable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.simplephonebook.Contact.Contact
import com.example.simplephonebook.Fragments.ListFragment
import com.example.simplephonebook.Fragments.ListFragmentDirections
import com.example.simplephonebook.databinding.FragmentListBinding
import com.example.simplephonebook.placeholder.PlaceholderContent.PlaceholderItem

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyItemRecyclerViewAdapter(var contactList: ArrayList<Contact>) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {
    //private var contactList = emptyList<Contact>()

    inner class ViewHolder(val itemBinding: FragmentListBinding):RecyclerView.ViewHolder(itemBinding.root){

            fun bindItem(contact: Contact){
                itemBinding.itemId.text = contact.id.toString()
                itemBinding.itemName.text = contact.name
                itemBinding.itemNumber.text = contact.number
            }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(FragmentListBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

     override fun onBindViewHolder(holder: ViewHolder,position: Int, ) {

        val item = contactList[position]
        holder.bindItem(item)
        holder.itemBinding.rowLayout.setOnClickListener{
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(item)
            holder.itemView.findNavController().navigate(action)
        }
        holder.itemBinding.deleteContact.setOnClickListener{
            val action = ListFragmentDirections.actionListFragmentToDeleteFragment(item)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int = contactList.size

//    inner class ViewHolder(binding: FragmentListBinding) : RecyclerView.ViewHolder(binding.root) {
//        val idView:TextView= binding.itemId
//        val nameView:TextView = binding.itemName
//        val numberView:TextView = binding.itemNumber
//
//        override fun toString(): String {
//            return super.toString() + " '" + numberView.text + "'"
//        }
//    }
    @SuppressLint("NotifyDataSetChanged")
    fun setData(contact:ArrayList<Contact>){
        this.contactList = contact
        notifyDataSetChanged()
    }


}