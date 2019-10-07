package com.example.samplecsv

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Build
import androidx.annotation.RequiresApi
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.File
import java.io.Reader
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths


class MainActivity : AppCompatActivity() {
 private lateinit var mapsOfContacts : MutableList<Map<String, String>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        open_button.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "*/*"
            startActivityForResult(intent, 7)
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // TODO Auto-generated method stub

        when (requestCode) {

            7 ->
                if (resultCode == Activity.RESULT_OK) {
                    val pathHolder = data!!.data!!.path
                   csvReader().open(pathHolder!!){
                       readAllAsSequence().forEach { row ->
                          val map = mapOf(row[0] to row[1])
                           mapsOfContacts.add(map)
                       } }

                   }



                }
        }
    }

