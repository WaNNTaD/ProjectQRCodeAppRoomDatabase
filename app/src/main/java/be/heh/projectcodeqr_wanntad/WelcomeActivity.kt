package be.heh.projectcodeqr_wanntad

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.Button
import androidx.room.Room
import be.heh.projectcodeqr_wanntad.database.AppDatabase

// Activité de bienvenue affichée lorsque l'application est lancée
class WelcomeActivity : AppCompatActivity() {

    // Déclarer les vues de l'interface utilisateur en tant que variables membres
    // afin qu'elles puissent être utilisées dans toutes les méthodes de l'activité
    private lateinit var connectbtn: Button
    private lateinit var registerbtn: Button


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        // Récupère les vues du layout
        connectbtn = findViewById(R.id.button_login)
        registerbtn = findViewById(R.id.button_inscription)

        // Initialise la base de données
        DBBuilder.startDatabase(this)

        // Définit un listener pour le clic sur le bouton de connexion
        connectbtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                // Redirige vers l'activité de connexion
                val intent = Intent(applicationContext, LoginActivity::class.java)
                startActivity(intent)
            }
        })

        // Définit un listener pour le clic sur le bouton d'enregistrement
        registerbtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                // Redirige vers l'activité d'enregistrement
                val intent = Intent(applicationContext, RegisterActivity::class.java)
                startActivity(intent)
            }
        })
    }
}