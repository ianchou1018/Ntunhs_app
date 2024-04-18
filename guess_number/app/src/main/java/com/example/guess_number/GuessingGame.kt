package com.example.guess_number

import java.util.*

class GuessingGame {
    private var secret = Random().nextInt(100) + 1
    var maxNum = 100
    var minNum = 0

    fun guessNumber(guess: Int): String {
        if (guess > maxNum || guess < minNum) {
            return "請輸入位於${minNum}~${maxNum}的數字"
        }

        when {
            guess > secret -> {
                maxNum = guess
                return "你猜的比較大喔"
            }
            guess < secret -> {
                minNum = guess
                return "你猜的比較小喔"
            }
            else -> {
                resetGame()  // Reset game on correct guess
                return "猜對了"
            }
        }
    }

    fun getRangeText(): String = "$minNum~$maxNum"

    fun resetGame() {
        secret = Random().nextInt(100) + 1
        maxNum = 100
        minNum = 0
    }
}