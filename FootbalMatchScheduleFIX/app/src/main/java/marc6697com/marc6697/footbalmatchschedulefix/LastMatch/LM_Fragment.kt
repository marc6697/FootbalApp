package marc6697com.marc6697.footbalmatchschedulefix.LastMatch

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

class LM_Fragment:Fragment(),LM_View{
    private var LastMatch: MutableList<LM_Model_LastMatch> = mutableListOf()
    private var listener: OnListFragmentInteractionListener?=null
    private lateinit var adapter: LM_Adapter
    private lateinit var presenter: LM_Presenter
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun showLastMatchList(data: List<LM_Model_LastMatch>) {

      swipeRefresh.isRefreshing = false
        LastMatch.clear()
        LastMatch.addAll(data)
        adapter.notifyDataSetChanged()
        hideProgressBar()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.lastmatch_fragment_itemlist, container, false)

        val recycleView = view.findViewById<RecyclerView>(R.id.listlastmatch)
        recycleView.layoutManager = LinearLayoutManager(context)
        adapter = LM_Adapter(LastMatch,listener)
        recycleView.adapter = adapter

        swipeRefresh = view.swipeRefresh
        progressBar = view.progressBar

        swipeRefresh.onRefresh {
            presenter.getLastMatchList("4328")
        }
        showProgressBar()
        val apiRequest = ApiRepository()
        val gson = Gson()
        presenter = LM_Presenter(this, apiRequest, gson)
        presenter.getLastMatchList("4328")
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
        fun onListFragmentInteraction(item: LM_Model_LastMatch)
    }

    companion object {

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance() = LM_Fragment()
    }


}