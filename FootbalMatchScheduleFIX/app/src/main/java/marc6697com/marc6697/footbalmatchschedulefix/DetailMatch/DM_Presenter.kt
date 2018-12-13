package marc6697com.marc6697.footbalmatchschedulefix.DetailMatch

import com.google.gson.Gson
import marc6697com.marc6697.footbalmatchschedulefix.API.ApiRepository
import marc6697com.marc6697.footbalmatchschedulefix.API.TheSportDBApi
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DM_Presenter(private val detailmatchview: DM_View_fix,
                   private val apiRepository: ApiRepository,
                   private val gson: Gson)
{
    fun getDetailMatch(events: String?) {
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getDetailMatch(events))
                    , DM_Model_MatchResponse::class.java)
            uiThread {
               detailmatchview.showMatchDetail(data.events)
            }
        }
    }
    fun getLogoTeam(teamLogo: String?, teamStatus: String?) {
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getLogoTeam(teamLogo))
                , DM_Model_LogoResponse::class.java)
            uiThread {
                if (teamStatus == "home")
                    detailmatchview.showHomeLogo(data.teams)
                else
                    detailmatchview.showAwayLogo(data.teams)
            }
        }
    }

}