package be.heh.projectcodeqr_wanntad.database

import androidx.room.Database
import androidx.room.RoomDatabase
import be.heh.projectcodeqr_wanntad.database.hightechitem.HighTechItemDAO
import be.heh.projectcodeqr_wanntad.database.hightechitem.HighTechItemEntity
import be.heh.projectcodeqr_wanntad.database.user.UserDAO
import be.heh.projectcodeqr_wanntad.database.user.UserEntity

@Database(entities = [UserEntity::class, HighTechItemEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDAO
    abstract fun itemDao(): HighTechItemDAO
}