package store.dide.cifracar.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
import javax.crypto.SecretKey

@Entity
data class Tags(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "serial") val serial: String?,
    @ColumnInfo(name = "id_Certificates") val idCertificates: String?,
    @ColumnInfo(name = "state") val state: String,
    @ColumnInfo(name = "number") val number: String, //enum
    @ColumnInfo(name = "created") val created: String?, //date
    @ColumnInfo(name = "updated") val updated: String? //date
)
