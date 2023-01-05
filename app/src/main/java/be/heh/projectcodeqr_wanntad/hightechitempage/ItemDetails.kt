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


class ItemDetails : Activity() {
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
    private var item : HighTechItemEntity?= null
    private var user : UserEntity?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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


        val itemRef = intent.getStringExtra("ref")
        val userMail = intent.getStringExtra("USER_MAIL")
        item = DBBuilder.db.itemDao().getByRef(itemRef)
        user = DBBuilder.db.userDao().findByEmail(userMail)
        var i = ""


        textRef!!.setText(item!!.ref)
        textModel!!.setText(item!!.model)
        textType!!.setText(item!!.type)
        textLink!!.setText(item!!.link)
        if(item!!.availability){
            i = "Disponible"
        }else{
            i = "Non disponible"
        }
        textIsAvailable!!.setText(i)

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

    }
    fun modifyButton(){
        typeEdit?.setVisibility(View.VISIBLE)
        textType?.setVisibility(View.GONE)
        modelEdit?.setVisibility(View.VISIBLE)
        textModel?.setVisibility(View.GONE)
        refEdit?.setVisibility(View.VISIBLE)
        textRef?.setVisibility(View.GONE)
        linkEdit?.setVisibility(View.VISIBLE)
        textLink?.setVisibility(View.GONE)
        textIsAvailable?.setVisibility(View.GONE)
        val params: LinearLayout.LayoutParams = validateButton!!.getLayoutParams() as LinearLayout.LayoutParams
        validateButton!!.setLayoutParams(params)
        params.width = 470
        validateButton!!.setVisibility(View.VISIBLE)
        validateButton!!.setClickable(true)
        typeEdit?.text ?: textType?.text.toString()
    }

    fun validateButton(){
        val itemType = this.typeEdit?.text.toString()
        val itemModel = this.modelEdit?.text.toString()
        val itemRef = this.refEdit?.text.toString()
        val itemLink = this.linkEdit?.text.toString()
        var itemUpdated = item?.let { HighTechItemEntity(it.id,itemType, itemModel, itemRef, itemLink, item!!.availability) }
        itemUpdated?.let { DBBuilder.db.itemDao().updateItem(it) }
        Toast.makeText(this, "Appareil créé avec succès", Toast.LENGTH_LONG).show()
        val intent = MainActivity.newIntent(this, intent_mail.toString())
        startActivity(intent)
    }

    fun deleteButton() {

        val toMain = MainActivity.newIntent(this, intent_mail.toString())
        startActivity(toMain)
    }

    fun toLink(v : View){
        val link = Intent(Intent.ACTION_VIEW, Uri.parse(textLink?.text.toString()))
        startActivity(link)
    }
    companion object {

        private var intent_ref : String ?= null
        private var intent_mail : String ?= null

        fun newIntent(context: Context, ref: String, userMail: String): Intent {
            val intent = Intent(context, ItemDetails::class.java)
            intent.putExtra(intent_ref, ref)
            intent.putExtra(intent_mail, userMail)
            this.intent_mail = userMail
            this.intent_ref = ref
            return intent
        }
    }
}