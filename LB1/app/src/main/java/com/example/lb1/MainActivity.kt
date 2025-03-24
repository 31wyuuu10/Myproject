package com.example.lb1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val edName =findViewById<EditText>(R.id.editTextText3)
        val tvText =findViewById<TextView>(R.id.textView5)
        val radioGroup=findViewById<RadioGroup>(R.id.radioGroup)
        val btnMora=findViewById<Button>(R.id.button4)
        val tvName=findViewById<TextView>(R.id.textView6)
        val tvWinner=findViewById<TextView>(R.id.textView7)
        val tvMymora=findViewById<TextView>(R.id.textView8)
        val tvTargetMora=findViewById<TextView>(R.id.textView9)

        btnMora.setOnClickListener {
            if(edName.text.isEmpty()){
                tvText.text="請輸入玩家姓名"
                return@setOnClickListener
             }
            val playerName=edName.text.toString()
            val targetMora =(0..2).random()
            val myMora= when(radioGroup.checkedRadioButtonId){
                R.id.radioButton4 ->0
                R.id.radioButton5 ->1
                else ->2
            }
            tvName.text="名字\n$playerName"
            tvMymora.text="我方出拳\n${getMoraString(myMora)}"
            tvTargetMora.text="電腦出拳\n${getMoraString(targetMora)}"
            when{
                myMora==targetMora-> {
                    tvWinner.text="勝利者\n平手"
                    tvText.text="平局，請再試一次!"
                }
                (myMora==0 && targetMora==2)||(myMora==1&&targetMora==0)||(myMora==2&&targetMora==1)->{
                    tvWinner.text="勝利者\n$playerName"
                    tvText.text="恭喜你獲勝了!"

                }
                else->{
                    tvWinner.text="勝利者\n電腦"
                    tvText.text="可惜，電腦獲勝了!"

                }

            }

        }
    }
    private fun getMoraString(mora: Int):String{
        return when (mora){
            0->"剪刀"
            1->"石頭"
            else->"布"
        }

    }
}