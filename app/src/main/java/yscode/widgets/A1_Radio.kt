package yscode.widgets

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast

class A1_Radio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a1_radio)

        //뷰 얀결
        val radioTitle = findViewById<TextView>(R.id.radioTitle)
        radioTitle.text = "스타벅스 설문조사"

        val coffeeRadioGroup = findViewById<RadioGroup>(R.id.coffeeRadioGroup)
        val dessertRadioGroup = findViewById<RadioGroup>(R.id.dessertRadioGroup)
        val btnOK = findViewById<Button>(R.id.btnOK)

        //커피 선택
        coffeeRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
                R.id.americano -> setChosenCoffe(findViewById<TextView>(R.id.americano).text)
                R.id.espresso -> setChosenCoffe(findViewById<TextView>(R.id.espresso).text)
                R.id.cafeRatte -> setChosenCoffe(findViewById<TextView>(R.id.cafeRatte).text)
                R.id.caramelMacchiato -> setChosenCoffe(findViewById<TextView>(R.id.caramelMacchiato).text)
                R.id.coldBrew -> setChosenCoffe(findViewById<TextView>(R.id.coldBrew).text)
            }
        }

        //디저트류 선택
        dessertRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
                R.id.kindOfCake -> setChosenDessert(findViewById<TextView>(R.id.kindOfCake).text)
                R.id.kindOfSandWich -> setChosenDessert(findViewById<TextView>(R.id.kindOfSandWich).text)
                R.id.kindOfCookie -> setChosenDessert(findViewById<TextView>(R.id.kindOfCookie).text)
            }
        }

        //확인 버튼
        btnOK.setOnClickListener {
            Toast.makeText(this, "참여에 감사드립니다", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    //선택한 커피 결과 출력
    private fun setChosenCoffe(coffee: CharSequence?) {
        val chosenCoffee = findViewById<TextView>(R.id.chosenCoffee)
        chosenCoffee.text = coffee
    }

    //선택한 디저트류 결과 출력
    private fun setChosenDessert(dessert: CharSequence?) {
        val chosenDessert = findViewById<TextView>(R.id.chosenDessert)
        chosenDessert.text = dessert
    }
}