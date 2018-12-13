package marc6697com.marc6697.footbalmatchschedulefix.NextMatch

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.google.gson.Gson
import kotlinx.android.synthetic.main.lastmatch_fragment_itemlist.view.*
import marc6697com.marc6697.footbalmatchschedulefix.API.ApiRepository

import marc6697com.marc6697.footbalmatchschedulefix.R
import org.jetbrains.anko.support.v4.onRefresh

class NM_Fragment: Fragment(), NM_View {
    private var NextMatch: MutableList<NM_Model_NextMatch> = mutableListOf()
    private var listener: OnListFragmentInteractionListener?=null
    private lateinit var adapter: NM_Adapter
    private lateinit var presenter: NM_Presenter
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun showNextMatchList(data: List<NM_Model_NextMatch>) {
        swipeRefresh.isRefreshing = false
        NextMatch.clear()
        NextMatch.addAll(data)
        adapter.notifyDataSetChanged()
        hideProgressBar()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.nextmatch_fragment_itemlist, container, false)

        val recycleView = view.findViewById<RecyclerView>(R.id.listnextmatch)
        recycleView.layoutManager = LinearLayoutManager(context)
        adapter = NM_Adapter(NextMatch,listener)
        recycleView.adapter = adapter

        swipeRefresh = view.swipeRefresh
        progressBar = view.progressBar

        swipeRefresh.onRefresh {
            presenter.getNextMatchList("4328")
        }
        showProgressBar()
        val apiRequest = ApiRepository()
        val gson = Gson()
        presenter = NM_Presenter(this, apiRequest, gson)
        presenter.getNextMatchList("4328")
        return view
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() )
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(item: NM_Model_NextMatch)
    }

    companion object {

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance() = NM_Fragment()
    }


}