package be.heh.projectcodeqr_wanntad

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.widget.ListView
import be.heh.projectcodeqr_wanntad.database.user.UserDAO
import be.heh.projectcodeqr_wanntad.database.user.UserEntity
import be.heh.projectcodeqr_wanntad.userpage.UserListAdapter


class UserList : Activity() {
    private lateinit var userList: List<UserEntity>
    private lateinit var userListView : ListView
    private var dao: UserDAO?=null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        userListView = findViewById(R.id.user_list_view)
        dao = DBBuilder.db.userDao()
        userList = dao?.getAll()!!
        val adapter = UserListAdapter(this, userList as ArrayList<UserEntity>)
        userListView.adapter = adapter

    }
}