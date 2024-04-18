package com.example.guess_number
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import android.widget.EditText
import android.widget.Button
import android.widget.Toast
import com.example.guess_number.databinding.ActivityMainBinding
import com.example.guess_number.GuessingGame
import java.util.*

/*class MainActivity : AppCompatActivity() {
    val Tag:String =MainActivity::class.java.simpleName
    private lateinit var handler: Handler
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        handler=Handler(Looper.getMainLooper())
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val textView = findViewById<TextView>(R.id.textView)
        val guess_button = findViewById<Button>(R.id.guess_button)
        val reset_button = findViewById<Button>(R.id.reset_button)
        val editText = findViewById<EditText>(R.id.editText)



        var validate_num:Int
        var secret : Int = Random().nextInt(100)+1
        guess_button.setOnClickListener {
            textView.text = editText.text
            validate_num=editText.text.toString().toInt()-secret
            var ans_str:String="你猜對了歐"


            if (validate_num > 0) {
                ans_str = "你猜得太大了，请再小一点。范围应在1到"+editText.text.toString()+"之间。"
            } else if (validate_num < 0) {
                ans_str = "你猜得太小了，请再大一点。范围应在"+editText.text.toString()+"到100之间。"
            }else{
                handler.postDelayed({
                    Toast.makeText(this,"6秒後的操作執行了!",Toast.LENGTH_SHORT).show()
                },6000)
                ans_str="我們在猜一次!!!"
                secret= Random().nextInt(100)+1
            }
        textView.text=ans_str
        }

        reset_button.setOnClickListener{
            secret= Random().nextInt(100)+1
            textView.text="我們在猜一次!!!"
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }
}
*/class MainActivity : AppCompatActivity() {
    private lateinit var handler: Handler
    private lateinit var binding: ActivityMainBinding
    private val game = GuessingGame()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handler = Handler(Looper.getMainLooper())

        binding.guessButton.setOnClickListener {
            val guessNum = binding.editText.text.toString().toInt()
            val resultText = game.guessNumber(guessNum)
            binding.textView2.text = game.getRangeText()
            binding.textView.text = resultText
            if (resultText == "猜對了") {
                handler.postDelayed({
                    Toast.makeText(this, "六秒後重置", Toast.LENGTH_SHORT).show()
                    game.resetGame()
                    binding.textView.text = "再猜一次"
                    binding.textView2.text = "下一回合"
                }, 6000)
            }
        }

        binding.resetButton.setOnClickListener {
            game.resetGame()
            binding.textView.text = "再猜一次"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }
}
