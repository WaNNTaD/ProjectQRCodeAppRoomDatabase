package be.heh.projectcodeqr_wanntad.hightechitempage

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.*
import be.heh.projectcodeqr_wanntad.DBBuilder
import be.heh.projectcodeqr_wanntad.MainActivity
import be.heh.projectcodeqr_wanntad.R
import be.heh.projectcodeqr_wanntad.database.hightechitem.HighTechItemEntity
import be.heh.projectcodeqr_wanntad.database.user.UserEntity

// Classe qui représente l'écran de détails d'un article high-tech
class ItemDetails : Activity() {

    // Champs de vue de l'écran
    private var textRef: TextView?=null
    private var textModel: TextView?=null
    private var textType: TextView?=null
    private var textLink: TextView?=null
    private var typeEdit : EditText?=null
    private var modelEdit : EditText?=null
    private var refEdit : EditText?=null
    private var linkEdit : EditText?=null
    private var modifyButton : Button?=null
    private var deleteButton : Button?=null
    private var validateButton : Button?=null
    private var textIsAvailable: TextView?=null

    // L'article à afficher et l'utilisateur associé à cet article
    private var item : HighTechItemEntity?= null
    private var user : UserEntity?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Définit le contenu de l'écran et récupère les champs de vue
        setContentView(R.layout.item_details)
        textRef = findViewById<View>(R.id.text_ref) as TextView
        textModel = findViewById<View>(R.id.text_model) as TextView
        textType = findViewById<View>(R.id.text_type) as TextView
        textLink = findViewById<View>(R.id.text_link) as TextView
        modifyButton = findViewById(R.id.modifyButtonId) as Button
        deleteButton = findViewById(R.id.deleteButtonId) as Button
        validateButton = findViewById(R.id.validateButtonId) as Button
        textIsAvailable = findViewById(R.id.text_isAvailable) as TextView
        typeEdit = findViewById(R.id.type) as EditText
        modelEdit = findViewById(R.id.model) as EditText
        refEdit = findViewById(R.id.ref) as EditText
        linkEdit = findViewById(R.id.link) as EditText


        // Récupère les données de l'article et de l'utilisateur depuis les extra de l'Intent
        val itemRef = intent.getStringExtra("ref")
        val userMail = intent.getStringExtra("USER_MAIL")
        item = DBBuilder.db.itemDao().getByRef(itemRef)
        user = DBBuilder.db.userDao().findByEmail(userMail)

        // Initialise la chaîne de disponibilité de l'article
        var i = ""

        // Remplit les champs de vue avec les données de l'article
        textRef!!.setText(item!!.ref)
        textModel!!.setText(item!!.model)
        textType!!.setText(item!!.type)
        textLink!!.setText(item!!.link)
        // Définit la chaîne de disponibilité en fonction de la disponibilité de l'article
        if(item!!.availability){
            i = "Disponible"
        }else{
            i = "Non disponible"
        }
        textIsAvailable!!.setText(i)

        // Si l'utilisateur est un administrateur, affiche les boutons de modification et de suppression
        if(user!!.admin){
            val params: LinearLayout.LayoutParams = modifyButton!!.getLayoutParams() as LinearLayout.LayoutParams
            modifyButton!!.setLayoutParams(params)
            deleteButton!!.setLayoutParams(params)
            params.width = 470
            modifyButton!!.setVisibility(View.VISIBLE)
            modifyButton!!.setClickable(true)
            deleteButton!!.setVisibility(View.VISIBLE)
            deleteButton!!.setClickable(true)
        }


        modifyButton!!.setOnClickListener(View.OnClickListener {
            modifyButton()
        })

        deleteButton!!.setOnClickListener(View.OnClickListener {
            deleteButton()
        })

        validateButton!!.setOnClickListener(View.OnClickListener {
            validateButton()
        })

        textLink!!.setOnClickListener(View.OnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(textLink!!.text.toString()))
            startActivity(browserIntent)
        })
    }

    // Affiche les champs de modification et cache les champs de texte
    private fun modifyButton() {
        textRef!!.visibility = View.GONE
        textModel!!.visibility = View.GONE
        textType!!.visibility = View.GONE
        textLink!!.visibility = View.GONE
        refEdit!!.visibility = View.VISIBLE
        modelEdit!!.visibility = View.VISIBLE
        typeEdit!!.visibility = View.VISIBLE
        linkEdit!!.visibility = View.VISIBLE
        validateButton!!.visibility = View.VISIBLE
        modifyButton!!.visibility = View.GONE
        deleteButton!!.visibility = View.GONE
        // Remplit les champs de modification avec les données de l'article
        refEdit!!.setText(item!!.ref)
        modelEdit!!.setText(item!!.model)
        typeEdit!!.setText(item!!.type)
        linkEdit!!.setText(item!!.link)
    }

    // Met à jour l'article dans la base de données avec les données des champs de modification et retourne à l'écran principal
    private fun validateButton() {
        // Met à jour l'article avec les nouvelles données des champs de modification
        item!!.ref = refEdit!!.text.toString()
        item!!.model = modelEdit!!.text.toString()
        item!!.type = typeEdit!!.text.toString()
        item!!.link = linkEdit!!.text.toString()
        // Enregistre les modifications dans la base de données
        DBBuilder.db.itemDao().updateItem(item!!)
        // Retourne à l'écran principal
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("USER_MAIL", user!!.email)
        intent.putExtra("USER_ADMIN", user!!.admin)
        startActivity(intent)
    }

    // Supprime l'article de la base de données et retourne à l'écran principal
    fun deleteButton() {
        // Supprime l'article de la base de données
        DBBuilder.db.itemDao().deleteItem(item!!)

        // Retourne à l'écran principal
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("USER_MAIL", user!!.email)
        intent.putExtra("USER_ADMIN",user!!.admin)
        startActivity(intent)
    }


}