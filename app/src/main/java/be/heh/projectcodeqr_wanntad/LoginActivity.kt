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

    private lateinit var username_mail: EditText
    private lateinit var password: EditText
    private lateinit var loginbtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        username_mail = findViewById(R.id.username_mail)
        password = findViewById(R.id.user_password)
        loginbtn = findViewById(R.id.loginbtn)

        loginbtn.setOnClickListener(View.OnClickListener {
            onLoginClick()
        })

    }

    private fun onLoginClick(){
        val userMail = this.username_mail?.text.toString()
        val userPassword = this.password?.text.toString()
        val u1 = UserEntity(0, userMail, userPassword,false)
        if(userMail == "" || userPassword == ""){
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_LONG).show()
        }else{

            val userList = DBBuilder.db.userDao().getAll()

            if (userList != null) {
                for(user in userList){
                    if(user.email == userMail){
                        if(user.password == userPassword){
                            val toMain = Intent(this, MainActivity::class.java)
                            toMain.putExtra("USER_MAIL", user.email)
                            toMain.putExtra("USER_ADMIN", user.admin)
                            startActivity(toMain)
                        }else{
                            Toast.makeText(this, "Mot de passe erroné!", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }

        }

    }


}
