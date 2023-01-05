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
    private lateinit var textType: EditText
    private lateinit var textModel: EditText
    private lateinit var textRef: EditText
    private lateinit var textLink: EditText
    private lateinit var addButton: Button

    private var dao: HighTechItemDAO?=null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        textType = findViewById(R.id.type)
        textModel = findViewById(R.id.model)
        textRef = findViewById(R.id.ref)
        textLink = findViewById(R.id.link)
        addButton = findViewById(R.id.addButton)
        val userMail = intent.getStringExtra("USER_MAIL")
        val userAdmin = intent.getBooleanExtra("USER_ADMIN", false)


        dao = DBBuilder.db.itemDao()

        addButton.setOnClickListener(View.OnClickListener {

            val toMain = Intent(this, MainActivity::class.java)
            toMain.putExtra("USER_MAIL",userMail)
            toMain.putExtra("USER_ADMIN",userAdmin)
            addItemClick()
            startActivity(toMain)
        })



    }
    private fun addItemClick() {
        val itemType = this.textType?.text.toString()
        val itemModel = this.textModel?.text.toString()
        val itemRef = this.textRef?.text.toString()
        val itemLink = this.textLink?.text.toString()

        if(itemType == "" || itemModel == "" || itemRef == "" || itemLink == ""){
            Toast.makeText(this, "Complétez tous les champs.", Toast.LENGTH_LONG).show()
        }else {
            val i = HighTechItem(0, itemType, itemModel, itemRef, itemLink, true)
            val i1 = HighTechItemEntity(0, i.type, i.model, i.ref, i.link,i.availability)
            dao?.insertItem(i1)
            Toast.makeText(this, "Appareil créé avec succès", Toast.LENGTH_LONG).show()
        }
    }

    companion object {

        private var INTENT_USER_MAIL : String ?= null

        fun addMaterialIntent(context: Context, userMail: String): Intent {
            val intent = Intent(context, AddItem::class.java)
            intent.putExtra(INTENT_USER_MAIL, userMail)
            INTENT_USER_MAIL = userMail
            return intent
        }
    }
}