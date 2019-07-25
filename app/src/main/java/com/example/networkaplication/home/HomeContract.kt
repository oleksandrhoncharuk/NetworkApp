package com.example.networkaplication.home

import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData

import androidx.recyclerview.widget.RecyclerView

import com.example.networkaplication.MainActivity
import com.example.networkaplication.details.DetailsViewFragment
import com.example.networkaplication.home.adapter.HomeAdapter
import com.example.networkaplication.home.adapter.ItemData
import com.example.networkaplication.home.search.story.SearchItem
import com.example.networkaplication.home.search.story.SearchStoryAdapter
import com.example.networkaplication.models.search.SearchObject
import com.example.networkaplication.persistance.model.MovieQuery

import java.util.ArrayList

interface HomeContract {

    interface HomeView {

        fun getHomeViewActivity(): MainActivity

        fun getSearchRecycle(): RecyclerView

        fun setRecycleViewAdapter(adapter: HomeAdapter)
    }

    interface HomeViewModel: CustomOnEditorActionListener, CustomOnFocusChangeListener, SearchStoryAdapter.OnSearchItemClickedListener, HomeAdapter.OnFilmClickedListener{

        val searchText: ObservableField<String>

        override fun onEditorAction(v: TextView?, actionId: Int)

        fun setItemToDataAdapter()

        fun refreshSearchAdapter()

        fun createDetailsView(itemData: ItemData): DetailsViewFragment

        fun clearSearchAdapter()

        fun hideKeyboard(view: View)

        fun search(searchRequest: String)

        fun addUniqueMovie(title: String)

        fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int)

        override fun onFocusChanged(view: View, hasFocus: Boolean)

        override fun onClickedSearchItem(viewId: Int, itemData: SearchItem)

        fun isSearchItemEmpty(): Boolean
    }

    interface HomeRepository {
        fun startSearch(searchRequest: String)

        fun findAllFromQuery(search: String): List<MovieQuery>

        fun findByTitle(title: String): MovieQuery?

        fun insertIntoQuery(query: MovieQuery)
    }
}
