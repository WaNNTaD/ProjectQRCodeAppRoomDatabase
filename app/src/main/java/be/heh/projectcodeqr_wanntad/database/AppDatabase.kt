package be.heh.projectcodeqr_wanntad.database

import androidx.room.Database   // annotation pour déclarer une base de données Room
import androidx.room.RoomDatabase   // classe de base pour toutes les bases de données Room
import be.heh.projectcodeqr_wanntad.database.hightechitem.HighTechItemDAO   // interface définissant les opérations de base pour les objets HighTechItemEntity dans la base de données
import be.heh.projectcodeqr_wanntad.database.hightechitem.HighTechItemEntity   // entité représentant un objet HighTechItem dans la base de données
import be.heh.projectcodeqr_wanntad.database.user.UserDAO   // interface définissant les opérations de base pour les objets UserEntity dans la base de données
import be.heh.projectcodeqr_wanntad.database.user.UserEntity   // entité représentant un utilisateur dans la base de données

// annotation pour déclarer la classe comme une base de données Room
@Database(entities = [UserEntity::class, HighTechItemEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    // déclarer les DAO pour accéder aux utilisateurs et aux objets HighTechItem dans la base de données
    abstract fun userDao(): UserDAO
    abstract fun itemDao(): HighTechItemDAO
}
