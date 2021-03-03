package yscode.widgets

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*

class A2_CheckBox : AppCompatActivity() {
    val deviceResult = ArrayList<String>()    //소유 기기 결과를 저장할 버퍼
    val gameResult = ArrayList<String>()     //선호하는 게임 결과를 저장할 버퍼

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a2_check_box)

        val checkBoxTitle = findViewById<TextView>(R.id.checkBoxTitle)
        checkBoxTitle.text = "게임 플레이 타임 설문조사"

        val pc = findViewById<CheckBox>(R.id.pc)
        val xbox = findViewById<CheckBox>(R.id.xbox)
        val playStation = findViewById<CheckBox>(R.id.playStation)

        val wow = findViewById<CheckBox>(R.id.wow)
        val warOfGenesis = findViewById<CheckBox>(R.id.warOfGenesis)
        val counterStrike = findViewById<CheckBox>(R.id.counterStrike)
        val superMario = findViewById<CheckBox>(R.id.superMario)
        val tekken = findViewById<CheckBox>(R.id.tekken)

        val btnOK = findViewById<Button>(R.id.btnOK)

        //체크 박스 리스너 설정
        val checkListener = CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                when (buttonView.id) {
                    R.id.pc -> setChosenDevices(pc)
                    R.id.xbox -> setChosenDevices(xbox)
                    R.id.playStation -> setChosenDevices(playStation)
                    R.id.wow -> setChosenGames("와우")
                    R.id.warOfGenesis -> setChosenGames("창세기전")
                    R.id.counterStrike -> setChosenGames("카운터 스트라이크")
                    R.id.superMario -> setChosenGames("슈퍼마리오")
                    R.id.tekken -> setChosenGames("철권")
                }
            } else {
                when (buttonView.id) {
                    R.id.pc -> unSetChosenDevices(pc)
                    R.id.xbox -> unSetChosenDevices(xbox)
                    R.id.playStation -> unSetChosenDevices(playStation)
                    R.id.wow -> unSetChosenGames("와우")
                    R.id.warOfGenesis -> unSetChosenGames("창세기전")
                    R.id.counterStrike -> unSetChosenGames("카운터 스트라이크")
                    R.id.superMario -> unSetChosenGames("슈퍼마리오")
                    R.id.tekken -> unSetChosenGames("철권")
                }
            }
        }

        //각 뷰에 리스너 연결
        pc.setOnCheckedChangeListener(checkListener)
        xbox.setOnCheckedChangeListener(checkListener)
        playStation.setOnCheckedChangeListener(checkListener)

        wow.setOnCheckedChangeListener(checkListener)
        warOfGenesis.setOnCheckedChangeListener(checkListener)
        counterStrike.setOnCheckedChangeListener(checkListener)
        superMario.setOnCheckedChangeListener(checkListener)
        tekken.setOnCheckedChangeListener(checkListener)

        //확인 버튼
        btnOK.setOnClickListener {
            Toast.makeText(this, "참여에 감사드립니다", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    //목록에 게임기기 추가
    private fun setChosenDevices(device: CheckBox) {
        deviceResult.add(device.text.toString())
        showDevices()
    }

    //목록에 게임 추가
    private fun setChosenGames(game: String) {
        gameResult.add(game)
        showGames()
    }

    /**
     * 리스트 순회 중 item 삭제 후, ConcurrentModificationException 발생
     * 원인 : Enhansed for loop 의 동작방식에서 찾을 수 있음
     *      리스트에서 item 을 삭제하는 등 데이터 조작을 하면,
     *      내부적으로 2개의 클래스 변수로 리스트의 데이터 변경 여부를 체크하는데 데이터 조작으로 인해 일치하지 않아 오류를 발생한다.
     * 해결 : 마지막까지 순회하지 않도록 break 로 탈출.
     *      단, 1개 이상의 item 을 제거하면 문제 발생
     * 참조 https://m.blog.naver.com/tmondev/220393974518
     */
    //목록에서 게임기기 제거
    private fun unSetChosenDevices(device: CheckBox) {
        for (d in deviceResult) {
            Log.d("Check", "${d}")
            if (d == device.text.toString()) {
                deviceResult.remove(d)
                break
            }
        }

        showDevices()
    }

    //목록에서 게임 제거
    private fun unSetChosenGames(game: String) {
        for (g in gameResult) {
            Log.d("Check", "${g}")
            if (g == game) {
                gameResult.remove(g)
                break
            }
        }

        showGames()
    }

    //소유 기기 결과 출력
    private fun showDevices() {
        val chosenDevice = findViewById<TextView>(R.id.chosenDevice)
        val str = StringBuffer()
        for (d in deviceResult) {
            str.append("${d}\n")
        }
        chosenDevice.text = str.toString()
    }

    //선호하는 게임 결과 출력
    private fun showGames() {
        val chosenGame = findViewById<TextView>(R.id.chosenGame)
        val str = StringBuffer()
        for (g in gameResult) {
            str.append("${g}\n")
        }
        chosenGame.text = str.toString()
    }
}