package com.arlanov.androidapp.view.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import com.arlanov.androidapp.R
import com.arlanov.androidapp.databinding.ListFragmentBinding
import com.arlanov.androidapp.model.models.RepositoryModelWrapper
import com.arlanov.androidapp.view.adapters.Adapter
import com.arlanov.androidapp.view.adapters.EmptyAdapter
import com.arlanov.androidapp.vm.ListViewModel
import kotlinx.android.synthetic.main.recycler_repositories.view.*

class ListFragment : BaseFragment() {

    private lateinit var viewModel: ListViewModel
    private lateinit var repositoriesAdapter: Adapter
    private lateinit var view: ListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = ListFragmentBinding.inflate(inflater, container, false)
        return view.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        initView()
        initListeners()
        hangObserver()
    }

    private fun initView(){
        repositoriesAdapter = Adapter{
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.fragments_container, PageFragment.newInstance(it.name, it.watchers_count))
                .addToBackStack("")
                .commit()
        }

        val divider = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)

        with(view.recyclerRepositories.recyclerView) {
            setHasFixedSize(true)
            adapter = EmptyAdapter()
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(divider)
        }
    }
    private fun initListeners(){
        view.ibSearch.setOnClickListener {
            showCurrentContainersToolbar(true)
        }

        view.ibCloseSearchContainer.setOnClickListener {
            showCurrentContainersToolbar(false)
        }
    }

    private fun hangObserver(){
        viewModel.getLiveData().observe(this, Observer {
            view.progressBar.visibility = View.GONE
            if (it.size == 0)
                changeAdapter(true, it)
            else
                changeAdapter(false, it)
        })
    }

    private fun changeAdapter(isEmptyList: Boolean, list: List<RepositoryModelWrapper>){
        if (isEmptyList)
            view.recyclerRepositories.recyclerView.adapter = EmptyAdapter()
        else{
            view.recyclerRepositories.recyclerView.adapter = repositoriesAdapter
            repositoriesAdapter.update(list)
        }
    }

    private fun showCurrentContainersToolbar(isEnable: Boolean){
        if (isEnable){
            view.searchMainContainer.visibility = View.GONE
            view.searchContainer.visibility = View.VISIBLE
            with(view.sv){
                isIconified = false
                setOnCloseListener {
                    showCurrentContainersToolbar(false)
                    true
                }
                setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        view.progressBar.visibility = View.VISIBLE
                        viewModel.get(view.sv.query.toString())
                        showCurrentContainersToolbar(false)
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        return false
                    }

                })
            }
        }else{
            view.searchMainContainer.visibility = View.VISIBLE
            view.searchContainer.visibility = View.GONE
            view.sv.setQuery("",false)
        }
    }

}
