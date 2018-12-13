package marc6697com.marc6697.footbalmatchschedulefix.DB

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import marc6697com.marc6697.footbalmatchschedulefix.Favorite.Favorite
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteMatchDetail.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
       db?.createTable(Favorite.TABLE_FAVORITE,true,
           Favorite.ID_MATCH to INTEGER+ PRIMARY_KEY,
           Favorite.DATE_MATCH to TEXT,
           Favorite.HOME_TEAM_NAME to TEXT,
           Favorite.AWAY_TEAM_NAME to TEXT,
           Favorite.HOME_SCORE to TEXT,
           Favorite.AWAY_SCORE to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?,oldVersion:Int,newVersion:Int) {
        db?.dropTable(Favorite.TABLE_FAVORITE,true)
    }

    companion object {
        private var instance:MyDatabaseOpenHelper?=null

        @Synchronized
    fun getInstance(ctx: Context):MyDatabaseOpenHelper{
            if(instance==null)
                instance= MyDatabaseOpenHelper(ctx.applicationContext)
            return instance as MyDatabaseOpenHelper
        }
    }

}
val Context.database:MyDatabaseOpenHelper
get() = MyDatabaseOpenHelper.getInstance(applicationContext)