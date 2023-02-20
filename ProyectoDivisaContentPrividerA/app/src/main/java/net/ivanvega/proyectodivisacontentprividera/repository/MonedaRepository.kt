package net.ivanvega.proyectodivisacontentprividera.repository

import kotlinx.coroutines.flow.Flow
import net.ivanvega.proyectodivisacontentprividera.db.Moneda
import net.ivanvega.proyectodivisacontentprividera.db.MonedaDao

class MonedaRepository(private val monedaDao: MonedaDao) {
    val allMonedas: Flow<List<Moneda>>
        get() = monedaDao.getAll()
}