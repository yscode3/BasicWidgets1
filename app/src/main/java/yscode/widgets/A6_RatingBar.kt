package yscode.widgets

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView

class A6_RatingBar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a6_rating_bar)

        val ratingBarTitle = findViewById<TextView>(R.id.ratingBarTitle)
        ratingBarTitle.text = "영화 평점 매기기"

        val movieImg = findViewById<ImageView>(R.id.movieImg)
        movieImg.setImageResource(R.drawable.movie)

        val ratingScore = findViewById<TextView>(R.id.ratingScore)
        val movieScoreRatingBar = findViewById<RatingBar>(R.id.movieScoreRatingBar)

        movieScoreRatingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            ratingScore.text = "${rating}점"
        }
    }
}