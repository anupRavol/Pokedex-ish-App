package com.example.pokedex_ishapp.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokedex_ishapp.R
import com.example.pokedex_ishapp.model.Result
import com.example.pokedex_ishapp.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment(), CharacterAdapter.CharacterAdapterHelper {
    override fun onCharacterSelected(view: View, result: Result) {
        val action = ListFragmentDirections.actionListToDetail(result, result.name)
        val extras = FragmentNavigatorExtras(
            view to "imageView"
        )
        findNavController().navigate(action, extras)

    }

    private lateinit var listViewModel: ListViewModel
    private val characterAdapter = CharacterAdapter(arrayListOf(), this)

    private val listDataObserver = Observer<List<Result>> { list ->
        list?.let {
            characterList.visibility = View.VISIBLE
            characterAdapter.updateCharactersList(list)
        }
    }

    private val loadingObserver = Observer<Boolean> { isLoading: Boolean ->
        progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        if (isLoading) {
            error_text.visibility = View.GONE
            characterList.visibility = View.VISIBLE
        }
    }
    private val errorObserver = Observer<Boolean> { isError: Boolean ->
        error_text.visibility = if (isError) View.VISIBLE else View.GONE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listViewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)

        listViewModel.characters.observe(this, listDataObserver)

        listViewModel.loading.observe(this, loadingObserver)
        listViewModel.loadError.observe(this, errorObserver)

        listViewModel.refresh()

        characterList.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = characterAdapter
        }
        refreesh_layout.setOnRefreshListener {
            characterList.visibility = View.GONE
            error_text.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
            listViewModel.refresh()
            refreesh_layout.isRefreshing = false
        }
    }
}