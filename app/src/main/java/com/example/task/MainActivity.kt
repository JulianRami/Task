package com.example.task

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.task.ui.screens.task.AddTaskFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Cargar el fragmento inicial
        if (savedInstanceState == null) {
            loadFragment(AddTaskFragment()) // Aquí llamas a tu primer fragmento
        }
    }

    private fun loadFragment(fragment: Fragment) {
        // Cambia el fragmento dentro del FragmentContainerView
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }
}
