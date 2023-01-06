package be.heh.projectcodeqr_wanntad.hightechitempage

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import be.heh.projectcodeqr_wanntad.DBBuilder
import be.heh.projectcodeqr_wanntad.MainActivity
import be.heh.projectcodeqr_wanntad.R
import be.heh.projectcodeqr_wanntad.database.hightechitem.HighTechItemDAO


// Activité permettant de scanner un code QR pour changer la disponibilité d'un élément high-tech
class ItemScan : AppCompatActivity() {

    // DAO pour accéder à la base de données des éléments high-tech
    private var highTechItemDAO : HighTechItemDAO?= null
    // Champ de texte pour entrer la référence de l'élément à scanner
    private lateinit var textRef : EditText
    // Bouton pour changer la disponibilité de l'élément
    private lateinit var availableButton : Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_scan_activity)

        // Récupère les vues de l'interface utilisateur
        textRef = findViewById<View>(R.id.itemRef) as EditText
        availableButton = findViewById(R.id.availableButton)
        // Récupère les données de l'utilisateur courant passées en extra dans l'intent
        val userMail = intent.getStringExtra("USER_MAIL")
        val userAdmin = intent.getBooleanExtra("USER_ADMIN", false)

        // Ajoute un écouteur d'événement au clic sur le bouton de disponibilité
        availableButton.setOnClickListener(View.OnClickListener {
            // Appelle la méthode pour changer la disponibilité de l'élément
            useItem()
            // Retour à l'activité principale
            val toMain = Intent(this, MainActivity::class.java)
            toMain.putExtra("USER_MAIL",userMail)
            toMain.putExtra("USER_ADMIN",userAdmin)
            startActivity(toMain)
        })
    }

    // Méthode pour changer la disponibilité de l'élément high-tech
    private fun useItem(){
        // Récupère la référence de l'élément entrée dans le champ de texte
        val itemRef = this.textRef?.text.toString()
        // Récupère l'élément high-tech avec cette référence dans la base de données
        this.highTechItemDAO = DBBuilder.db.itemDao()
        val item = this.highTechItemDAO?.getByRef(itemRef)
        // Si l'élément existe, inverse sa disponibilité
        if (item != null) {
            // Inverse la disponibilité de l'élément
            item.availability = item.availability != true
            // Met à jour l'élément dans la base de données
            this.highTechItemDAO?.updateItem(item)
            // Retour à l'activité principale
            val toMain = MainActivity.newIntent(this, INTENT_USER_MAIL.toString())
            startActivity(toMain)
        }

    }

    companion object {

        // Référence de l'utilisateur courant
        private var INTENT_USER_MAIL : String ?= null

        // Crée un nouvel intent pour cette activité avec la référence de l'utilisateur en extra
        fun newIntent(context: Context, userMail: String): Intent {
            val intent = Intent(context, ItemScan::class.java)
            intent.putExtra(INTENT_USER_MAIL, userMail)
            this.INTENT_USER_MAIL = userMail
            return intent
        }
    }
}
