package be.heh.projectcodeqr_wanntad.userpage

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import be.heh.projectcodeqr_wanntad.R
import be.heh.projectcodeqr_wanntad.database.user.UserEntity

// Adaptateur pour afficher une liste d'utilisateurs dans une vue de liste
class UserListAdapter(
    // Contexte de l'activité utilisant l'adaptateur
    private val context: Activity,
    // Liste des utilisateurs à afficher
    private val arrayList: ArrayList<UserEntity>
) : ArrayAdapter<UserEntity>(context, R.layout.custom_user_layout, arrayList) {

    @SuppressLint("MissingInflatedId")
    // Méthode appelée pour créer chaque vue de l'élément de la liste
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        // Inflate la vue personnalisée pour chaque élément de la liste
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.custom_user_layout, null)

        // Récupère les vues de l'interface de la vue personnalisée
        val textEmail: TextView = view.findViewById(R.id.text_email)
        val textRole: TextView = view.findViewById(R.id.text_role)

        // Met à jour les vues avec les données de l'utilisateur courant
        textEmail.text = arrayList[position].email
        textRole.text = if (arrayList[position].admin) "Admin" else "Non-admin"

        // Retourne la vue personnalisée pour l'élément de la liste courant
        return view
    }
}
