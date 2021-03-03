package yscode.widgets

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class A3_Toggle_Switch : AppCompatActivity() {
    private lateinit var alarmToggleBtn:ToggleButton
    private lateinit var darkmodeToggleBtn:ToggleButton
    private lateinit var back:ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a3_toggle_switch)

        val toggle_switchTitle = findViewById<TextView>(R.id.toggle_switchTitle)
        toggle_switchTitle.text = "환경설정"

        //뷰 연결
        back = findViewById<ConstraintLayout>(R.id.back)

        val toggleListener = CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                when (buttonView.id) {
                    R.id.alarmToggleBtn -> alarmOn(true)
                    R.id.darkModeToggleBtn -> darkModeOn(true)
                    R.id.powerSavingSwitch -> powerSavingOn(true)
                }
            }
            else {
                when (buttonView.id) {
                    R.id.alarmToggleBtn -> alarmOn(false)
                    R.id.darkModeToggleBtn -> darkModeOn(false)
                    R.id.powerSavingSwitch -> powerSavingOn(false)
                }
            }
        }

        alarmToggleBtn = findViewById<ToggleButton>(R.id.alarmToggleBtn)
        darkmodeToggleBtn = findViewById<ToggleButton>(R.id.darkModeToggleBtn)
        val powerSavingSwitch = findViewById<Switch>(R.id.powerSavingSwitch)

        alarmToggleBtn.setOnCheckedChangeListener(toggleListener)
        darkmodeToggleBtn.setOnCheckedChangeListener(toggleListener)
        powerSavingSwitch.setOnCheckedChangeListener(toggleListener)

        //동적 Chip 추가, 삭제
        val chipGroup = findViewById<ChipGroup>(R.id.chipGroup)
        for (i in 0..5) {
            //Chip 인스턴스 생성
            val chip = Chip(this)
            chip.text = "Chip ${i+1}"
            chip.isCheckable = true     //체크 여부
            chip.closeIcon = getDrawable(R.drawable.ic_close_24)        //아이콘 이미지 지정
            chip.isCloseIconVisible = true      //아이콘 표시 여부

            chipGroup.addView(chip)

            chip.setOnClickListener {
                Toast.makeText(this, "Check ${i+1}", Toast.LENGTH_SHORT).show()
            }

            chip.setOnCloseIconClickListener {
                chipGroup.removeView(it)
            }
        }
    }

    //알람 설정
    private fun alarmOn(isOn:Boolean) {
        var alarmResult = findViewById<TextView>(R.id.alarmResult)

        if (isOn) {
            alarmToggleBtn.isChecked = true
            alarmResult.text = "알람이 켜졌습니다."
            alarmResult.setTextColor(Color.GREEN)
        }
        else {
            alarmToggleBtn.isChecked = false
            alarmResult.text = "알람이 꺼졌습니다."
            alarmResult.setTextColor(Color.BLACK)
        }
    }

    //다크모드 설정
    private fun darkModeOn(isOn: Boolean) {
        var darkmodeResult = findViewById<TextView>(R.id.darkModeResult)

        if (isOn) {
            darkmodeToggleBtn.isChecked = true
            darkmodeResult.text = "지금은 다크모드입니다."
            darkmodeResult.setTextColor(Color.WHITE)
            back.setBackgroundColor(Color.BLACK)
        }
        else {
            darkmodeToggleBtn.isChecked = false
            darkmodeResult.text = "지금은 라이트 모드입니다."
            darkmodeResult.setTextColor(Color.BLACK)
            back.setBackgroundColor(Color.WHITE)
        }
    }

    //절전모드 설정
    private fun powerSavingOn(isOn: Boolean) {
        if (isOn) {
            back.setBackgroundColor(Color.LTGRAY)
        }
        else {
            back.setBackgroundColor(Color.WHITE)
        }
    }
}