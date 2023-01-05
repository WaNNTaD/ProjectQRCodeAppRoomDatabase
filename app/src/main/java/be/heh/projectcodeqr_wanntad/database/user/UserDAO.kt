package be.heh.projectcodeqr_wanntad.database.user

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDAO {
    @Query("SELECT * FROM user")
    fun getAll(): List<UserEntity>


    @Query("SELECT * FROM user WHERE email = :user_email "+"LIMIT 1")
    fun findByEmail(user_email: String?): UserEntity

    @Insert
    fun insertUser(vararg listCategories: UserEntity)

    @Delete
    fun deleteUser(user: UserEntity)
}