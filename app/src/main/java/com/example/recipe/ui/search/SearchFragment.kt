package com.example.recipe.ui.search

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe.R
import com.example.recipe.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val searchViewModel =
            ViewModelProvider(this).get(SearchViewModel::class.java)

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val url = "https://www.themealdb.com/api/json/v1/1/search.php?s="

        val recyclerView: RecyclerView = root.findViewById(R.id.rotd)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = searchViewModel.getAdapter(url)

        val searchView: SearchView = root.findViewById(R.id.search_bar)

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val imm = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
                imm?.hideSoftInputFromWindow(root.windowToken, 0)
                return false;
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                var searchUrl = url
                if (newText != null) {
                    searchUrl += newText
                    recyclerView.adapter = searchViewModel.getAdapter(searchUrl)
                } else {
                    recyclerView.adapter = searchViewModel.getAdapter(url)
                }
                return true;
            }
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}