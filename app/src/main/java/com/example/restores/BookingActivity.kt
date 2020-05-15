package com.example.restores

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import kotlinx.android.synthetic.main.activity_booking.*
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONArray

class BookingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)
        booking.setOnClickListener {
            val tanggal :String = tanggal.text.toString()
            val jam :String = jam.text.toString()
            val jumlah :String = jumlah.text.toString()
            val nama :String = nama.text.toString()

            postkeserver(tanggal, jam, jumlah, nama )
            Log.i("result", tanggal+jam+jumlah+nama)
            startActivity(Intent(this, BuktiActivity::class.java))


        }

    }
    fun postkeserver(data1:String, data2:String, data3:String, data4:String)
    {
        val loading = ProgressDialog(this)
        loading.setMessage("Menambahkan data...")
        loading.show()

        AndroidNetworking.post(API.BOKING)
            .addBodyParameter("tanggal", data1)
            .addBodyParameter("jam", data2)
            .addBodyParameter("jumlah", data3)
            .addBodyParameter("nama", data4)

            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener {
                override fun onResponse(response: JSONArray) {

                }

                override fun onError(anError: ANError?) {
                    loading.dismiss()
                    Log.d("ONERROR",anError?.errorDetail?.toString())
                    Toast.makeText(applicationContext,"Connection Failure", Toast.LENGTH_SHORT).show()
                }
            })
    }
}
