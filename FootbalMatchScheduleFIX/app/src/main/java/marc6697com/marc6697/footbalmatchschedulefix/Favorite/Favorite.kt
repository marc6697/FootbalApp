package marc6697com.marc6697.footbalmatchschedulefix.Favorite

data class Favorite(val IdMatch: Int, val DateMatch: String, val HomeTeamName: String, val AwayTeamName: String, val HomeScore: String, val AwayScore: String) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID_MATCH: String = "ID_MATCH"
        const val DATE_MATCH: String = "DATE_MATCH"
        const val HOME_TEAM_NAME: String = "HOME_TEAM_NAME"
        const val AWAY_TEAM_NAME: String = "AWAY_TEAM_NAME"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val AWAY_SCORE: String = "AWAY_SCORE"
    }
}