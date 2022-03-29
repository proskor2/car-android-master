package store.dide.cifracar.db.dao

import androidx.room.*
import store.dide.cifracar.db.models.Tags

@Dao
interface TagsDao {

        @Query("SELECT * FROM tags")
        fun getAll(): List<Tags>?

        @Insert
        fun insert(vararg tags: Tags)

        @Update
        fun update(vararg tags: Tags)

        @Delete
        fun delete(tags: Tags)

}