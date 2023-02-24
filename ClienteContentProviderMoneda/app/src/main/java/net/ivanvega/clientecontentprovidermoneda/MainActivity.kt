package net.ivanvega.clientecontentprovidermoneda

import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SimpleCursorAdapter
import android.widget.Spinner
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader

class MainActivity : AppCompatActivity() {

    lateinit var spnMonedas : Spinner

    val mLoaderCallbacks = object : LoaderManager.LoaderCallbacks<Cursor>{
        override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
            //TODO("Not yet implemented")
            return CursorLoader(
                applicationContext,
                Uri.parse("content://net.ivanvega.proyectodivisacontentprividera/monedas"),
                arrayOf<String>("_ID", "codeMoneda","nombreMoneda"),
                null, null, null)

        }

        override fun onLoaderReset(loader: Loader<Cursor>) {
            //TODO("Not yet implemented")
        }

        override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
            //TODO("Not yet implemented")
            val adapter = SimpleCursorAdapter(applicationContext,
            android.R.layout.simple_list_item_2,data,
                arrayOf<String>("_ID", "codeMoneda","nombreMoneda"),
                IntArray(2).apply { set(0, android.R.id.text1)
                    set(1, android.R.id.text2)
                },
                SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE
                )
                spnMonedas.adapter =adapter
            data?.let {
                while (it.moveToNext()){
                    Log.i("CPMonenas", "ID: ${it.getInt(0)} , code=${it.getString(1)}")
                }
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spnMonedas = findViewById(R.id.spinner)

        LoaderManager.getInstance(this)
            .initLoader<Cursor>(1001, null, mLoaderCallbacks)

    }


}