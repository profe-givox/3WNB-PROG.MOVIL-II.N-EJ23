package net.ivanvega.proyectodivisacontentprividera.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Moneda (
    @PrimaryKey(autoGenerate = true)
    val  _ID: Int ,
   val codeMoneda: String,
   val nombreMoneda: String,
   val pais : String
                   )