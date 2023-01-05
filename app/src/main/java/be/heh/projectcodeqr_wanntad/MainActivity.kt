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

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listViewItems = findViewById(R.id.listItem)
        userMailTextView = findViewById(R.id.userMail)
        userListButton = findViewById(R.id.userListButton)
        scannerItemButton = findViewById(R.id.scannerButton)
        addItemButton = findViewById(R.id.addItemButton)


        val userMail = intent.getStringExtra("USER_MAIL")
        user = DBBuilder.db.userDao().findByEmail(userMail)
        val userAdmin = intent.getBooleanExtra("USER_ADMIN", false)
        userMailTextView.text = userMail

        //Boutons pour l'admin
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


        //affichage des items
        itemList = DBBuilder.db.itemDao().getAll()
        val adapter = ItemListAdapter(this, itemList as ArrayList<HighTechItemEntity>)
        listViewItems.adapter = adapter

        listViewItems.setOnItemClickListener { parent, view, i, l ->

            val itemId = listViewItems.getItemIdAtPosition(i) as Int
            for(item in itemList as ArrayList<HighTechItemEntity>){
                if(item.id == itemId){
                    if (userMail != null) {
                        val intent = Intent(this, be.heh.projectcodeqr_wanntad.hightechitempage.ItemDetails::class.java)
                        intent.putExtra("USER_MAIL", userMail)
                        intent.putExtra("ref", item.ref)
                        startActivity(intent)
                    }
                }
            }
        }


        addItemButton.setOnClickListener((View.OnClickListener {
            if (userMail != null) {
                addItem(userMail,userAdmin)
            }
        }))

        scannerItemButton.setOnClickListener(View.OnClickListener {
            if (userMail != null) {
                scanItem(userMail,userAdmin)
            }
        })

        userListButton.setOnClickListener(View.OnClickListener {
            if (userMail != null) {
                toUserList(userMail,userAdmin)
            }
        })




    }


    private fun addItem(userMail: String, userAdmin: Boolean) {
        val toAddItem = Intent(this,be.heh.projectcodeqr_wanntad.hightechitempage.AddItem::class.java)
        toAddItem.putExtra("USER_MAIL", userMail)
        toAddItem.putExtra("USER_ADMIN", userAdmin)
        startActivity(toAddItem)
    }
    private fun scanItem(userMail : String, userAdmin : Boolean) {
        val scanItem = Intent(this,be.heh.projectcodeqr_wanntad.hightechitempage.ItemScan::class.java)
        scanItem.putExtra("USER_MAIL", userMail)
        scanItem.putExtra("USER_ADMIN", userAdmin)
        startActivity(scanItem)
    }
    fun toUserList(userMail : String, userAdmin : Boolean) {
        val toUserList = Intent(this, UserList::class.java)
        toUserList.putExtra("USER_MAIL", userMail)
        toUserList.putExtra("USER_ADMIN", userAdmin)
        startActivity(toUserList)
    }


    companion object {
        private var INTENT_USER_MAIL : String ?= null
        fun newIntent(context: Context, userMail: String): Intent {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(INTENT_USER_MAIL, userMail)
            this.INTENT_USER_MAIL = userMail
            return intent
        }
    }
}