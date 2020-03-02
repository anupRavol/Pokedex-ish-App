package com.example.pokedex_ishapp.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.transition.Transition
import androidx.transition.TransitionInflater

import com.example.pokedex_ishapp.R
import com.example.pokedex_ishapp.model.Result
import com.example.pokedex_ishapp.util.getProgressDrawable
import com.example.pokedex_ishapp.util.loadImage
import kotlinx.android.synthetic.main.character_item.*
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.character_image_view
import kotlinx.android.synthetic.main.fragment_detail.character_name

class DetailFragment : Fragment() {

    var result :Result? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            result = DetailFragmentArgs.fromBundle(it).result
        }
        context?.let {
            character_image_view.loadImage(result?.image, getProgressDrawable(it))
        }

        result?.let {
            character_name.text = it.name
            character_gender.text = it.gender
            character_species.text = it.species
            character_status.text = it.status
            character_origin.text = it.origin?.name
            character_location.text = it.location?.name
        }
    }
}
