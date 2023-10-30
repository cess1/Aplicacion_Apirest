package com.example.android_kt_retrofit

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BDHelper (context: Context, factory: SQLiteDatabase.CursorFactory?)
    : SQLiteOpenHelper (context, DATABASE_NAME, factory, DATABASE_VERSION) {


    companion object {

        private val DATABASE_NAME = "BD_Libro"
        private val DATABASE_VERSION = 1
        private val TABLA_AUTOR = "AUTOR"
        private val COLUMN_ID = "IDAUTOR"
        private val COLUMN_AUTOR = "AUTOR"
        private val COLUMN_LIBRO = "LIBRO"

    }

    override fun onCreate(db: SQLiteDatabase) {
        val tableExist = "SELECT name FROM sqlite_master WHERE type='table' AND name='$TABLA_AUTOR'"
        val cursor = db.rawQuery(tableExist, null)
        if (cursor.count == 0) {
            // La tabla no existe, entonces la creamos
            val queryCreateTable = "CREATE TABLE $TABLA_AUTOR ( " +
                    "$COLUMN_ID INTEGER PRIMARY KEY, " +
                    "$COLUMN_AUTOR TEXT, " +
                    "$COLUMN_LIBRO TEXT )"
            db.execSQL(queryCreateTable)
        }
    }

    fun CrearRegistro(autor:String, libro:String) {

        val values = ContentValues();
        values.put(COLUMN_AUTOR, autor)
        values.put(COLUMN_LIBRO, libro)
        val db = this.writableDatabase
        db.insert(TABLA_AUTOR,null, values)
        db.close()
    }

    fun obtenerAutoresYLibros(): List<AutorLibro> {
        val cursor = readableDatabase.rawQuery("SELECT $COLUMN_AUTOR, $COLUMN_LIBRO FROM $TABLA_AUTOR", null)
        val autorLibroList = ArrayList<AutorLibro>()

        while (cursor.moveToNext()) {
            val autor = cursor.getString(cursor.getColumnIndex(COLUMN_AUTOR))
            val libro = cursor.getString(cursor.getColumnIndex(COLUMN_LIBRO))
            autorLibroList.add(AutorLibro(autor, libro))
        }

        cursor.close()
        return autorLibroList
    }


    fun ListarTodosRegistros() : Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM " + TABLA_AUTOR, null)
    }



    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }


}