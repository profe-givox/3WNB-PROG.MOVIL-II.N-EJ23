package net.ivanvega.proyectodivisacontentprividera

import android.content.ContentValues
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.UserDictionary
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import net.ivanvega.proyectodivisacontentprividera.databinding.ActivityMainBinding
import net.ivanvega.proyectodivisacontentprividera.repository.MonedaViewModel
import net.ivanvega.proyectodivisacontentprividera.repository.MonedaViewModelFactory
import java.util.Arrays

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private val MonedaViewModel: MonedaViewModel by  viewModels {
        MonedaViewModelFactory((application as MiApplication).repositoryMoneda)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

                // Sets the columns to retrieve for the user profile
                val  projection = arrayOf(
                    ContactsContract.Profile._ID,
                    ContactsContract.Profile.DISPLAY_NAME_PRIMARY,
                    ContactsContract.Profile.LOOKUP_KEY,
                    ContactsContract.Profile.PHOTO_THUMBNAIL_URI
                )

    // Retrieves the profile from the Contacts Provider
                val profileCursor = contentResolver.query(
                    ContactsContract.Profile.CONTENT_URI,
                    projection,
                    null,
                    null,
                    null
                )

            profileCursor?.apply {
                while (moveToNext()){
                    Log.i("ContactsProvcider", " id: ${getInt(0)} , nombre: ${getString(1)} ")
                }
            }
        }

        // Defines an object to contain the new values to insert
        val newValues = ContentValues().apply {
            /*
             * Sets the values of each column and inserts the word. The arguments to the "put"
             * method are "column name" and "value"
             */
            put(UserDictionary.Words.APP_ID, "example.user")
            put(UserDictionary.Words.LOCALE, "en_US")
            put(UserDictionary.Words.WORD, "insert")
            put(UserDictionary.Words.FREQUENCY, "100")

        }

        val miuri = contentResolver.insert(
            UserDictionary.Words.CONTENT_URI,
            newValues
        )

        Log.i("MiPropiaUri", miuri.toString())

        val cursor = contentResolver.query(
            UserDictionary.Words.CONTENT_URI,
            arrayOf(UserDictionary.Words._ID,
                UserDictionary.Words.WORD, UserDictionary.Words.FREQUENCY ),
            null, null, null)

        cursor?.apply {
            while (moveToNext()){
                Log.i("WordDictionary", " id: ${getInt(0)} , palabra: ${getString(1)} ")
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}