package com.example.tic_tac_toe_game

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.Button
import android.widget.TextView


class CustomDialog(
    private val context: Context,
    private val message: String,
    private val textButton: String
) : Dialog(context) {

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.custom_dialog)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val text = findViewById<TextView>(R.id.Dialog_Text)
        text.text = message

        val button = findViewById<Button>(R.id.Dialog_Button_Start)
        button.text = textButton
        button.setOnClickListener {
            dismiss()
        }
    }
}