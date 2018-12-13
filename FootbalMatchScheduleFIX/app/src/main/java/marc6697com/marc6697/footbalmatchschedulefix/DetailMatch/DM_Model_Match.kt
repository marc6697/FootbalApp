package marc6697com.marc6697.footbalmatchschedulefix.DetailMatch

import com.google.gson.annotations.SerializedName
data class DM_Model_Match(
@SerializedName("dateEvent")
var DateMatch: String? = "",

@SerializedName("strHomeTeam")
var HomeTeamName: String? = "",

@SerializedName("intHomeScore")
var HomeScore: String? = "",

@SerializedName("strHomeGoalDetails")
val HomeGoalDetail: String? = "",

@SerializedName("strHomeLineupGoalkeeper")
val HomeGoalKeeper: String? = "",

@SerializedName("strHomeLineupDefense")
val HomeDefense: String? = "",

@SerializedName("strHomeLineupMidfield")
val HomeMidfield: String? = "",

@SerializedName("strHomeLineupForward")
val HomeForward: String? = "",

@SerializedName("strHomeLineupSubstitutes")
val HomeSubstitutes: String? = "",






@SerializedName("strAwayTeam")
var AwayTeamName: String? = "",

@SerializedName("intAwayScore")
var AwayScore: String? = "",

@SerializedName("strAwayGoalDetails")
val AwayGoalDetail: String? = "",

@SerializedName("strAwayLineupGoalkeeper")
val AwayGoalKeeper: String? = "",

@SerializedName("strAwayLineupDefense")
val AwayDefense: String? = "",

@SerializedName("strAwayLineupMidfield")
val AwayMidfield: String? = "",

@SerializedName("strAwayLineupForward")
val AwayForward: String? = "",


@SerializedName("strAwayLineupSubstitutes")
val AwaySubstitutes: String? = ""
)