package be.heh.projectcodeqr_wanntad

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.widget.ListView
import be.heh.projectcodeqr_wanntad.database.user.UserDAO
import be.heh.projectcodeqr_wanntad.database.user.UserEntity
import be.heh.projectcodeqr_wanntad.userpage.UserListAdapter

// Activité pour afficher la liste des utilisateurs enregistrés
class UserList : Activity() {
    // Liste d'objets représentant les utilisateurs enregistrés
    private lateinit var userList: List<UserEntity>
    // Vue pour afficher la liste des utilisateurs
    private lateinit var userListView : ListView
    // Accès à la couche de données pour récupérer les utilisateurs enregistrés
    private var dao: UserDAO?=null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        // Récupère la vue de la liste des utilisateurs
        userListView = findViewById(R.id.user_list_view)
        // Récupère l'accès à la couche de données
        dao = DBBuilder.db.userDao()
        // Récupère la liste des utilisateurs enregistrés
        userList = dao?.getAll()!!
        // Crée un adapter pour afficher la liste des utilisateurs dans la vue
        val adapter = UserListAdapter(this, userList as ArrayList<UserEntity>)
        // Associe l'adapter à la vue de la liste des utilisateurs
        userListView.adapter = adapter

    }
}
