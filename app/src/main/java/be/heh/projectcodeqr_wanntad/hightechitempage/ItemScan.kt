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


class ItemScan : AppCompatActivity() {


    private var highTechItemDAO : HighTechItemDAO?= null
    private lateinit var textRef : EditText
    private lateinit var availableButton : Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_scan_activity)

        textRef = findViewById<View>(R.id.itemRef) as EditText
        availableButton = findViewById(R.id.availableButton)
        val userMail = intent.getStringExtra("USER_MAIL")
        val userAdmin = intent.getBooleanExtra("USER_ADMIN", false)

        availableButton.setOnClickListener(View.OnClickListener {
            useItem()
            val toMain = Intent(this, MainActivity::class.java)
            toMain.putExtra("USER_MAIL",userMail)
            toMain.putExtra("USER_ADMIN",userAdmin)
            startActivity(toMain)
        })


    }

    private fun useItem(){

        val itemRef = this.textRef?.text.toString()
        this.highTechItemDAO = DBBuilder.db.itemDao()
        val item = this.highTechItemDAO?.getByRef(itemRef)
        if (item != null) {
            item.availability = item.availability != true
            this.highTechItemDAO?.updateItem(item)
            val toMain = MainActivity.newIntent(this, INTENT_USER_MAIL.toString())
            startActivity(toMain)
        }

    }

    companion object {

        private var INTENT_USER_MAIL : String ?= null

        fun newIntent(context: Context, userMail: String): Intent {
            val intent = Intent(context, ItemScan::class.java)
            intent.putExtra(INTENT_USER_MAIL, userMail)
            this.INTENT_USER_MAIL = userMail
            return intent
        }
    }
}