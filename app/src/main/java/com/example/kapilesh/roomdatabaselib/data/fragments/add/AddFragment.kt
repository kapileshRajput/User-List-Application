package com.example.kapilesh.roomdatabaselib.data.fragments.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.kapilesh.roomdatabaselib.R
import com.example.kapilesh.roomdatabaselib.data.User
import com.example.kapilesh.roomdatabaselib.data.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.btnAdd.setOnClickListener {
            insertDataToDatabase()
        }
        return view
    }

    private fun insertDataToDatabase() {
        val firstName = etFirstName.text.toString()
        val lastName = etLastName.text.toString()
        val age = etAge.text.toString()

        if (inputCheck(firstName, lastName, age)) {
            // create user object
            val user = User(0, firstName, lastName, Integer.parseInt(age.toString()))
            // Add data to database
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_SHORT).show()
            // Navigate Back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT)
                .show()
        }

    }

    private fun inputCheck(firstName: String, lastName: String, age: String): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && TextUtils.isEmpty(
            age
        ))
    }

}