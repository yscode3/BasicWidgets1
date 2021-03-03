package yscode.widgets

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import kotlin.concurrent.thread

class A4_ProgressBar : AppCompatActivity() {
    private lateinit var imgArea:LinearLayout
    private lateinit var loadingArea:LinearLayout
    private lateinit var progressBarTitle:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a4_progress_bar)

        progressBarTitle = findViewById<TextView>(R.id.progressBarTitle)
        progressBarTitle.text = "게임 정보 불러오기"

        imgArea = findViewById<LinearLayout>(R.id.imgArea)
        loadingArea = findViewById<LinearLayout>(R.id.loadingArea)

        showProgress(true)
        thread(start = true) {
            Thread.sleep(2000)

            runOnUiThread {
                showProgress(false)
                setPicture()
            }
        }
    }

    //프로그래스 바 보이기 / 숨기기
    fun showProgress(isShow:Boolean) {
        if (isShow) {
            imgArea.visibility = View.INVISIBLE
            loadingArea.visibility = View.VISIBLE
        }
        else {
            imgArea.visibility = View.VISIBLE
            progressBarTitle.text = "창세기전3"
            loadingArea.visibility = View.INVISIBLE
        }
    }

    //프로그래스바 완료 후, 이미지 로딩
    private fun setPicture() {
        val theGameImg = findViewById<ImageView>(R.id.theGameImg)
        theGameImg.setImageResource(R.drawable.war_of_genesis)
    }
}