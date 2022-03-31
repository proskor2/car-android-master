package store.dide.cifracar.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import store.dide.cifracar.db.dao.TagsDao
import store.dide.cifracar.db.models.Tags


@Database(entities = [Tags::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tagsDao(): TagsDao?

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "tag_databse"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}
