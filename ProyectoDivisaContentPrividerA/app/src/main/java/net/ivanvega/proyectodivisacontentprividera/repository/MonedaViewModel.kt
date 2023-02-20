package net.ivanvega.proyectodivisacontentprividera.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import net.ivanvega.proyectodivisacontentprividera.db.Moneda

class MonedaViewModel(private val repositoryMoneda: MonedaRepository) : ViewModel() {
    val allMoneda : LiveData<List<Moneda>> = repositoryMoneda.allMonedas.asLiveData()
}

class MonedaViewModelFactory(private val repositoryMoneda: MonedaRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MonedaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MonedaViewModel(repositoryMoneda ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
