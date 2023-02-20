package net.ivanvega.proyectodivisacontentprividera.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import java.util.concurrent.Flow

@Dao
interface MonedaDao {

    @Insert
    suspend fun insertar(moneda: Moneda)

    @Query("select * from Moneda")
    fun getAll(): kotlinx.coroutines.flow.Flow<List<Moneda>>

    @Query("DELETE FROM Nota")
    suspend fun deleteAll() : Int

}