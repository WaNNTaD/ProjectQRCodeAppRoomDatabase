package be.heh.projectcodeqr_wanntad.hightechitempage

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import be.heh.projectcodeqr_wanntad.DBBuilder
import be.heh.projectcodeqr_wanntad.MainActivity
import be.heh.projectcodeqr_wanntad.R
import be.heh.projectcodeqr_wanntad.database.hightechitem.HighTechItem
import be.heh.projectcodeqr_wanntad.database.hightechitem.HighTechItemDAO
import be.heh.projectcodeqr_wanntad.database.hightechitem.HighTechItemEntity


class AddItem : Activity(){
    private lateinit var textType: EditText    // champ de saisie pour le type de l'appareil
    private lateinit var textModel: EditText   // champ de saisie pour le modèle de l'appareil
    private lateinit var textRef: EditText     // champ de saisie pour la référence de l'appareil
    private lateinit var textLink: EditText    // champ de saisie pour le lien de l'appareil
    private lateinit var addButton: Button     // bouton pour ajouter l'appareil à la liste

    private var dao: HighTechItemDAO?=null     // DAO (objet d'accès aux données) pour manipuler la base de données

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        // récupérer les champs de saisie et le bouton à partir de la mise en page
        textType = findViewById(R.id.type)
        textModel = findViewById(R.id.model)
        textRef = findViewById(R.id.ref)
        textLink = findViewById(R.id.link)
        addButton = findViewById(R.id.addButton)

        // récupérer l'adresse email de l'utilisateur et son statut d'administrateur depuis l'intention qui a démarré l'activité
        val userMail = intent.getStringExtra("USER_MAIL")
        val userAdmin = intent.getBooleanExtra("USER_ADMIN", false)

        // initialiser le DAO
        dao = DBBuilder.db.itemDao()

        // définir un onClickListener pour le bouton d'ajout
        addButton.setOnClickListener(View.OnClickListener {
            // créer l'intention pour retourner à l'activité principale en passant les données de l'utilisateur
            val toMain = Intent(this, MainActivity::class.java)
            toMain.putExtra("USER_MAIL",userMail)
            toMain.putExtra("USER_ADMIN",userAdmin)
            // appeler la méthode pour gérer le clic sur le bouton d'ajout
            addItemClick()
            // démarrer l'activité principale
            startActivity(toMain)
        })
    }

    // méthode pour gérer le clic sur le bouton d'ajout
    private fun addItemClick() {
        // récupérer le texte des champs de saisie
        val itemType = this.textType?.text.toString()
        val itemModel = this.textModel?.text.toString()
        val itemRef = this.textRef?.text.toString()
        val itemLink = this.textLink?.text.toString()

        // si un des champs est vide, afficher un message toast à l'utilisateur
        if(itemType == "" || itemModel == "" || itemRef == "" || itemLink == ""){
            Toast.makeText(this, "Complétez tous les champs.", Toast.LENGTH_LONG).show()
        }else {
            // sinon, créer un nouvel objet HighTechItem avec les valeurs entrées par l'utilisateur et l'ajouter à la base de données
            val i = HighTechItem(0, itemType, itemModel, itemRef, itemLink, true)
            val i1 = HighTechItemEntity(0, i.type, i.model, i.ref, i.link,i.availability)
            dao?.insertItem(i1)
            // afficher un message toast pour confirmer l'ajout réussi
            Toast.makeText(this, "Appareil créé avec succès", Toast.LENGTH_LONG).show()
        }

    }

}