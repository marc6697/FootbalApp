package marc6697com.marc6697.footbalmatchschedulefix.LastMatch

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import marc6697com.marc6697.footbalmatchschedulefix.R
import org.jetbrains.anko.find

class LM_Adapter(private val lastmatchitem:MutableList<LM_Model_LastMatch>,
                       private val listener: LM_Fragment.OnListFragmentInteractionListener?)
    : RecyclerView.Adapter<LM_Adapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder{
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.lastmatch_fragment_data, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int =lastmatchitem.size

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        holder.bindItem(lastmatchitem[position])
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val matchSchedule: TextView = view.find(R.id.MatchSchedule)
        val homeTeamname: TextView = view.find(R.id.home_team_name)
        val homeScore: TextView = view.find(R.id.home_score)
        val awayTeamname: TextView = view.find(R.id.away_team_name)
        val awayScore: TextView = view.find(R.id.away_score)

        fun bindItem(lmitem: LM_Model_LastMatch){
            matchSchedule.text = lmitem.DateMatch
            homeTeamname.text = lmitem.HomeTeamName
            homeScore.text = lmitem.HomeScore
            awayTeamname.text = lmitem.AwayTeamName
            awayScore.text = lmitem.AwayScore

            itemView.setOnClickListener {
                listener?.onListFragmentInteraction(lmitem)
            }
        }
    }



}