package com.example.insurancepremiumcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isEmpty
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

   lateinit var myData: PremiumModel//myData is object name/ object pointer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myData = ViewModelProviders.of(this).get(PremiumModel::class.java)

        display()

        btnCalc.setOnClickListener()
        {
            val total: Double = getPremium()
            textView6.text="%.1f".format(total)
            myData.premiumAmount = getPremium()
            display()
        }

        btnReset.setOnClickListener(){
            radioGroup.clearCheck()
            spinner.setSelection(0)
            textView6.text=null
            chkSmoker.setChecked(false)
            myData.premiumAmount = 0.0
        }
    }

   fun getPremium():Double{

       return when(spinner.selectedItemPosition){
           0-> 60.00
           1-> 70.00 +
                   (if(radMale.isChecked) 50.00 else 0.0) +
                   (if(chkSmoker.isChecked) 100.00 else 0.0)
           2-> 90.00 +
                   (if(radMale.isChecked) 100.00 else 0.0) +
                   (if(chkSmoker.isChecked) 150.00 else 0.0)
           3-> 120.00 +
                   (if(radMale.isChecked) 150.00 else 0.0) +
                   (if(chkSmoker.isChecked) 200.00 else 0.0)
           4-> 150.00 +
                   (if(radMale.isChecked) 200.00 else 0.0) +
                   (if(chkSmoker.isChecked) 250.00 else 0.0)
           else -> 150.00 +
                   (if(radMale.isChecked) 200.00 else 0.0) +
                   (if(chkSmoker.isChecked) 300.00 else 0.0)
       }
   }

    fun display(){
        if(myData.premiumAmount !=0.0)
        textView6.text = myData.premiumAmount.toString()
    }

}
