package marc6697com.marc6697.footbalmatchschedulefix.Favorite

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
import kotlinx.android.synthetic.main.favorite_fragment_list.view.*
import marc6697com.marc6697.footbalmatchschedulefix.DB.database
import marc6697com.marc6697.footbalmatchschedulefix.LastMatch.LM_Model_LastMatch
import marc6697com.marc6697.footbalmatchschedulefix.LastMatch.LM_View
import marc6697com.marc6697.footbalmatchschedulefix.NextMatch.NM_Model_NextMatch
import marc6697com.marc6697.footbalmatchschedulefix.NextMatch.NM_View
import marc6697com.marc6697.footbalmatchschedulefix.R
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.onRefresh
import java.lang.RuntimeException

class Favorite_Fragment:Fragment(),LM_View,NM_View{

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun showLastMatchList(data: List<LM_Model_LastMatch>) {}
    override fun showNextMatchList(data: List<NM_Model_NextMatch>) {}

    private var event: MutableList<Favorite> = mutableListOf()
    private var flistener: Favorite_Fragment.OnListFragmentInteractionListener? = null
    private lateinit var fadapter: Favorite_Adapter
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar

    private fun showFav(){
        context?.database?.use{
            swipeRefresh.isRefreshing=false
            val result=select(Favorite.TABLE_FAVORITE)
            val favorite=result.parseList(classParser<Favorite>())
            event.clear()
            event.addAll(favorite)
            fadapter.notifyDataSetChanged()
            hideProgressBar()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=inflater.inflate(R.layout.favorite_fragment_list,container,false)

        val recycleView=view.findViewById<RecyclerView>(R.id.favorite)
        recycleView.layoutManager=LinearLayoutManager(context)
        fadapter= Favorite_Adapter(event,flistener)
        recycleView.adapter=fadapter

        swipeRefresh=view.swipeRefresh
        progressBar=view.progressBar

        swipeRefresh=view.swipeRefresh
        progressBar=view.progressBar

        swipeRefresh.onRefresh { showFav() }
        showProgressBar()
        showFav()
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is OnListFragmentInteractionListener){
            flistener=context
        }else{
            throw RuntimeException(context.toString())
        }
    }
    override fun onDetach() {
        super.onDetach()
        flistener=null
    }

    interface OnListFragmentInteractionListener{
        fun onFavoriteListFragmentInteraction(item:Favorite)
    }

    companion object {
        @JvmStatic
    fun newInstance()=Favorite_Fragment()
    }
}