package be.heh.projectcodeqr_wanntad





import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import be.heh.projectcodeqr_wanntad.database.AppDatabase
import be.heh.projectcodeqr_wanntad.database.user.UserDAO
import be.heh.projectcodeqr_wanntad.database.user.UserEntity


class LoginActivity : AppCompatActivity() {

    // Déclaration des éléments de la vue
    private lateinit var username_mail: EditText
    private lateinit var password: EditText
    private lateinit var loginbtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Récupération des éléments de la vue à partir de leur ID
        username_mail = findViewById(R.id.username_mail)
        password = findViewById(R.id.user_password)
        loginbtn = findViewById(R.id.loginbtn)

        // Ajout d'un écouteur d'évènement au clic sur le bouton de connexion
        loginbtn.setOnClickListener(View.OnClickListener {
            onLoginClick()
        })

    }

    // Méthode appelée lorsque l'utilisateur clique sur le bouton de connexion
    private fun onLoginClick(){
        // Récupération des valeurs entrées dans les champs de texte pour le nom d'utilisateur/adresse électronique et le mot de passe
        val userMail = this.username_mail?.text.toString()
        val userPassword = this.password?.text.toString()
        val u1 = UserEntity(0, userMail, userPassword,false)
        // Vérification si les champs sont vides
        if(userMail == "" || userPassword == ""){
            // Affichage d'un message Toast si les champs sont vides
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_LONG).show()
        }else{
            // Récupération de la liste de tous les utilisateurs de la base de données
            val userList = DBBuilder.db.userDao().getAll()
            // Vérification si la liste n'est pas vide
            if (userList != null) {
                // Parcours de la liste d'utilisateurs
                for(user in userList){
                    // Vérification si l'adresse électronique de l'utilisateur correspond à celle entrée par l'utilisateur
                    if(user.email == userMail){
                        // Si l'adresse électronique correspond, vérification si le mot de passe est correct
                        // Vérifie si le mot de passe entré par l'utilisateur correspond au mot de passe enregistré
                        if(user.password == userPassword){
                            // Prépare l'intent qui va rediriger l'utilisateur vers la page principale
                            val toMain = Intent(this, MainActivity::class.java)
                            // Ajoute des informations sur l'utilisateur à l'intent sous forme d'extra
                            toMain.putExtra("USER_MAIL", user.email)
                            toMain.putExtra("USER_ADMIN", user.admin)
                            // Démarre l'activity de la page principale
                            startActivity(toMain)
                        }else{
                            // Affiche un message indiquant que le mot de passe est incorrect
                            Toast.makeText(this, "Mot de passe erroné!", Toast.LENGTH_LONG).show()
                        }

                    }
                }
            }

        }

    }


}
