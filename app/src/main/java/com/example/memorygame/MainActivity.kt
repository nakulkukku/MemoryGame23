package com.example.memorygame

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var buttonDatePicker = findViewById<Button>(R.id.btnDatePicker) as Button
        buttonDatePicker.setOnClickListener {view->
            clickDatePicker(view)
            Toast.makeText(this, "Button Works", Toast.LENGTH_LONG).show()
        }

    }

    fun clickDatePicker(view: View){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            val myCalender = Calendar.getInstance()
            val year = myCalender.get(Calendar.YEAR)
            val month = myCalender.get(Calendar.MONTH)
            val day = myCalender.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
                    view, selectedYear, selectedMonth, selectedDayOfMonth ->

                val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                var selectedDateTextView = findViewById<TextView>(R.id.tvSelectedDate)
                selectedDateTextView.text = selectedDate

                val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = simpleDateFormat.parse(selectedDate)
                val selectedDateInMinutes = theDate!!.time / 60000

                val currentDate = simpleDateFormat.parse(simpleDateFormat.format(System.currentTimeMillis()))
                val currentDateInMinutes = currentDate!!.time / 60000
                val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                val selectedDateInMinutesTextView = findViewById<TextView>(R.id.tvSelectedDateInMinutes)
                selectedDateInMinutesTextView.text = differenceInMinutes.toString()
            }, year, month, day)

            datePickerDialog.datePicker.maxDate = myCalender.timeInMillis
            datePickerDialog.show()
        }
    }

}