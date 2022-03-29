package store.dide.cifracar.db

import android.app.Application
import androidx.room.Room
import store.dide.cifracar.db.database.AppDatabase


class App : Application() {
    private var db: AppDatabase? = null
    override fun onCreate() {
        super.onCreate()
        instance = this
        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "data-database")
            .allowMainThreadQueries()
            .build()
    }

    val databaseInstance: AppDatabase?
        get() = db

    companion object {
        var instance: App? = null
            private set
    }
}