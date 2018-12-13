package marc6697com.marc6697.footbalmatchschedulefix.LastMatch

import com.google.gson.annotations.SerializedName


data class LM_Model_LastMatch(
    @SerializedName("idEvent")
    var IdEvent: String? = "",

    @SerializedName("dateEvent")
    var DateMatch: String? = "",

    @SerializedName("strHomeTeam")
    var HomeTeamName: String? = "",

    @SerializedName("intHomeScore")
    var HomeScore: String? = "",

    @SerializedName("strAwayTeam")
    var AwayTeamName: String? = "",

    @SerializedName("intAwayScore")
    var AwayScore: String? = "")
