package store.dide.cifracar.db

import android.app.Application
import androidx.room.Room
import store.dide.cifracar.db.database.AppDatabase


class AppDB : Application() {
    var database: AppDatabase? = null
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(this, AppDatabase::class.java, "database")
            .build()
    }

    companion object {
        var instance: AppDB? = null
    }
}