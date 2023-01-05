package be.heh.projectcodeqr_wanntad.database.user

import androidx.room.*


@Entity(tableName = "user")
data class UserEntity(
    @ColumnInfo(name="id") @PrimaryKey(autoGenerate = true) var id: Int=0,
    @ColumnInfo(name = "email") var email: String,
    @ColumnInfo(name = "password") var password: String,
    @ColumnInfo(name = "admin") var admin: Boolean=false
)