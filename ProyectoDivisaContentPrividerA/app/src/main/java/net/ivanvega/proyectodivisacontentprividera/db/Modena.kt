package net.ivanvega.proyectodivisacontentprividera.db

import androidx.room.Entity

@Entity
data class Moneda (val _ID: Int ,
                   val codeMoneda: String,
                   val nombreMoneda: String,
                   val pais : String
                   )