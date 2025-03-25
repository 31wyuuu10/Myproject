package com.example.lb2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lb2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val startForResult=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result: ActivityResult-> if(result.resultCode== Activity.RESULT_OK){
            val intent=result.data
            val drink=intent?.getStringExtra("drink")
            val sugar=intent?.getStringExtra("sugar")
            val ice=intent?.getStringExtra("ice")
            val tvMeal=findViewById<TextView>(R.id.tvMeal)
            tvMeal.text="飲料: $drink\n\n甜度: $sugar\n\n冰塊: $ice"
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }




        binding.btnChoice.setOnClickListener { par1 ->
            val intent= Intent(this,SecActivity::class.java)
            startForResult.launch(intent)
        }

    }
}