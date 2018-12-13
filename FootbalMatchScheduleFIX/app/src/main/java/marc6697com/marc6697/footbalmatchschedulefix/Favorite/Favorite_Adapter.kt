package marc6697com.marc6697.footbalmatchschedulefix.Favorite

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import marc6697com.marc6697.footbalmatchschedulefix.R
import org.jetbrains.anko.find

class Favorite_Adapter(
    private val items:MutableList<Favorite>,
    private val Listener:Favorite_Fragment.OnListFragmentInteractionListener?
):RecyclerView.Adapter<Favorite_Adapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.favorite_fragment_data, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.bindItem(items[position])
    }


    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        val fmatch: TextView = view.find(R.id.MatchSchedule)
        val fhomeTeamname: TextView = view.find(R.id.home_team_name)
        val fhomeScore: TextView = view.find(R.id.home_score)
        val fawayTeamname: TextView = view.find(R.id.away_team_name)
        val fawayScore: TextView = view.find(R.id.away_score)

        fun bindItem(items: Favorite) {
            text(fmatch, items.DateMatch)
            text(fhomeTeamname, items.HomeTeamName)
            text(fhomeScore, items.HomeScore)
            text(fawayTeamname, items.AwayTeamName)
            text(fawayScore, items.AwayScore)

            itemView.setOnClickListener {
                Listener?.onFavoriteListFragmentInteraction(items)
            }
        }

        private fun text(textView: TextView, value: String) {
            if (value == "null")
                textView.visibility = View.GONE
            else
                textView.text = value
        }


    }
}