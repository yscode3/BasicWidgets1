package yscode.widgets

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        //기본 인텐트
        //액티비티 전환

        //View 연결
        val btnRadio = findViewById<Button>(R.id.btnRadio)
        val btnCheckBox = findViewById<Button>(R.id.btnCheckBox)
        val btnToggleSwitch = findViewById<Button>(R.id.btnToggleSwitch)
        val btnProgressBar = findViewById<Button>(R.id.btnProgressBar)
        val btnSeekBar = findViewById<Button>(R.id.btnSeekBar)
        val btnRatingBar = findViewById<Button>(R.id.btnRatingBar)

        //각 메뉴로 이동
        btnRadio.setOnClickListener { startActivity(Intent(this, A1_Radio::class.java)) }
        btnCheckBox.setOnClickListener { startActivity(Intent(this, A2_CheckBox::class.java)) }
        btnToggleSwitch.setOnClickListener { startActivity(Intent(this, A3_Toggle_Switch::class.java)) }
        btnProgressBar.setOnClickListener { startActivity(Intent(this, A4_ProgressBar::class.java)) }
        btnSeekBar.setOnClickListener { startActivity(Intent(this, A5_SeekBar::class.java)) }
        btnRatingBar.setOnClickListener { startActivity(Intent(this, A6_RatingBar::class.java)) }
    }
}