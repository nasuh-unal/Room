package com.example.room.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.room.data.UserModel
import com.example.room.databinding.ItemCardBinding
import com.example.room.fragment.ListFragmentDirections


class UserAdapter (private var urunlerList: List<UserModel?>) : RecyclerView.Adapter<UserAdapter.CardHolder>() {

    class CardHolder(val itemCardBinding: ItemCardBinding) : RecyclerView.ViewHolder(itemCardBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemCardBinding = ItemCardBinding.inflate(layoutInflater, parent, false)
        return CardHolder(itemCardBinding)
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        val user = urunlerList[position]

        holder.itemCardBinding.apply {
            user?.let{
                textViewUrunAd.text = user.name
                textViewUrunFiyat.text = user.age.toString()
            }

            itemCard.setOnClickListener {button->
                user?.let {userModel->
                    val listToUpdate = ListFragmentDirections.actionListFragmentToUpdateFragment(userModel)
                    Navigation.findNavController(button).navigate(listToUpdate)
                }
            }
        }
    }

    override fun getItemCount() = urunlerList.size


}