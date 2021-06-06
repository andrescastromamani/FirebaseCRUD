package com.acm.crudfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import com.acm.crudfirebase.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db:FirebaseFirestore = FirebaseFirestore.getInstance()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btConsultar.setOnClickListener{
            var datos = ""
            db.collection("friends")
                .get()
                .addOnSuccessListener { resultado ->
                    for (documento in resultado){
                        datos += "${documento.id}: ${documento.data}\n"
                    }
                    binding.tvConsulta.text = datos
                }
                .addOnFailureListener{ exception ->
                    binding.tvConsulta.text = "No se pudo conectar"
                }
        }
    }
}