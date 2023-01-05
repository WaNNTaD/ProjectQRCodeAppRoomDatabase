package be.heh.projectcodeqr_wanntad.database.hightechitem

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class HighTechItemEntity(
    @ColumnInfo(name="id") @PrimaryKey(autoGenerate = true) var id: Int=0,
    @ColumnInfo(name = "type") var type: String,
    @ColumnInfo(name = "model") var model: String,
    @ColumnInfo(name = "ref") var ref: String,
    @ColumnInfo(name = "link") var link: String,
    @ColumnInfo(name="availability") var availability: Boolean

)