package com.tarento.tictactoeapp

import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.tarento.tictactoeapp.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btn1.setOnClickListener(this)
        binding.btn2.setOnClickListener(this)
        binding.btn3.setOnClickListener(this)
        binding.btn4.setOnClickListener(this)
        binding.btn5.setOnClickListener(this)
        binding.btn6.setOnClickListener(this)
        binding.btn7.setOnClickListener(this)
        binding.btn8.setOnClickListener(this)
        binding.btn9.setOnClickListener(this)


    }

    override fun onClick(view: View?) {
        val btnSelected = view as Button
        var cellId = 0

        when (btnSelected.id) {
            R.id.btn1 -> cellId = 1
            R.id.btn2 -> cellId = 2
            R.id.btn3 -> cellId = 3
            R.id.btn4 -> cellId = 4
            R.id.btn5 -> cellId = 5
            R.id.btn6 -> cellId = 6
            R.id.btn7 -> cellId = 7
            R.id.btn8 -> cellId = 8
            R.id.btn9 -> cellId = 9
        }

        playGame(cellId, btnSelected)
    }

    var activePlayer = 1
    var player1 = ArrayList<Int>();
    var player2 = ArrayList<Int>();

    fun playGame(cellId: Int, btnSelected: Button) {

        if (activePlayer == 1) {
            btnSelected.text = "X"
           // btnSelected.setBackgroundResource(android.R.color.holo_blue_light)
            btnSelected.background.setTint(ContextCompat.getColor(applicationContext, android.R.color.holo_blue_light))
            player1.add(cellId)
            activePlayer = 2
            autoPlay()
        } else {
            btnSelected.text = "O"
            //btnSelected.setBackgroundResource(android.R.color.holo_green_light)
            btnSelected.background.setTint(ContextCompat.getColor(applicationContext, android.R.color.holo_green_light))
            player2.add(cellId)
            activePlayer = 1
        }

        btnSelected.isEnabled = false

        checkWinner()
    }

    private fun autoPlay() {
        var emptyCells = ArrayList<Int>()

        for (cellId in 1..9) {
            if (!(player1.contains(cellId) || player2.contains(cellId))) {
                emptyCells.add(cellId)
            }
        }

        if(emptyCells.size == 0)
            reStartGame()

        val r = Random()
        val randomIndex = r.nextInt(emptyCells.size)
        val cellId = emptyCells[randomIndex]

        var btnSelected: Button = when (cellId) {
            1 -> binding.btn1
            2 -> binding.btn2
            3 -> binding.btn3
            4 -> binding.btn4
            5 -> binding.btn5
            6 -> binding.btn6
            7 -> binding.btn7
            8 -> binding.btn8
            9 -> binding.btn9
            else -> {
                binding.btn1
            }
        }

        playGame(cellId, btnSelected)
    }

    fun checkWinner() {
        var winner = -1

        //row 1
        if (player1.contains(1) && player1.contains(2) && player1.contains(3)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(2) && player2.contains(3)) {
            winner = 2
        }

        //row 2
        if (player1.contains(4) && player1.contains(5) && player1.contains(6)) {
            winner = 1
        }
        if (player2.contains(4) && player2.contains(5) && player2.contains(6)) {
            winner = 2
        }

        //row 3
        if (player1.contains(7) && player1.contains(8) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(7) && player2.contains(8) && player2.contains(9)) {
            winner = 2
        }

        //column 1
        if (player1.contains(1) && player1.contains(4) && player1.contains(7)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(4) && player2.contains(7)) {
            winner = 2
        }

        //column 2
        if (player1.contains(2) && player1.contains(5) && player1.contains(8)) {
            winner = 1
        }
        if (player2.contains(2) && player2.contains(5) && player2.contains(8)) {
            winner = 2
        }

        //column 1
        if (player1.contains(3) && player1.contains(6) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(3) && player2.contains(6) && player2.contains(9)) {
            winner = 2
        }

        if (winner == 1) {
            player1winsCount += 1
            Toast.makeText(this, "Player 1 win the game", Toast.LENGTH_LONG).show()
            reStartGame()
        } else if (winner == 2) {
            player2winsCount += 1
            Toast.makeText(this, "Player 2 win the game", Toast.LENGTH_LONG).show()
            reStartGame()
        }
    }

    var player1winsCount = 0
    var player2winsCount = 0

    fun reStartGame() {
        activePlayer = 1
        player1.clear()
        player2.clear()

        for (cellId in 1..9) {
            var btnSelected: Button = when (cellId) {
                1 -> binding.btn1
                2 -> binding.btn2
                3 -> binding.btn3
                4 -> binding.btn4
                5 -> binding.btn5
                6 -> binding.btn6
                7 -> binding.btn7
                8 -> binding.btn8
                9 -> binding.btn9
                else -> {
                    binding.btn1
                }
            }

            btnSelected.text = ""
            btnSelected.background.setTint(ContextCompat.getColor(applicationContext, R.color.button_color))
            btnSelected.isEnabled = true
        }

        Toast.makeText(
            this,
            "Player1:  + $player1winsCount, Player2: +$player2winsCount",
            Toast.LENGTH_SHORT
        ).show()
    }


}