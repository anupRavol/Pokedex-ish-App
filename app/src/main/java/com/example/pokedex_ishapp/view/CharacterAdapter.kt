package com.example.pokedex_ishapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex_ishapp.R
import com.example.pokedex_ishapp.model.Result
import com.example.pokedex_ishapp.util.getProgressDrawable
import com.example.pokedex_ishapp.util.loadImage
import kotlinx.android.synthetic.main.character_item.view.*

class CharacterAdapter (private val characterList: ArrayList<Result>, private val helper: CharacterAdapterHelper) :
    RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>(){

    fun updateCharactersList(newList : List<Result>){
        characterList.clear()
        characterList.addAll(newList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.character_item, parent, false)
        return CharacterViewHolder(view)
    }

    override fun getItemCount(): Int = characterList.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(characterList[position], helper)
    }

    class CharacterViewHolder(var view :View): RecyclerView.ViewHolder(view){
        fun bind(result: Result, helper: CharacterAdapterHelper){
            view.character_name.text = result.name
            view.character_image_view.loadImage(result.image, getProgressDrawable(view.context))
            view.character_item.setOnClickListener{
                helper.onCharacterSelected(view.character_image_view, result)
            }
        }
    }

    interface CharacterAdapterHelper{
        fun onCharacterSelected(view:View, result: Result)
    }
}