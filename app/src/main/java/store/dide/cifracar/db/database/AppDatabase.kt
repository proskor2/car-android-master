package store.dide.cifracar.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import store.dide.cifracar.db.dao.TagsDao
import store.dide.cifracar.db.models.Tags


@Database(entities = [Tags::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
        abstract fun tagsDao(): TagsDao?
    }
