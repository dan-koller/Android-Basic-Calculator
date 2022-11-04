package com.example.android_basic_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.android_basic_calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = CalculatorViewModel()

        viewModel.getHint.observe(this) { hint -> binding.editText.hint = hint }

        val listener = View.OnClickListener { v ->
            when (v.id) {
                binding.button0.id -> viewModel.zeroPressed()
                binding.button1.id -> viewModel.digitPressed("1")
                binding.button2.id -> viewModel.digitPressed("2")
                binding.button3.id -> viewModel.digitPressed("3")
                binding.button4.id -> viewModel.digitPressed("4")
                binding.button5.id -> viewModel.digitPressed("5")
                binding.button6.id -> viewModel.digitPressed("6")
                binding.button7.id -> viewModel.digitPressed("7")
                binding.button8.id -> viewModel.digitPressed("8")
                binding.button9.id -> viewModel.digitPressed("9")
                binding.dotButton.id -> viewModel.dotPressed()
                binding.clearButton.id -> viewModel.clearPressed()

                binding.addButton.id -> viewModel.operandPressed("+")
                binding.subtractButton.id -> viewModel.subtractPressed() // unary minus
                binding.multiplyButton.id -> viewModel.operandPressed("*")
                binding.divideButton.id -> viewModel.operandPressed("/")
                binding.equalButton.id -> viewModel.operandPressed("=")
            }
            // Set the result to the text view
            binding.editText.setText(viewModel.getResult.value)
        }

        val operandListener = View.OnClickListener { v ->
            when (v.id) {
                binding.addButton.id -> viewModel.operandPressed("+")
                binding.subtractButton.id -> viewModel.subtractPressed() // unary minus
                binding.multiplyButton.id -> viewModel.operandPressed("*")
                binding.divideButton.id -> viewModel.operandPressed("/")
                binding.equalButton.id -> viewModel.operandPressed("=")
            }
            // Set the result to the text view
            binding.editText.setText(viewModel.getResult.value)
        }

        // Set the listener for the number buttons
        binding.button0.setOnClickListener(listener)
        binding.button1.setOnClickListener(listener)
        binding.button2.setOnClickListener(listener)
        binding.button3.setOnClickListener(listener)
        binding.button4.setOnClickListener(listener)
        binding.button5.setOnClickListener(listener)
        binding.button6.setOnClickListener(listener)
        binding.button7.setOnClickListener(listener)
        binding.button8.setOnClickListener(listener)
        binding.button9.setOnClickListener(listener)
        binding.dotButton.setOnClickListener(listener)
        binding.clearButton.setOnClickListener(listener)
        // Set the operand listeners
        binding.addButton.setOnClickListener(operandListener)
        binding.subtractButton.setOnClickListener(operandListener)
        binding.multiplyButton.setOnClickListener(operandListener)
        binding.divideButton.setOnClickListener(operandListener)
        binding.equalButton.setOnClickListener(operandListener)
    }
}