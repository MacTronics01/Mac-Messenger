package com.example.macmessenger

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.auth.FirebaseAuth
import com.wang.avi.AVLoadingIndicatorView
import de.hdodenhof.circleimageview.CircleImageView

class LoginActivity : AppCompatActivity() {

    lateinit var login_button_login:Button
    lateinit var kotlinImageView:ImageView
    lateinit var back_to_register_textview:TextView
    lateinit var email_edittext_login:EditText
    lateinit var password_edittext_login:EditText
    lateinit var loading_view:AVLoadingIndicatorView

    private val TAG = LoginActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.abs_layout)

        login_button_login=findViewById(R.id.login_button_login)
        kotlinImageView=findViewById(R.id.kotlinImageView)
        back_to_register_textview=findViewById(R.id.back_to_register_textview)
        email_edittext_login=findViewById(R.id.email_edittext_login)
        password_edittext_login=findViewById(R.id.password_edittext_login)
        loading_view=findViewById(R.id.loading_view)



        Glide.with(this).asGif()
                .load("https://media1.tenor.com/images/1d550cc7494b9ac5a85fbe4f6bc184c8/tenor.gif?itemid=11525834")
                .apply(RequestOptions.circleCropTransform())
                .into(kotlinImageView)

        login_button_login.setOnClickListener {
            performLogin()
        }

        back_to_register_textview.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left)
    }

    private fun performLogin() {
        val email = email_edittext_login.text.toString()
        val password = password_edittext_login.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            return
        }

        back_to_register_textview.visibility = View.GONE
        loading_view.visibility = View.VISIBLE

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (!it.isSuccessful) return@addOnCompleteListener
                    Log.d(TAG, "Successfully logged in: ${it.result!!.user?.uid}")

                    val intent = Intent(this, LatestMessagesActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    overridePendingTransition(R.anim.enter, R.anim.exist)
                }
                .addOnFailureListener {
                    Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()

                    back_to_register_textview.visibility = View.VISIBLE
                    loading_view.visibility = View.GONE
                }
    }
}