
package com.example.dragontigerscanner

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.*
import android.view.ViewGroup

class MainActivity : Activity() {

    private val history = mutableListOf<String>()
    private lateinit var historyText: TextView
    private lateinit var dragonInput: EditText
    private lateinit var tigerInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(24, 24, 24, 24)
            setBackgroundColor(Color.rgb(20, 25, 35))
        }

        val title = TextView(this).apply {
            text = "Dragon Tiger Scanner"
            textSize = 24f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
        }
        layout.addView(title)

        fun addLabel(text: String) {
            layout.addView(TextView(this).apply {
                this.text = text
                textSize = 17f
                setTextColor(Color.WHITE)
                setPadding(0, 18, 0, 6)
            })
        }

        addLabel("Dragon का दिखाई दिया कार्ड")
        dragonInput = EditText(this).apply {
            hint = "जैसे A, K, Q, J, 10"
            setSingleLine()
            setTextColor(Color.BLACK)
            setHintTextColor(Color.GRAY)
            setBackgroundColor(Color.WHITE)
        }
        layout.addView(dragonInput)

        addLabel("Tiger का दिखाई दिया कार्ड")
        tigerInput = EditText(this).apply {
            hint = "जैसे 9, 8, 7"
            setSingleLine()
            setTextColor(Color.BLACK)
            setHintTextColor(Color.GRAY)
            setBackgroundColor(Color.WHITE)
        }
        layout.addView(tigerInput)

        val saveButton = Button(this).apply {
            text = "रिजल्ट सेव करो"
            setOnClickListener {
                val dragon = dragonInput.text.toString().trim()
                val tiger = tigerInput.text.toString().trim()

                if (dragon.isBlank() || tiger.isBlank()) {
                    Toast.makeText(
                        this@MainActivity,
                        "दोनों कार्ड भरें",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    history.add("Dragon: $dragon | Tiger: $tiger")
                    historyText.text = history.reversed().joinToString("\n\n")
                    dragonInput.text.clear()
                    tigerInput.text.clear()
                }
            }
        }
        layout.addView(saveButton)

        addLabel("रिजल्ट हिस्ट्री")
        historyText = TextView(this).apply {
            text = "अभी कोई रिकॉर्ड नहीं है"
            textSize = 16f
            setTextColor(Color.WHITE)
        }
        layout.addView(
            historyText,
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                0,
                1f
            )
        )

        setContentView(ScrollView(this).apply {
            addView(layout)
        })
    }
}
