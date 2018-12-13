package marc6697com.marc6697.footbalmatchschedulefix

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_match_detail.*
import marc6697com.marc6697.footbalmatchschedulefix.API.ApiRepository
import marc6697com.marc6697.footbalmatchschedulefix.DB.database
import marc6697com.marc6697.footbalmatchschedulefix.DetailMatch.DM_Model_Logo
import marc6697com.marc6697.footbalmatchschedulefix.DetailMatch.DM_Model_Match
import marc6697com.marc6697.footbalmatchschedulefix.DetailMatch.DM_Presenter
import marc6697com.marc6697.footbalmatchschedulefix.DetailMatch.DM_View_fix
import marc6697com.marc6697.footbalmatchschedulefix.Favorite.Favorite
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.toast


class MatchDetail : AppCompatActivity(),DM_View_fix {

    private lateinit var teamBadge: ImageView

    override fun showHomeLogo(data: List<DM_Model_Logo>) {
        Picasso.get().load(data[0].teamlogo).into(HomeLogo)
        hideProgressBar(HomeProgress)
        HomeLogo.visibility = View.VISIBLE
    }

    override fun showAwayLogo(data: List<DM_Model_Logo>) {
        Picasso.get().load(data[0].teamlogo).into(AwayLogo)
        hideProgressBar(AwayProgress)
        AwayLogo.visibility = View.VISIBLE
    }
    private val detail: MutableList<DM_Model_Match> = mutableListOf()

    private lateinit var presenter: DM_Presenter
    private lateinit var Progress: ProgressBar
    private lateinit var HomeProgress: ProgressBar
    private lateinit var AwayProgress: ProgressBar

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false


    private var IdMatch: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)

        IdMatch = intent.getStringExtra("Id")
        val HomeTeam = intent.getStringExtra("HomeTeam")
        val AwayTeam = intent.getStringExtra("AwayTeam")

checkFavorite()
        Progress = ProgressBar
        HomeProgress = HomeProgress1
        AwayProgress = AwayProgress1

        HomeName.text = HomeTeam
        AwayName.text = AwayTeam

        showProgressBar(Progress)
        showProgressBar(HomeProgress)
        showProgressBar(AwayProgress)
        val apiRequest = ApiRepository()
        val gson = Gson()
        presenter = DM_Presenter(this, apiRequest, gson)
        presenter.run {
            getDetailMatch(IdMatch)
           // getTsetLogo(HomeTeam)
         //   getTsetLogo(AwayTeam)

            getLogoTeam(HomeTeam, "home")
            getLogoTeam(AwayTeam, "away")
        }
    }

  private fun favicon(favorite: Boolean){
        if(favorite){
            menuItem?.findItem(R.id.add_to_favorite)?.icon=getDrawable(R.drawable.ic_star_full)
        }else{
            menuItem?.findItem(R.id.add_to_favorite)?.icon=getDrawable(R.drawable.ic_star_empty)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu,menu)
        menuItem=menu
        favicon(isFavorite)
        return true
    }
    private var DataAvailable = false

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       when(item.itemId){
           android.R.id.home->{
               finish()
           }
           R.id.add_to_favorite->{
               if(DataAvailable){
                   if(isFavorite)
                       removeFromFavorite()
                   else addToFavorite()
                   favicon(isFavorite)
               }else{
                   toast("PleaseWait")
               }
           }
       }
        return super.onOptionsItemSelected(item)
    }

    private fun addToFavorite(){
        try{
            database.use{
                insert(Favorite.TABLE_FAVORITE,
                    Favorite.ID_MATCH to IdMatch.toInt(),
                    Favorite.DATE_MATCH to detail[0].DateMatch.toString(),
                    Favorite.HOME_TEAM_NAME to detail[0].HomeTeamName.toString(),
                    Favorite.AWAY_TEAM_NAME to detail[0].AwayTeamName.toString(),
                    Favorite.HOME_SCORE to detail[0].HomeScore.toString(),
                    Favorite.AWAY_SCORE to detail[0].AwayScore.toString())
            }
            snackbar(Layout,"Successfully Added").show()
            isFavorite=true
        }catch (e:SQLiteConstraintException){
            snackbar(Layout,e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use{
                delete(Favorite.TABLE_FAVORITE
                    ,"(ID_MATCH={Id})"
                    ,"Id" to IdMatch)
            }
            snackbar(Layout,"Succesfully Removed").show()
            isFavorite=false
        }catch (e:SQLiteConstraintException){
            snackbar(Layout,e.localizedMessage)
        }
    }

    private fun checkFavorite(){
        database.use {
            val result=select(Favorite.TABLE_FAVORITE)
                .whereArgs("(ID_MATCH={Id})",
                    "Id" to IdMatch)
            val favorite=result.parseList(classParser<Favorite>())
            if(!favorite.isEmpty())isFavorite=true
        }
    }
    override fun showMatchDetail(data: List<DM_Model_Match>) {
        detail.clear()
        detail.addAll(data)
        var HomeScore1 = data[0].HomeScore.toString()
        var AwayScore1 = data[0].AwayScore.toString()
        val matchSchedule = data[0].DateMatch.toString()




        if (data[0].HomeScore.toString() == "null") HomeScore1 = ""
        val HomeGoal = getListData(data[0].HomeGoalDetail)
        val HomeGoalKeeper = getListData(data[0].HomeGoalKeeper)
        val HomeDefense = getListData(data[0].HomeDefense)
        val HomeMidfield = getListData(data[0].HomeMidfield)
        val HomeForward = getListData(data[0].HomeForward)
        val HomeSubstitutes = getListData(data[0].HomeSubstitutes)

        setText(HomeGoal, TextHomeGoal)
        setText(HomeGoalKeeper, textHomeGK)
        setText(HomeDefense, textHomeDefense)
        setText(HomeMidfield, textHomeMidfield)
        setText(HomeForward, textHomeForward)
        setText(HomeSubstitutes, textHomeSubstitutes)

        if (data[0].AwayScore.toString() == "null") AwayScore1 = ""
        val AwayGoal = getListData(data[0].AwayGoalDetail)
        val AwayGoalKeeper = getListData(data[0].AwayGoalKeeper)
        val AwayDefense = getListData(data[0].AwayDefense)
        val AwayMidfield = getListData(data[0].AwayMidfield)
        val AwayForward = getListData(data[0].AwayForward)
        val AwaySubstitutes = getListData(data[0].AwaySubstitutes)

        setText(AwayGoal, TextAwayGoal)
        setText(AwayGoalKeeper, textAwayGK)
        setText(AwayDefense, textAwayDefense)
        setText(AwayMidfield, textAwayMidfield)
        setText(AwayForward, textAwayForward)
        setText(AwaySubstitutes, textAwaySubstitutes)


        textHomeScore.text = HomeScore1
        textAwayScore.text = AwayScore1
        DateMatch.text = matchSchedule

        DataAvailable=true
        hideProgressBar(Progress)
        detailTable.visibility = View.VISIBLE
    }

    override fun showProgressBar(progressBar: ProgressBar) {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar(progressBar: ProgressBar) {
        progressBar.visibility = View.GONE
    }


    private fun getListData(details: String?): List<String> {
        return details.toString().split(";")
    }

    private fun getString(text: String, value: String): String {
        return if (value != "null")
            getString(R.string.text, text, value)
        else
            getString(R.string.text, "", "")
    }

    private fun setText(list: List<String>, textView: TextView) {
        list.forEach { value ->
            textView.text = getString(textView.text.toString(), value.trim())
        }
    }
}
