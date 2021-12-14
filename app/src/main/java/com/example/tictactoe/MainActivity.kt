package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.gridlayout.widget.GridLayout

class MainActivity : AppCompatActivity() {
    // Array for the block track
    var position = intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2)

    // Array for all the winning position
    var winningPositions = arrayOf(
        intArrayOf(0, 1, 2),
        intArrayOf(3, 4, 5),
        intArrayOf(6, 7, 8),
        intArrayOf(0, 3, 6),
        intArrayOf(1, 4, 7),
        intArrayOf(2, 5, 8),
        intArrayOf(0, 4, 8),
        intArrayOf(2, 4, 6)
    )

    // Used as the active player
    var active = 0

    // To check to play or not
    var activeGame = true

    //----------------------------------------------------------------------------------------------//
    // Game main logic
    fun click(view: View) {
        val iv = view as ImageView
        val tagPosition = iv.tag.toString().toInt()
        var winner = ""
        if (position[tagPosition] == 2 && activeGame) {
            position[tagPosition] = active
            iv.animate().alpha(1f).duration = 500

            // 0:Captain America, 1:Iron Man, 2:Empty
            active = if (active == 0) {
                iv.setImageResource(R.drawable.captainamerica)
                1
            } else {
                iv.setImageResource(R.drawable.ironman)
                0
            }
            for (winningPosition in winningPositions) {
                if (position[winningPosition[0]] == position[winningPosition[1]] && position[winningPosition[1]] == position[winningPosition[2]] && position[winningPosition[0]] != 2) {
                    activeGame = false
                    if (active == 1) {
                        winner = "Winner is Captain America"
                        gameFinish(winner)
                    } else if (active == 0) {
                        winner = "Winner is Iron Man"
                        gameFinish(winner)
                    }
                } else if (isTied
                    && position[winningPosition[0]] != position[winningPosition[1]] && position[winningPosition[1]] != position[winningPosition[2]]
                ) {
                    winner = "Draw Match"
                    gameFinish(winner)
                }
            }
        }
    }

    //----------------------------------------------------------------------------------------------//
    // Play again logic
    fun playAgain(view: View?) {
        val tvResult = findViewById<View>(R.id.tvResult) as TextView
        val btnPlayAgain = findViewById<View>(R.id.btnPlayAgain) as Button
        tvResult.visibility = View.INVISIBLE
        btnPlayAgain.visibility = View.INVISIBLE
        val gridLayout = findViewById<View>(R.id.gridLayout) as GridLayout
        for (i in 0 until gridLayout.childCount) {
            val imageView = gridLayout.getChildAt(i) as ImageView
            imageView.animate().alpha(0f).duration = 500
            imageView.setImageDrawable(null)
        }
        for (i in position.indices) {
            position[i] = 2
        }
        activeGame = true
        active = 0
    }

    //----------------------------------------------------------------------------------------------//
    // Game over logic
    fun gameFinish(winner: String?) {
        val tvResult = findViewById<View>(R.id.tvResult) as TextView
        val btnPlayAgain = findViewById<View>(R.id.btnPlayAgain) as Button
        tvResult.visibility = View.VISIBLE
        btnPlayAgain.visibility = View.VISIBLE
        tvResult.text = winner
    }

    //----------------------------------------------------------------------------------------------//
    // Game draw logic
    val isTied: Boolean
        get() {
            for (i in position.indices) {
                if (position[i] == 2) {
                    return false
                }
            }
            return true
        }

    //----------------------------------------------------------------------------------------------//
    // Create function blank
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}