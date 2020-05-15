package com.example.restores

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_perusahaan_beranda.view.*

class BerandaAdapter(val context: Context, val slideList: ArrayList<Beranda>) :
    RecyclerView.Adapter<BerandaAdapter.ViewHolder>()  {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewNama = itemView.findViewById(R.id.textViewNama) as TextView
       val alamat_resto = itemView.findViewById(R.id.alamat_view) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.list_perusahaan_beranda, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return slideList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user: Beranda = slideList[position]
        holder.textViewNama.text = user.nama_resto
        holder.alamat_resto.text = user.alamat


        holder.itemView.viewresto.setOnClickListener {
            val i = Intent(context,ViewActivity::class.java)
            i.putExtra("nama_resto", slideList?.get(position)?.nama_resto)
            i.putExtra("alamat", slideList?.get(position)?.alamat)

            context.startActivity(i)
        }
    }

}