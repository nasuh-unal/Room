package com.example.room.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.room.R
import com.example.room.data.UserDatabase
import com.example.room.data.UserModel
import com.example.room.data.UserViewModel
import com.example.room.data.UserViewModelFactory
import com.example.room.databinding.FragmentUpdateBinding

class UpdateFragment : Fragment() {
    private lateinit var viewModel: UserViewModel
    private lateinit var binding:FragmentUpdateBinding
    private lateinit var userModel: UserModel
    private lateinit var urunDB: UserDatabase


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding= FragmentUpdateBinding.inflate(inflater)
        binding.lifecycleOwner=viewLifecycleOwner

        val bundle: UpdateFragmentArgs by navArgs()     // Tıklanılan modeli update fragment'a çekmek
        userModel = bundle.user

        val application= requireNotNull(this.activity).application
        val dataSource= UserDatabase.getDatabase(application)?.userDao

        // Application ve DAO'yu ViewModela vermek

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
            etxtAd.setText(userModel.name)          //UpdateFragment'a gelen veriyi edittextlere yazdırmak
            etxtYas.setText(userModel.age.toString())

            btnGuncelle.setOnClickListener {
                val userNameInput=etxtAd.text.toString()        //etxtdeki verileri update edip tekrar database'e göndermek
                val userAgeInput=etxtYas.text.toString().toInt()

                userModel.name=userNameInput
                userModel.age=userAgeInput

                viewModel.updateUser(userModel)

                findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            }

            btnSil.setOnClickListener {
                viewModel.deleteUser(userModel)         // GÖrüntülenen veriyi silmek
                findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            }
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        urunDB = UserDatabase.getDatabase(requireContext())!!
    }

}