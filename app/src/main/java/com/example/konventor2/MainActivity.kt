package com.example.konventor2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.math.BigDecimal
import java.math.RoundingMode
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        b_0.setOnClickListener { setTextFields("0") }
        b_1.setOnClickListener { setTextFields("1") }
        b_2.setOnClickListener { setTextFields("2") }
        b_3.setOnClickListener { setTextFields("3") }
        b_4.setOnClickListener { setTextFields("4") }
        b_5.setOnClickListener { setTextFields("5") }
        b_6.setOnClickListener { setTextFields("6") }
        b_7.setOnClickListener { setTextFields("7") }
        b_8.setOnClickListener { setTextFields("8") }
        b_9.setOnClickListener { setTextFields("9") }
        b_tochka.setOnClickListener { setTextFields(".") }

        var a = ""
        var b = ""
        var x = ""


        b_back.setOnClickListener {
            val str = math_operation.text.toString()
            if (str.isNotEmpty()) { math_operation.text = str.substring(0, str.length - 1) }
            result_text.text = ""
        }


        var str = ""

        b_tochka.setOnClickListener {
            var x = math_operation.text
            if (up.getSelectedItemPosition() == 0) {a="1"}
            else if (up.getSelectedItemPosition() == 1) {a="0.014"}
            else if (up.getSelectedItemPosition() == 2) {a="1.13"}
            else if (up.getSelectedItemPosition() == 3) {a="1.26"}

            if (down.getSelectedItemPosition() == 0) {b="1"}
            else if (down.getSelectedItemPosition() == 1) {b="69.12"}
            else if (down.getSelectedItemPosition() == 2) {b="0.89"}
            else if (down.getSelectedItemPosition() == 3) {b="0.79"}

            str=x.toString()+"*"+a+"*"+b

            try {
                val ex = ExpressionBuilder(str).build()
                val result = ex.evaluate()
                val decimal = BigDecimal(result).setScale(2, RoundingMode.HALF_EVEN)
                result_text.text= decimal.toString()
                val longRes =  result.toLong()
                if (result == longRes.toDouble()) {result_text.text = longRes.toString()}
                else {result_text.text = result.toString()}
            }catch (e: Exception) { Log.d("Ошибка", "сообщение: ${e.message}") }
        }
    }

    fun setTextFields(str: String) {
        if (result_text.text != "") {
            math_operation.text = result_text.text
            result_text.text = ""
        }
        math_operation.append(str)

    }

    object Global {

    }
}