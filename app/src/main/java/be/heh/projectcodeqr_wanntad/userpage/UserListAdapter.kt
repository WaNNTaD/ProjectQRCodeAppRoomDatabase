package be.heh.projectcodeqr_wanntad.userpage

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import be.heh.projectcodeqr_wanntad.R
import be.heh.projectcodeqr_wanntad.database.user.UserEntity

class UserListAdapter(
    private val context: Activity,
    private val arrayList: ArrayList<UserEntity>
) : ArrayAdapter<UserEntity>(context, R.layout.custom_user_layout, arrayList) {

    @SuppressLint("MissingInflatedId")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.custom_user_layout, null)

        val textEmail: TextView = view.findViewById(R.id.text_email)
        val textRole: TextView = view.findViewById(R.id.text_role)

        textEmail.text = arrayList[position].email
        textRole.text = if (arrayList[position].admin) "Admin" else "Non-admin"

        return view
    }
}