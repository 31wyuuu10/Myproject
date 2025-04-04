package com.example.lb6

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class MyAdapter (
    private val data: ArrayList<Contact>
) : RecyclerView.Adapter<MyAdapter.ViewHolder>(){

    class ViewHolder(v:View):RecyclerView.ViewHolder(v){
        private val tvName:TextView=v.findViewById(R.id.tvName)
        private val tvPhone:TextView=v.findViewById(R.id.tvPhone)
        private val imgDelete:ImageView=v.findViewById(R.id.imgDelete)
        fun bind(item: Contact, clickListener: (Contact)->Unit){
            tvName.text=item.name
            tvPhone.text=item.phone
            imgDelete.setOnClickListener{
                clickListener.invoke(item)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup,position: Int):ViewHolder{
        val v= LayoutInflater.from(viewGroup.context).inflate(R.layout.adapter_row,viewGroup,false)
        return  ViewHolder(v)
    }

    override fun getItemCount()=data.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position]) { item ->
            data.remove(item)
            notifyDataSetChanged()
        }
    }
}





