package com.example.lb6

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lb6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private  lateinit var  myAdapter: MyAdapter
    private  val contacts=ArrayList<Contact>()
    private  val startForResult=registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        result:ActivityResult->if(result.resultCode== Activity.RESULT_OK)
        {
            val intent =result.data
            val name=intent?.getStringExtra("name")?:""
            val phone=intent?.getStringExtra("phone")?:""
            Log.e("123","name:$name, phone:$phone")
            contacts.add(Contact(name,phone))
            myAdapter.notifyDataSetChanged()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val linearLayoutManager=LinearLayoutManager(this).apply {
            orientation=LinearLayoutManager.VERTICAL
        }

        binding.recyclerView.layoutManager = linearLayoutManager
        myAdapter= MyAdapter(contacts)
        with(binding) {
            recyclerView.adapter=myAdapter
            btnAdd.setOnClickListener {
                val i= Intent(this@MainActivity,SecActivity::class.java)
                startForResult.launch(i)

            }
        }



    }
}