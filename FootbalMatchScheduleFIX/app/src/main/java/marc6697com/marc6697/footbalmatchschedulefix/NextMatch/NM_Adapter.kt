package marc6697com.marc6697.footbalmatchschedulefix.NextMatch

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import marc6697com.marc6697.footbalmatchschedulefix.R
import org.jetbrains.anko.find


class NM_Adapter(private val nextmatchitem:MutableList<NM_Model_NextMatch>,
                 private val listener: NM_Fragment.OnListFragmentInteractionListener?)
    : RecyclerView.Adapter<NM_Adapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder{
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.nextmatch_fragment_data, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int =nextmatchitem.size

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        holder.bindItem(nextmatchitem[position])
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val matchSchedule: TextView = view.find(R.id.MatchSchedule)
        val homeTeamname: TextView = view.find(R.id.home_team_name)
        val homeScore: TextView = view.find(R.id.home_score)
        val awayTeamname: TextView = view.find(R.id.away_team_name)
        val awayScore: TextView = view.find(R.id.away_score)

        fun bindItem(nmitem: NM_Model_NextMatch){
            matchSchedule.text = nmitem.Datematch
            homeTeamname.text = nmitem.HomeTeamName
            homeScore.text = nmitem.HomeScore
            awayTeamname.text = nmitem.AwayTeamName
            awayScore.text = nmitem.AwayScore

            itemView.setOnClickListener {
                listener?.onListFragmentInteraction(nmitem)
            }
        }
    }



}