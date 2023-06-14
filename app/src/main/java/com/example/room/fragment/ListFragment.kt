package com.example.room.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.room.adapter.UserAdapter
import com.example.room.data.UserDatabase
import com.example.room.data.UserModel
import com.example.room.data.UserViewModel
import com.example.room.data.UserViewModelFactory
import com.example.room.databinding.FragmentListBinding
import com.google.android.material.snackbar.Snackbar

class ListFragment : Fragment() {
    private lateinit var viewModel: UserViewModel
    private lateinit var binding:FragmentListBinding
    private lateinit var userList: List<UserModel>
    private lateinit var adapter: UserAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding= FragmentListBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        val application= requireNotNull(this.activity).application
        val dataSource= UserDatabase.getDatabase(application)?.userDao

        val userViewModelFactory = dataSource?.let {        //DAO'yu ve applicationu viewModele gönderme işlemi
            UserViewModelFactory(it, application) }
        viewModel = userViewModelFactory?.let {
            ViewModelProvider(this, it).get(UserViewModel::class.java)
        }!!

        viewModel.userModelListLiveData.observe(viewLifecycleOwner){
            userList=it                                     //Adapter'a userlisti atamak ve adapter eşitleme işlemi
            adapter=UserAdapter(userList)
            binding.recyclerView.adapter=adapter
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tumUrunleriGoster()
        binding.floatingActionButton.setOnClickListener {
            val action=ListFragmentDirections.actionListFragmentToAddFragment()     //AddFragment'a yönlendirme
            NavHostFragment.findNavController(this).navigate(action)

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    fun tumUrunleriGoster() {       // UserList'in kontrolü ve gerekli işlemler
        viewModel.userModelListLiveData.observe(viewLifecycleOwner) { urunlerList ->
            userList = urunlerList
            binding.apply {
                if (userList.isEmpty()) {
                    Snackbar.make(requireView(), "Ürün bulunamadı", 1000).show()
                } else {
                    recyclerView.layoutManager = GridLayoutManager(context,2)
                    recyclerView.setHasFixedSize(true)
                }
            }
        }
    }


}