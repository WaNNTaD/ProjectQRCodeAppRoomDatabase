package be.heh.projectcodeqr_wanntad.database.user

import androidx.room.*


@Entity(tableName = "user")   // annotation pour déclarer la classe comme une entité de base de données
data class UserEntity(
    @ColumnInfo(name="id") @PrimaryKey(autoGenerate = true) var id: Int=0,   // colonne d'identifiant unique, générée automatiquement
    @ColumnInfo(name = "email") var email: String,   // colonne pour l'adresse email de l'utilisateur
    @ColumnInfo(name = "password") var password: String,   // colonne pour le mot de passe de l'utilisateur
    @ColumnInfo(name = "admin") var admin: Boolean=false   // colonne pour le statut d'administrateur de l'utilisateur (false par défaut)
)
