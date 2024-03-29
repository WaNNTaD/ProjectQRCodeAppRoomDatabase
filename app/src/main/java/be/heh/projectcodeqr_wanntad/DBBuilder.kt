package be.heh.projectcodeqr_wanntad

import android.content.Context
import androidx.room.Room
import be.heh.projectcodeqr_wanntad.database.AppDatabase
import be.heh.projectcodeqr_wanntad.database.hightechitem.HighTechItem
import be.heh.projectcodeqr_wanntad.database.hightechitem.HighTechItemEntity
import be.heh.projectcodeqr_wanntad.database.user.UserEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// Classe responsable de la création de la connexion à la base de données
// et de l'insertion de données par défaut si la base de données est vide
class DBBuilder {

    companion object {
        // Objet représentant la base de données
        lateinit var db: AppDatabase private set get

        // Méthode pour créer la connexion à la base de données
        // et insérer des données par défaut si la base de données est vide
        fun startDatabase(applicationContext: Context) {
            val startJob = CoroutineScope(Dispatchers.IO).launch {
                // Création de la base de données en utilisant la classe AppDatabase
                // et en autorisant les requêtes depuis le thread principal
                db = Room.databaseBuilder(
                    applicationContext,
                    AppDatabase::class.java, "bdd_heh_android_app"
                ).allowMainThreadQueries().build()

                // Si la base de données est vide, appel de la méthode
                // pour insérer des données par défaut
                if(db.userDao().getAll().isEmpty()&&db.itemDao().getAll().isEmpty()) {
                    insertInDatabaseDefault()
                }
            }
        }

        // Méthode pour insérer des données par défaut dans la base de données
        private fun insertInDatabaseDefault(){
            // Insertion d'un utilisateur par défaut
            db.userDao().insertUser(UserEntity(0,"scopel.fabrice@heh.be","test123",true))
            // Liste d'articles haute technologie à insérer par défaut
            val items = listOf(
                HighTechItemEntity(0, "téléphone","Apple"+" iPhone 12", "A12B3C","https://www.apple.com/befr/",true),
                HighTechItemEntity(0, "téléphone","Samsung"+" Galaxy S20", "B23C4D","https://www.samsung.com/be_fr/",true),
                HighTechItemEntity(0, "téléphone","Huawei"+" P40 Pro", "C34D5E","https://consumer.huawei.com/be-fr/",true),
                HighTechItemEntity(0, "téléphone","Google"+" Pixel 4", "D45E6F","https://store.google.com/be/?hl=fr-BE&pli=1",true),
                HighTechItemEntity(0, "téléphone","OnePlus"+" 8 Pro", "E56F7G","https://www.oneplus.com/be_fr",true),
                HighTechItemEntity(0, "tablette","Apple"+ " iPad Pro", "F67G8H","https://www.apple.com/befr/",true),
                HighTechItemEntity(0, "tablette","Samsung"+ " Galaxy Tab S7", "G78H9I","https://www.samsung.com/be_fr/",true),
                HighTechItemEntity(0, "tablette","Huawei"+ " MatePad Pro", "H89I0J","https://consumer.huawei.com/be-fr/",true),
                HighTechItemEntity(0, "tablette","Google"+ " Pixel Slate", "I0J1K2","https://store.google.com/be/?hl=fr-BE&pli=1",true),
                HighTechItemEntity(0, "tablette","OnePlus"+ " Nord Pad", "J1K2L3","https://www.oneplus.com/be_fr",true),
                HighTechItemEntity(0, "téléphone","Apple"+" iPhone SE", "K2L3M4","https://www.apple.com/befr/",true),
                HighTechItemEntity(0, "téléphone","Samsung"+ " Galaxy A50", "L3M4N5","https://www.samsung.com/be_fr/",true),
                HighTechItemEntity(0, "téléphone","Huawei"+ " P30", "M4N5O6","https://consumer.huawei.com/be-fr/",true),
                HighTechItemEntity(0, "téléphone","Google"+" Pixel 3", "N5O6P7","https://store.google.com/be/?hl=fr-BE&pli=1",true),
                HighTechItemEntity(0, "téléphone","OnePlus"+ " 7T", "O6P7Q8","https://www.oneplus.com/be_fr",true)
            )
            //Insertion des articles dans la base de données
            for(item in items){
                db.itemDao().insertItem(item)
            }
        }
    }
}