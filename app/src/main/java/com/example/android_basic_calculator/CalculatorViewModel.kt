package com.example.android_basic_calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.math.absoluteValue

class CalculatorViewModel : ViewModel() {

    private var previousNumber = 0.0
    private var selectedOperation = "="

    private val result = MutableLiveData<String>()
    val getResult: LiveData<String>
        get() = result

    private val hint = MutableLiveData<String>().apply { postValue("0") }
    val getHint: LiveData<String>
        get() = hint

    // Handler for number buttons
    fun digitPressed(digit: String) {
        when (result.value) {
            "0" -> result.value = digit
            "-0" -> result.value = "-$digit"
            else -> {
                // Only append the digit if the current number is not null
                result.value = if (result.value != null) result.value + digit else digit
            }
        }
    }

    // Handler for zero button
    fun zeroPressed() {
        if ((result.value ?: "") != "0") digitPressed("0")
    }

    // Handler for dot button
    fun dotPressed() {
        // If the unary minus is pressed, add a leading zero
        if (result.value == "-") {
            result.value = "-0."
        } else if (result.value?.contains(".") == false) {
            result.value = result.value + "."
        }
    }

    // Handler for clear button
    fun clearPressed() {
        // Reset to default values
        result.value = "" // DO NOT CHANGE TO "0"
        previousNumber = 0.0
        selectedOperation = "="
        hint.value = "0"
    }

    // Handler for the subtract button and unary minus
    fun subtractPressed() {
        if ((result.value ?: "").isEmpty()) result.value = "-" else operandPressed("-")
    }

    /**
     * Handler for operation buttons.
     * @param operation - operation to perform
     */
    fun operandPressed(operation: String) {
        // Get the current number from the text view
        val value = (result.value ?: "0").toDoubleOrNull() ?: 0.0

        val numFormatted = {
            (if (previousNumber.absoluteValue.rem(1)
                    .equals(0.0)
            ) previousNumber.toInt() else previousNumber).toString()
        }

        // Process calculation
        when (selectedOperation) {
            "=" -> previousNumber = value
            "/" -> previousNumber = if (value == 0.0) Double.NaN else previousNumber / value
            "*" -> previousNumber *= value
            "-" -> previousNumber -= value
            "+" -> previousNumber += value
        }

        // Set the result to the text view
        result.value = if (operation == "=") numFormatted() else {
            hint.value = numFormatted()
            ""
        }
        // Save the operation
        selectedOperation = operation
    }
}