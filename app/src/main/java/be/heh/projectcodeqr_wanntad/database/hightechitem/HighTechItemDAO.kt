package be.heh.projectcodeqr_wanntad.database.hightechitem

import androidx.room.*

@Dao   // annotation pour déclarer l'interface comme un DAO (objet d'accès aux données)
interface HighTechItemDAO {
    // requête pour sélectionner tous les appareils de la table "items"
    @Query("SELECT * FROM items")
    fun getAll(): List<HighTechItemEntity>

    // requête pour sélectionner l'appareil avec la référence spécifiée dans la table "items"
    @Query("SELECT * FROM items WHERE ref = :ref")
    fun getByRef(ref: String?): HighTechItemEntity

    // requête pour insérer un ou plusieurs appareils dans la table "items"
    @Insert
    fun insertItem(vararg listCategories: HighTechItemEntity)

    // requête pour mettre à jour un appareil dans la table "items"
    @Update
    fun updateItem(item: HighTechItemEntity)

    // requête pour supprimer un appareil de la table "items"
    @Delete
    fun deleteItem(item: HighTechItemEntity)
}
