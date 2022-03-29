package store.dide.cifracar.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.crypto.SecretKey

@Entity
data class Tags(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "serial") val serial: String?,
    @ColumnInfo(name = "id_Certificates") val idCertificates: String?,
    @ColumnInfo(name = "status") val status: Status,
    @ColumnInfo(name = "secret_Key") val secretKey: String?,
    @ColumnInfo(name = "created") val created: String?,
    @ColumnInfo(name = "updated") val updated: String?,
    @ColumnInfo(name = "is_Deleted") val isDeleted: Boolean
)
