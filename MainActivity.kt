package ultralogs.softind.com.tictactoykotlin

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()
    var activatePlayer = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * @param View
     * @Description passing button click event
     */
    protected fun onClickButton(view: View) {
        val buSlected = view as Button
        var id = 0
        when (buSlected.id) {
            R.id.btn1 -> id = 1
            R.id.btn2 -> id = 2
            R.id.btn3 -> id = 3
            R.id.btn4 -> id = 4
            R.id.btn5 -> id = 5
            R.id.btn6 -> id = 6
            R.id.btn7 -> id = 7
            R.id.btn8 -> id = 8
            R.id.btn9 -> id = 9
        }
        //
        playTicTacToe(id, buSlected)
    }

    /**
     * @param id
     * @param slectedBtn
     * @Description
     * based on click events button id
     */
    fun playTicTacToe(id: Int, slectedBtn: Button) {
        slectedBtn.isEnabled = false;
        if (activatePlayer == 1) {
            slectedBtn.text = "X"
            slectedBtn.setBackgroundResource(R.color.colorPlayer1Color)
            player1.add(id)
            activatePlayer = 2
            AutoPlay()
        } else {
            slectedBtn.text = "O"
            slectedBtn.setBackgroundResource(R.color.colorPlayer2Color)
            player2.add(id)
            activatePlayer = 1
        }
        findWinner()
    }

    /**
     * @Description
     * winner is always finding consecutive X or O in a row or columns
     * Writing very simple function to find winner
     * @Note
     * Not the perfect way to tell the winner
     */
    fun findWinner() {
        var winner = -1;
        //row 1
        if (player1.contains(1) && player1.contains(2) && player1.contains(3)) {
            winner = 1
        }
        //row 2
        if (player1.contains(4) && player1.contains(5) && player1.contains(6)) {
            winner = 1
        }
        //row 2
        if (player1.contains(7) && player1.contains(8) && player1.contains(9)) {
            winner = 1
        }


        //row 1
        if (player2.contains(1) && player2.contains(2) && player2.contains(3)) {
            winner = 2
        }
        //row 2
        if (player2.contains(4) && player2.contains(5) && player2.contains(6)) {
            winner = 2
        }
        //row 2
        if (player2.contains(7) && player2.contains(8) && player2.contains(9)) {
            winner = 2
        }

        //column 1
        if (player1.contains(1) && player1.contains(4) && player1.contains(7)) {
            winner = 1
        }
        //column 2
        if (player1.contains(4) && player1.contains(5) && player1.contains(8)) {
            winner = 1
        }
        //column 2
        if (player1.contains(7) && player1.contains(6) && player1.contains(8)) {
            winner = 1
        }


        //column 1
        if (player2.contains(1) && player2.contains(2) && player2.contains(3)) {
            winner = 2
        }
        //column 2
        if (player2.contains(4) && player2.contains(5) && player2.contains(6)) {
            winner = 2
        }
        //column 2
        if (player2.contains(7) && player2.contains(8) && player2.contains(9)) {
            winner = 2
        }

        if (winner != -1) {
            if (winner == 1) {
                Toast.makeText(this, "Player 1 Won", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Player 2 Won", Toast.LENGTH_LONG).show()
            }
        }
    }

    /**
     * @Description
     * finding the empty cells and executed click event as 2nd player (Device)
     */
    fun AutoPlay() {
        var emptyCells = ArrayList<Int>()
        for (id in 1..9) {
            if (!(player1.contains(id) || player2.contains(id))) {
                emptyCells.add(id);
            }
        }

        val r = Random()
        if (emptyCells.size > 0) { //avoid crash when device and user draw the game
            val index = r.nextInt(emptyCells.size - 0) + 0

            val cellId = emptyCells.get(index)

            var btn: Button?
            when (cellId) {
                1 -> btn = btn1
                2 -> btn = btn2
                3 -> btn = btn3
                4 -> btn = btn4
                5 -> btn = btn5
                6 -> btn = btn6
                7 -> btn = btn7
                8 -> btn = btn8
                9 -> btn = btn9
                else -> {
                    btn = btn1
                }
            }
            playTicTacToe(cellId, btn)
        } else {
            Toast.makeText(this, "Draw", Toast.LENGTH_LONG).show()
        }
    }
}
