package be.heh.projectcodeqr_wanntad.database.hightechitem

import androidx.room.*

@Dao
interface HighTechItemDAO {
    @Query("SELECT * FROM items")
    fun getAll(): List<HighTechItemEntity>

    @Query("SELECT * FROM items WHERE ref = :ref")
    fun getByRef(ref: String?): HighTechItemEntity

    @Insert
    fun insertItem(vararg listCategories: HighTechItemEntity)

    @Update
    fun updateItem(item: HighTechItemEntity)

    @Delete
    fun deleteItem(item: HighTechItemEntity)
}