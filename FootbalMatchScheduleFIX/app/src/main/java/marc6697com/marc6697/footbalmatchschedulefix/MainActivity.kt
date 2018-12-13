package marc6697com.marc6697.footbalmatchschedulefix

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import marc6697com.marc6697.footbalmatchschedulefix.Favorite.Favorite
import marc6697com.marc6697.footbalmatchschedulefix.Favorite.Favorite_Fragment
import marc6697com.marc6697.footbalmatchschedulefix.LastMatch.LM_Fragment
import marc6697com.marc6697.footbalmatchschedulefix.LastMatch.LM_Model_LastMatch
import marc6697com.marc6697.footbalmatchschedulefix.NextMatch.NM_Fragment
import marc6697com.marc6697.footbalmatchschedulefix.NextMatch.NM_Model_NextMatch
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity(), LM_Fragment.OnListFragmentInteractionListener ,NM_Fragment.OnListFragmentInteractionListener,
Favorite_Fragment.OnListFragmentInteractionListener{
    override fun onFavoriteListFragmentInteraction(item: Favorite) {
        startActivity<MatchDetail>("Id" to item.IdMatch.toString()
            , "HomeTeam" to item.HomeTeamName
            , "AwayTeam" to item.AwayTeamName)
    }

    override fun onListFragmentInteraction(item: NM_Model_NextMatch) {
        startActivity<MatchDetail>("Id" to item.IdEvent
            , "HomeTeam" to item.HomeTeamName
            , "AwayTeam" to item.AwayTeamName)
    }

    override fun onListFragmentInteraction(item: LM_Model_LastMatch) {
        startActivity<MatchDetail>("Id" to item.IdEvent
           , "HomeTeam" to item.HomeTeamName
         , "AwayTeam" to item.AwayTeamName)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {

            R.id.navigation_lastmatch-> {
                val fragment=LM_Fragment.newInstance()
                openFragment(fragment)
                title="Last 15 Matches"
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_home -> {
                val fragment=Favorite_Fragment.newInstance()
                openFragment(fragment)
                title="Favorites"
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_nextmatch -> {
                val fragment= NM_Fragment.newInstance()
                openFragment(fragment)
                title="Next 15 Matches"
                return@OnNavigationItemSelectedListener true
            }

        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)


        title="Last 15 Matches"
        openFragment(LM_Fragment.newInstance())
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_space, fragment)
            commit()
        }
    }
}
