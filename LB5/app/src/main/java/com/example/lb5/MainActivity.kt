package com.example.lb5

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lb5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.spinner
        binding.listView
        binding.gridView
        val count=ArrayList<String>()
        val item=ArrayList<Item>()
        val priceRange=IntRange(10,100)
        val array =resources.obtainTypedArray(R.array.img_list)
        for(index in 0 until array.length()){
            val photo=array.getResourceId(index,0)
            val name ="水果${index+1}"
            val price=priceRange.random()
            count.add("${index+1}個")
            item.add(Item(photo,name,price))

        }
        array.recycle()
        binding.spinner.adapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,count)
        binding.gridView.adapter=MyAdapter(this,item,R.layout.adpater_vertical)
        binding.listView.adapter=MyAdapter(this,item,R.layout.adapter_horizontal)
    }
}