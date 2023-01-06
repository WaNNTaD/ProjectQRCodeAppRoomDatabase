package be.heh.projectcodeqr_wanntad.hightechitempage

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import be.heh.projectcodeqr_wanntad.MainActivity
import be.heh.projectcodeqr_wanntad.R
import be.heh.projectcodeqr_wanntad.database.hightechitem.HighTechItemEntity
import be.heh.projectcodeqr_wanntad.database.user.UserEntity

class ItemListAdapter(
    private val context: Activity,      // l'activité dans laquelle cet adaptateur est utilisé
    private val arrayList: ArrayList<HighTechItemEntity>   // la liste d'objets HighTechItemEntity à afficher
) : ArrayAdapter<HighTechItemEntity>(context, R.layout.custom_item_layout, arrayList) {

    @SuppressLint("MissingInflatedId")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        // inflater la mise en page personnalisée pour chaque élément de la liste
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.custom_item_layout, null)

        // récupérer les textviews de la mise en page
        val textType: TextView = view.findViewById(R.id.text_type)
        val textModel: TextView = view.findViewById(R.id.text_model)
        val textRef: TextView = view.findViewById(R.id.text_ref)
        val textLink: TextView = view.findViewById(R.id.text_link)
        val textAvailability: TextView = view.findViewById(R.id.text_availability)

        // définir le texte pour chaque textview en utilisant les valeurs correspondantes de l'objet HighTechItemEntity à la position donnée
        textType.text = arrayList[position].type
        textModel.text = arrayList[position].model
        textRef.text = arrayList[position].ref
        textLink.text = arrayList[position].link
        if (arrayList[position].availability) textAvailability.text = "Disponible"
        else textAvailability.text = "Non disponible"

        // définir un onClickListener pour la vue, qui démarre une nouvelle activité (ItemDetails) lorsqu'on clique dessus
        view.setOnClickListener {
            // créer l'intention de démarrer la nouvelle activité
            val intent = Intent(context, ItemDetails::class.java)
            // inclure des données supplémentaires avec l'intention: l'adresse email de l'utilisateur et les valeurs de plusieurs champs de l'objet HighTechItemEntity à la position cliquée
            intent.putExtra("USER_MAIL",(context as MainActivity).userMail)
            intent.putExtra("type", arrayList[position].type)
            intent.putExtra("model", arrayList[position].model)
            intent.putExtra("ref", arrayList[position].ref)
            intent.putExtra("link", arrayList[position].link)
            // démarrer l'activité
            context.startActivity(intent)
        }

        // renvoyer la vue
        return view
    }
}
