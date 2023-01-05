package be.heh.projectcodeqr_wanntad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import be.heh.projectcodeqr_wanntad.database.user.UserEntity

class RegisterActivity : AppCompatActivity() {
    private lateinit var username_mail: EditText
    private lateinit var password: EditText
    private lateinit var repassword: EditText
    private lateinit var registerbtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        username_mail = findViewById(R.id.username)
        password = findViewById(R.id.password)
        repassword = findViewById(R.id.repassword)
        registerbtn = findViewById(R.id.registerbtn)

        registerbtn.setOnClickListener(View.OnClickListener {
            onRegisterClick()
        })

    }

    private fun onRegisterClick(){
        val user = username_mail.text.toString()
        val pass = password.text.toString()
        val repass = repassword.text.toString()

        if (user.isEmpty() || pass.isEmpty() || repass.isEmpty()) {
            Toast.makeText(this@RegisterActivity, "Remplissez tous les champs.", Toast.LENGTH_SHORT).show()
        } else {
            if (pass != repass) {
                Toast.makeText(this@RegisterActivity, "La verification de mot de passe a échoué, réessayez", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@RegisterActivity, "Information enregistrée avec succès", Toast.LENGTH_SHORT).show()
                DBBuilder.db.userDao().insertUser(UserEntity(0,user,pass,false))
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)

            }
        }
    }
}