package marc6697com.marc6697.footbalmatchschedulefix.LastMatch

import com.google.gson.Gson
import marc6697com.marc6697.footbalmatchschedulefix.API.ApiRepository
import marc6697com.marc6697.footbalmatchschedulefix.API.TheSportDBApi
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class LM_Presenter(private val LastMatchView: LM_View,
                   private val apiRepository: ApiRepository,
                   private val gson: Gson
) {


    fun getLastMatchList(league: String?) {
        //view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getLastMatch(league)),
                LM_Model_LastMatchResponse::class.java
            )

            uiThread {
                LastMatchView.showLastMatchList(data.events)
            }
        }
    }
}

