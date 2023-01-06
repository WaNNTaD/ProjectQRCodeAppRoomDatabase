package be.heh.projectcodeqr_wanntad

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.LinearLayout.LayoutParams
import be.heh.projectcodeqr_wanntad.database.hightechitem.HighTechItemEntity
import be.heh.projectcodeqr_wanntad.database.user.UserEntity
import be.heh.projectcodeqr_wanntad.hightechitempage.ItemDetails
import be.heh.projectcodeqr_wanntad.hightechitempage.ItemListAdapter


class MainActivity: Activity() {

    private lateinit var userListButton: Button
    private lateinit var scannerItemButton: Button
    private lateinit var addItemButton : Button
    private lateinit var userMailTextView: TextView
    private lateinit var listViewItems : ListView
    private var itemList : List<HighTechItemEntity>?= null
    private var user : UserEntity?=null
    var userMail : String?=null

    // Empêche les avertissements pour l'ID manquant dans le layout de cette activité
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Récupère les vues du layout
        listViewItems = findViewById(R.id.listItem)
        userMailTextView = findViewById(R.id.userMail)
        userListButton = findViewById(R.id.userListButton)
        scannerItemButton = findViewById(R.id.scannerButton)
        addItemButton = findViewById(R.id.addItemButton)

        // Récupère les informations de l'utilisateur envoyées via l'intent
        userMail = intent.getStringExtra("USER_MAIL")
        user = DBBuilder.db.userDao().findByEmail(userMail)
        val userAdmin = intent.getBooleanExtra("USER_ADMIN", false)
        userMailTextView.text = userMail

        // Si l'utilisateur est administrateur, affiche les boutons supplémentaires et rend ces boutons cliquables
        if(userAdmin == true){
            val paramUserListButton: LinearLayout.LayoutParams = userListButton!!.getLayoutParams() as LayoutParams
            val paramScanButton: LinearLayout.LayoutParams = scannerItemButton!!.getLayoutParams() as LayoutParams
            val paramAddButton: LinearLayout.LayoutParams = addItemButton!!.getLayoutParams() as LayoutParams
            userListButton!!.setLayoutParams(paramUserListButton)
            scannerItemButton!!.setLayoutParams(paramScanButton)
            addItemButton!!.setLayoutParams(paramAddButton)
            paramUserListButton.width = 500
            paramScanButton.width = 500
            paramAddButton.width = 500
            userListButton!!.setVisibility(View.VISIBLE)
            userListButton!!.setClickable(true)
            scannerItemButton!!.setVisibility(View.VISIBLE)
            scannerItemButton!!.setClickable(true)
            addItemButton!!.setVisibility(View.VISIBLE)
            addItemButton!!.setClickable(true)
        }

        // Affiche la liste des items
        itemList = DBBuilder.db.itemDao().getAll()
        val adapter = ItemListAdapter(this, itemList as ArrayList<HighTechItemEntity>)
        listViewItems.adapter = adapter

        // Définit les listeners pour chaque bouton
        addItemButton.setOnClickListener((View.OnClickListener {
            if (userMail != null) {
                addItem(userMail!!,userAdmin)
            }
        }))
        scannerItemButton.setOnClickListener(View.OnClickListener {
            if (userMail != null) {
                scanItem(userMail!!,userAdmin)
            }
        })

        userListButton.setOnClickListener(View.OnClickListener {
            if (userMail != null) {
                toUserList(userMail!!,userAdmin)
            }
        })

    }

    // Redirige vers l'activité d'ajout d'item en envoyant les informations de l'utilisateur
    private fun addItem(userMail: String, userAdmin: Boolean) {
        val toAddItem = Intent(this,be.heh.projectcodeqr_wanntad.hightechitempage.AddItem::class.java)
        toAddItem.putExtra("USER_MAIL", userMail)
        toAddItem.putExtra("USER_ADMIN", userAdmin)
        startActivity(toAddItem)
    }
    // Redirige vers l'activité de scan d'item en envoyant les informations de l'utilisateur
    private fun scanItem(userMail : String, userAdmin : Boolean) {
        val scanItem = Intent(this,be.heh.projectcodeqr_wanntad.hightechitempage.ItemScan::class.java)
        scanItem.putExtra("USER_MAIL", userMail)
        scanItem.putExtra("USER_ADMIN", userAdmin)
        startActivity(scanItem)
    }
    // Redirige vers l'activité de liste d'utilisateurs en envoyant les informations de l'utilisateur
    fun toUserList(userMail : String, userAdmin : Boolean) {
        val toUserList = Intent(this, UserList::class.java)
        toUserList.putExtra("USER_MAIL", userMail)
        toUserList.putExtra("USER_ADMIN", userAdmin)
        startActivity(toUserList)
    }


    companion object {
        // Clé utilisée pour envoyer l'email de l'utilisateur via l'intent
        private var INTENT_USER_MAIL : String ?= null
        // Crée un nouvel intent avec l'email de l'utilisateur en tant qu'extra
        fun newIntent(context: Context, userMail: String): Intent {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(INTENT_USER_MAIL, userMail)
            return intent
        }
    }
}