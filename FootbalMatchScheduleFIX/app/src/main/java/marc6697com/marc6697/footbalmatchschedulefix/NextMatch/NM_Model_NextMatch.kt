package marc6697com.marc6697.footbalmatchschedulefix.NextMatch

import com.google.gson.annotations.SerializedName


data class NM_Model_NextMatch(
    @SerializedName("idEvent")
    var IdEvent: String? = "",

    @SerializedName("dateEvent")
    var Datematch: String? = "",

    @SerializedName("strHomeTeam")
    var HomeTeamName: String? = "",

    @SerializedName("intHomeScore")
    var HomeScore: String? = "",

    @SerializedName("strAwayTeam")
    var AwayTeamName: String? = "",

    @SerializedName("intAwayScore")
    var AwayScore: String? = "")
