package com.example.lb13

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lb13.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val receiver=
        object  : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
               intent.extras?.let {
                    binding.tvMsg.text="${it.getString("msg")}"
                }
            }

        }
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

        with(binding){
            btnMusic.setOnClickListener {
                register("music")
            }
            btnNew.setOnClickListener {
                register("new")

            }
            btnSport.setOnClickListener {
                register("sport")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
    private fun register(channel: String){
            val intentFilter=IntentFilter(channel)
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.TIRAMISU){
                registerReceiver(receiver,intentFilter, RECEIVER_EXPORTED)
            }else{
                registerReceiver(receiver,intentFilter)

            }
            val i = Intent(this,MyService::class.java)
            startService(i.putExtra("channel",channel))

    }
}