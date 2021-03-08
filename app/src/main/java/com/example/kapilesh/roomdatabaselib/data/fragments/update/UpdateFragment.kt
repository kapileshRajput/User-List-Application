package com.example.kapilesh.roomdatabaselib.data.fragments.update

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.kapilesh.roomdatabaselib.R
import com.example.kapilesh.roomdatabaselib.model.User
import com.example.kapilesh.roomdatabaselib.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*


class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.etUpdateFirstName.setText(args.currentUser.firstName)
        view.etUpdateLastName.setText(args.currentUser.lastName)
        view.etUpdateAge.setText(args.currentUser.age.toString())

        view.btnUpdate.setOnClickListener {
            updateItem()
        }
        return view
    }

    private fun updateItem() {
        val firstName = etUpdateFirstName.text.toString()
        val lastName = etUpdateLastName.text.toString()
        val age = Integer.parseInt(etUpdateAge.text.toString())

        if (inputCheck(firstName, lastName, etUpdateAge.text)) {
            //create user object
            val updatedUser = User(args.currentUser._id, firstName, lastName, age)

            // update current user
            mUserViewModel.updateUser(updatedUser)
            Toast.makeText(requireContext(), "Updated Successfully!", Toast.LENGTH_SHORT).show()
            // navigate bak
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT)
                .show()
        }

    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }
}