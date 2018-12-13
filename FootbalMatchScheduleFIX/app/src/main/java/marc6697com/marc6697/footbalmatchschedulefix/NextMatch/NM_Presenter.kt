package marc6697com.marc6697.footbalmatchschedulefix.NextMatch

import com.google.gson.Gson
import marc6697com.marc6697.footbalmatchschedulefix.API.ApiRepository
import marc6697com.marc6697.footbalmatchschedulefix.API.TheSportDBApi
import marc6697com.marc6697.footbalmatchschedulefix.LastMatch.LM_Model_LastMatchResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class NM_Presenter(private val NextMatchView: NM_View,
                   private val apiRepository: ApiRepository,
                   private val gson: Gson
) {


    fun getNextMatchList(league: String?) {
        //view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getNextMatch(league)),
                NM_Model_NextMatchResponse::class.java
            )

            uiThread {
                NextMatchView.showNextMatchList(data.events)
            }
        }
    }
}

