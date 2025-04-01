package com.example.lb6

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lb6.databinding.ActivityMainBinding
import com.example.lb6.databinding.ActivitySecBinding

class SecActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivitySecBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        with(binding){
            btnSend.setOnClickListener {
                when {
                    edName.text.isEmpty() -> showToast("請輸入姓名")
                    edPhone.text.isEmpty() -> showToast("請輸入電話")
                    else -> {
                        val b =Bundle()
                        b.putString("name", edName.text.toString())
                        b.putString("phone", edPhone.text.toString())
                        setResult(Activity.RESULT_OK, Intent().putExtras(b))
                        finish()

                    }
                }
            }


        }


    }
    private  fun showToast(msg:String){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }
}