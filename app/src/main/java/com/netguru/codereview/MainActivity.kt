package com.netguru.codereview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.netguru.codereview.ui.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        I notice that we have a MainFragment that is part of the shoplist
        module and is initialized in MainActivity.
        The only problem is that the transaction is very difficult

        shopiList module follows the MVVM architecture
         */
        supportFragmentManager.beginTransaction().replace(R.id.container, MainFragment()).commitNow()
    }
}
