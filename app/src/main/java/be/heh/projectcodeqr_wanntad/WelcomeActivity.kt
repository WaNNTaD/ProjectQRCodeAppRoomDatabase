package be.heh.projectcodeqr_wanntad

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.Button
import androidx.room.Room
import be.heh.projectcodeqr_wanntad.database.AppDatabase


class WelcomeActivity : AppCompatActivity() {

    // Déclarer les vues de l'interface utilisateur en tant que variables membres
    // afin qu'elles puissent être utilisées dans toutes les méthodes de l'activité
    private lateinit var connectbtn: Button
    private lateinit var registerbtn: Button


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)


        connectbtn = findViewById(R.id.button_login)
        registerbtn = findViewById(R.id.button_inscription)


        DBBuilder.startDatabase(this)

        connectbtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                val intent = Intent(applicationContext, LoginActivity::class.java)
                startActivity(intent)
            }
        })

        registerbtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                val intent = Intent(applicationContext, RegisterActivity::class.java)
                startActivity(intent)
            }
        })
    }
}