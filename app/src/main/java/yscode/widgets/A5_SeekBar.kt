package yscode.widgets

import android.media.AudioManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.AbsSeekBar
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView

class A5_SeekBar : AppCompatActivity() {
    private lateinit var params:WindowManager.LayoutParams
    private var brightness:Float = 0.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a5_seek_bar)

        //화면 정보 불러오기
        params = window.attributes

        val seekBarTitle = findViewById<TextView>(R.id.seekBarTitle)
        seekBarTitle.text = "음량, 화면 밝기 조절"

        val volumeNumber = findViewById<TextView>(R.id.volumeNumber)
        val volumeSeekBar = findViewById<SeekBar>(R.id.volumeSeekBar)
        val screenBrightNumber = findViewById<TextView>(R.id.screenBrightNumber)
        val screenBrightSeekBar = findViewById<SeekBar>(R.id.screenBrightSeekBar)

        volumeNumber.text = "${volumeSeekBar.progress}"
        volumeSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                controlVolume(progress)
                volumeNumber.text = "${progress}"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) { }
            override fun onStopTrackingTouch(seekBar: SeekBar?) { }
        })

        screenBrightNumber.text = "${screenBrightSeekBar.progress}"
        screenBrightSeekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            //SeekBar 상태가 변경될 때 호출
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                setBrightness(progress)
                screenBrightNumber.text = "${progress}"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) { }    //SeekBar에 손이 닿았을 때
            override fun onStopTrackingTouch(seekBar: SeekBar?) { }     //SeekBar 에서 손을 뗐을 때
        })
    }

    //음량 조절
    private fun controlVolume(progress: Int) {
        val volumeValue = progress.toFloat() / 100.0f
        val mAudio = getSystemService(AUDIO_SERVICE) as AudioManager
        mAudio.setStreamVolume(AudioManager.STREAM_RING,
                (mAudio.getStreamMaxVolume(AudioManager.STREAM_RING)* volumeValue).toInt(),
                AudioManager.FLAG_PLAY_SOUND)
    }

    //화면 밝기 조절
    private fun setBrightness(progress: Int) {
        val brightnessValue = progress.toFloat()/10.0f
        Log.d("SeekBar", "progress : ${progress} 밝기 값 : ${brightnessValue}")
        params.screenBrightness = brightnessValue;      //최대 밝기로 설정
        window.attributes = params                      //밝기 설정 적용
    }

    override fun onResume() {
        super.onResume()
        brightness = params.screenBrightness;           //기존 밝기 저장
    }

    override fun onPause() {
        super.onPause()
        //기존 밝기로 변경
        params.screenBrightness = brightness
        window.attributes = params
    }
}