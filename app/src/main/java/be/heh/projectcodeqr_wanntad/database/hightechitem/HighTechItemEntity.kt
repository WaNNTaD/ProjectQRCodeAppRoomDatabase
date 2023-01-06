package be.heh.projectcodeqr_wanntad.database.hightechitem

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")   // annotation pour déclarer la classe comme une entité de base de données
data class HighTechItemEntity(
    @ColumnInfo(name="id") @PrimaryKey(autoGenerate = true) var id: Int=0,   // colonne d'identifiant unique, générée automatiquement
    @ColumnInfo(name = "type") var type: String,   // colonne pour le type de l'appareil
    @ColumnInfo(name = "model") var model: String,   // colonne pour le modèle de l'appareil
    @ColumnInfo(name = "ref") var ref: String,   // colonne pour la référence de l'appareil
    @ColumnInfo(name = "link") var link: String,   // colonne pour le lien du site du fabriquant de l'appareil
    @ColumnInfo(name="availability") var availability: Boolean   // colonne pour l'état de disponibilité de l'appareil
)
