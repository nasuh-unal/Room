package com.example.room.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.room.R
import com.example.room.data.UserDatabase
import com.example.room.data.UserModel
import com.example.room.data.UserViewModel
import com.example.room.data.UserViewModelFactory
import com.example.room.databinding.FragmentAddBinding

class AddFragment : Fragment() {
    private lateinit var binding:FragmentAddBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding= FragmentAddBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner

        val application= requireNotNull(this.activity).application
        val dataSource=UserDatabase.getDatabase(application)?.userDao

        val userViewModelFactory = dataSource?.let {
            UserViewModelFactory(it, application) }
        viewModel = userViewModelFactory?.let {
            ViewModelProvider(this, it).get(UserViewModel::class.java)
        }!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnAdd.setOnClickListener {
                val userNameInput=etxtAd.text.toString()
                val userAgeInput=etxtYas.text.toString().toInt()

                viewModel.addUser(
                    user = UserModel(
                        name = userNameInput,
                        age = userAgeInput
                    )
                )
                findNavController().navigate(R.id.action_addFragment_to_listFragment)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


}