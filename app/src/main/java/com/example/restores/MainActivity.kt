package com.example.restores

import android.content.Context
import android.content.Intent
import android.icu.text.DateFormat.MEDIUM
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.RenderScript
import android.util.Log
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.password
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences = getSharedPreferences("CEKLOGIN", Context.MODE_PRIVATE)
        val stat = sharedPreferences.getString("ROLE", "")

        if (stat=="customer"){
            startActivity(Intent(this@MainActivity,BerandaActivity::class.java))
            finish()
        }
        else {
            btn_login.setOnClickListener {
                var username = username1.text.toString()
                var pass = password.text.toString()
                postkerserver(username, pass)
            }

        }
        btn_daftar.setOnClickListener {
            val intent = Intent(context, RegisterActivity::class.java)

            startActivity(intent)
        }
    }


    private fun postkerserver(data1: String, data2: String) {
        AndroidNetworking.post(API.LOGIN)
            .addBodyParameter("username", data1)
            .addBodyParameter("pass", data2)
            .setPriority(Priority.MEDIUM).build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {

                    val jsonArray = response.getJSONArray("result")
                    if (jsonArray.length() == 0) {
                        Log.e("_kotlinTitle", "null")
                        Toast.makeText(applicationContext, "Error NJING", Toast.LENGTH_LONG).show()
                    }
                    for (i in 0 until jsonArray.length()) {

                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("_kotlinTitle", jsonObject.optString("username"))
                        Log.e("_kotlinTitle", jsonObject.optString("pass"))
                        Log.e("_kotlinTitle", jsonObject.optString("role"))
                        var rolelogin= jsonObject.optString("role")



                        if (rolelogin == "customer") {
                            val sharedPreferences =
                                getSharedPreferences("CEKLOGIN", Context.MODE_PRIVATE)
                            val editor = sharedPreferences.edit()
                            editor.putString("ROLE", rolelogin)
                            editor.apply()
                            startActivity(Intent(this@MainActivity, BerandaActivity::class.java))
                            finish()
                        }
                        else if (rolelogin == "admin") {
                            Toast.makeText(applicationContext, "Login lewat WEB COK!", Toast.LENGTH_LONG).show()
                        }
                    }
                }

                override fun onError(error: ANError) { // handle error
                }
            })
    }

}
