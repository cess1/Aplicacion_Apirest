package com.example.android_kt_retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class PantallaPrincipalActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AutorAdapter
    private val autorLibroList = mutableListOf<AutorLibro>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_principal)

        val btnRegistrar: Button = findViewById(R.id.btnRegistrar)
        val btnLimpiar: Button = findViewById(R.id.btnLimpiar)

        val inputAutor: EditText = findViewById(R.id.txtAutor)
        val inputLibro: EditText = findViewById(R.id.txtLibro)

        // Configura el RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = AutorAdapter(autorLibroList)
        recyclerView.adapter = adapter

        btnRegistrar.setOnClickListener {
            val autor = inputAutor.text.toString()
            val libro = inputLibro.text.toString()

            if (autor.isNotEmpty() && libro.isNotEmpty()) {
                val db = BDHelper(this, null)

                try {
                    db.CrearRegistro(autor, libro)
                    Toast.makeText(this, "Se registró correctamente", Toast.LENGTH_LONG).show()
                    // Agrega el nuevo autor y libro a la lista
                    autorLibroList.add(AutorLibro(autor, libro))
                    // Notifica al adaptador que los datos han cambiado
                    adapter.notifyDataSetChanged()
                } catch (e: Exception) {
                    Toast.makeText(this, "Error al registrar: " + e.message, Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "Los campos 'Autor' y 'Libro' no pueden estar vacíos", Toast.LENGTH_LONG).show()
            }
        }


        btnLimpiar.setOnClickListener {
            // Limpia los campos de texto
            inputAutor.text.clear()
            inputLibro.text.clear()
        }

        // Cargar registros existentes al iniciar la actividad
        cargarRegistrosExistentes()
    }

    private fun cargarRegistrosExistentes() {
        val db = BDHelper(this, null)
        val registros = db.obtenerAutoresYLibros()
        autorLibroList.addAll(registros)
        adapter.notifyDataSetChanged()
    }
}


