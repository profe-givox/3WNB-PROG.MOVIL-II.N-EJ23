package net.ivanvega.proyectodivisacontentprividera

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import net.ivanvega.proyectodivisacontentprividera.db.MiDbMonedas
import net.ivanvega.proyectodivisacontentprividera.repository.MonedaRepository

class MiApplication : Application() {
    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { MiDbMonedas.getDatabase(this, applicationScope) }
    val repositoryMoneda by lazy {  MonedaRepository (database.monedaDao()) }

    override fun onCreate() {
        super.onCreate()

    }
}