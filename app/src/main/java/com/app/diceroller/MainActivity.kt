package com.app.diceroller

import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ImageView
import androidx.activity.ComponentActivity
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    private lateinit var rollButton: Button
    private lateinit var diceImage: ImageView
    private val diceImages = arrayOf(
        R.drawable.dice_1,
        R.drawable.dice_2,
        R.drawable.dice_3,
        R.drawable.dice_4,
        R.drawable.dice_5,
        R.drawable.dice_6
    )
    private val random = Random(System.currentTimeMillis())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rollButton = findViewById(R.id.button)
        diceImage = findViewById(R.id.imageView5)

        rollButton.setOnClickListener {
            rollDiceAnimation()
        }

        // Initially set a random dice face
        rollDice()
    }

    private fun rollDiceAnimation() {
        // Disable the button during animation
        rollButton.isEnabled = false


        val rollCount = 10 // Number of times to roll the dice
        val handler = Handler()

        for (i in 0 until rollCount) {
            handler.postDelayed({
                val randomIndex = random.nextInt(diceImages.size)
                diceImage.setImageResource(diceImages[randomIndex])
            }, (i * 100).toLong()) // Delay each image change by 100 milliseconds
        }

        // Re-enable the button after the animation is complete
        handler.postDelayed({
            rollButton.isEnabled = true
            // Update the content description with the final dice value
            val finalDiceValue = diceImages[diceImage.tag as Int]
            diceImage.contentDescription = finalDiceValue.toString()
        }, (rollCount * 100).toLong())
    }

    private fun rollDice() {
        // Roll the dice and set the initial dice face
        val diceRoll = random.nextInt(6) + 1
        val drawableResource = diceImages[diceRoll - 1]
        diceImage.setImageResource(drawableResource)
        diceImage.tag = diceRoll - 1 // Store the index of the current dice face
    }
}
