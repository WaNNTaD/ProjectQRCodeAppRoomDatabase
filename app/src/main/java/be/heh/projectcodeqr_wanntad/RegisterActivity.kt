package be.heh.projectcodeqr_wanntad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import be.heh.projectcodeqr_wanntad.database.user.UserEntity

// Activité pour enregistrer un nouvel utilisateur
class RegisterActivity : AppCompatActivity() {
    // Champs de formulaire pour saisir le nom d'utilisateur/email, le mot de passe et la vérification du mot de passe
    private lateinit var username_mail: EditText
    private lateinit var password: EditText
    private lateinit var repassword: EditText
    // Bouton pour enregistrer les informations de l'utilisateur
    private lateinit var registerbtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Récupère les vues du layout
        username_mail = findViewById(R.id.username)
        password = findViewById(R.id.password)
        repassword = findViewById(R.id.repassword)
        registerbtn = findViewById(R.id.registerbtn)

        // Définit un listener pour le clic sur le bouton d'enregistrement
        registerbtn.setOnClickListener(View.OnClickListener {
            onRegisterClick()
        })

    }

    // Méthode appelée lorsque l'utilisateur clique sur le bouton d'enregistrement
    private fun onRegisterClick(){
        // Récupère les valeurs saisies par l'utilisateur
        val user = username_mail.text.toString()
        val pass = password.text.toString()
        val repass = repassword.text.toString()

        // Vérifie si tous les champs ont été remplis
        if (user.isEmpty() || pass.isEmpty() || repass.isEmpty()) {
            Toast.makeText(this@RegisterActivity, "Remplissez tous les champs.", Toast.LENGTH_SHORT).show()
        } else {
            // Vérifie si le mot de passe et sa vérification correspondent
            if (pass != repass) {
                Toast.makeText(this@RegisterActivity, "La verification de mot de passe a échoué, réessayez", Toast.LENGTH_SHORT).show()
            } else {
                // Enregistre les informations de l'utilisateur dans la base de données
                Toast.makeText(this@RegisterActivity, "Information enregistrée avec succès", Toast.LENGTH_SHORT).show()
                DBBuilder.db.userDao().insertUser(UserEntity(0,user,pass,false))
                // Redirige vers l'activité de bienvenue
                val intent = Intent(applicationContext, WelcomeActivity::class.java)
                startActivity(intent)

            }
        }
    }
}
