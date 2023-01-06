package be.heh.projectcodeqr_wanntad.database.user

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao   // annotation pour déclarer l'interface comme un DAO (objet d'accès aux données)
interface UserDAO {
    // requête pour sélectionner tous les utilisateurs de la table "user"
    @Query("SELECT * FROM user")
    fun getAll(): List<UserEntity>

    // requête pour sélectionner l'utilisateur avec l'adresse email spécifiée dans la table "user"
    @Query("SELECT * FROM user WHERE email = :user_email "+"LIMIT 1")
    fun findByEmail(user_email: String?): UserEntity

    // requête pour insérer un ou plusieurs utilisateurs dans la table "user"
    @Insert
    fun insertUser(vararg listCategories: UserEntity)

    // requête pour supprimer un utilisateur de la table "user"
    @Delete
    fun deleteUser(user: UserEntity)
}
