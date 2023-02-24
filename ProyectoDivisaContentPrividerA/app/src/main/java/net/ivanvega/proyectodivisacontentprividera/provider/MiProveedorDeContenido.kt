package net.ivanvega.proyectodivisacontentprividera.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import net.ivanvega.proyectodivisacontentprividera.MiApplication
import net.ivanvega.proyectodivisacontentprividera.db.MiDbMonedas

private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
    /*
     * The calls to addURI() go here, for all of the content URI patterns that the provider
     * should recognize. For this snippet, only the calls for table 3 are shown.
     */

    /*
     * Sets the integer value for multiple rows in table 3 to 1. Notice that no wildcard is used
     * in the path
     */
    addURI("net.ivanvega.proyectodivisacontentprividera", "monedas", 1)
    //"content://net.ivanvega.proyectodivisacontentprividera/monedas"

    /*
     * Sets the code for a single row to 2. In this case, the "#" wildcard is
     * used. "content://com.example.app.provider/table3/3" matches, but
     * "content://com.example.app.provider/table3 doesn't.
     */
    addURI("net.ivanvega.proyectodivisacontentprividera", "monedas/#", 2)

    addURI("net.ivanvega.proyectodivisacontentprividera", "monedas/*", 3)
}

class MiProveedorDeContenido : ContentProvider() {
    private lateinit var db: MiDbMonedas

    override fun onCreate(): Boolean {
        //TODO("Not yet implemented")
        db = (context as MiApplication).database
        return true
    }

    override fun query(
        p0: Uri,
        p1: Array<out String>?,
        p2: String?,
        p3: Array<out String>?,
        p4: String?
    ): Cursor? {
        //TODO("Not yet implemented")
        var cursor: Cursor? = null
        when( sUriMatcher.match(p0)){
            //"content://net.ivanvega.proyectodivisacontentprividera/monedas"
            //query / insert
            1 -> {
                //ir  a la bd y traer el getall
               cursor =  db.monedaDao().getAllCursor()
            }

            //"content://net.ivanvega.proyectodivisacontentprividera/monedas/*"
            //query
            2 -> {
            }

            //"content://net.ivanvega.proyectodivisacontentprividera/monedas/#"
            //query / update  /  delete
            3 -> {
            }
            else -> {
            }

        }
         return cursor
    }

    override fun getType(p0: Uri): String? {
        //TODO("Not yet implemented")
        var typeMime: String = "vnd.android.cursor.dir/vnd.net.ivanvega.provider.monedas"
        when( sUriMatcher.match(p0)){

            //"content://net.ivanvega.proyectodivisacontentprividera/monedas"
            //query / insert
            1 -> {
                //ir  a la bd y traer el getall
                typeMime = "vnd.android.cursor.dir/vnd.net.ivanvega.provider.monedas"
            }

            //"content://net.ivanvega.proyectodivisacontentprividera/monedas/#"
            //query
            2 -> {
                typeMime = "vnd.android.cursor.item/vnd.net.ivanvega.provider.monedas"
            }

            //"content://net.ivanvega.proyectodivisacontentprividera/monedas/*"
            //query / update  /  delete
            3 -> {
                typeMime = "vnd.android.cursor.item/vnd.net.ivanvega.provider.monedas"
            }
            else -> {

            }

        }
        return  typeMime
    }

    override fun insert(p0: Uri, p1: ContentValues?): Uri? {
        //TODO("Not yet implemented")
        when( sUriMatcher.match(p0)){

            //"content://net.ivanvega.proyectodivisacontentprividera/monedas"
            //query / insert
            1 -> {
                //ir  a la bd y llamar insert. Mapear ContentValues con Pojo
            }

            //"content://net.ivanvega.proyectodivisacontentprividera/monedas/*"
            //query
            2 -> {

            }

            //"content://net.ivanvega.proyectodivisacontentprividera/monedas/#"
            //query / update  /  delete
            3 -> {

            }
            else -> {

            }

        }
        return null
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        //TODO("Not yet implemented")
        when( sUriMatcher.match(p0)){

            //"content://net.ivanvega.proyectodivisacontentprividera/monedas"
            //query / insert
            1 -> {
                //ir  a la bd y traer el getall
            }

            //"content://net.ivanvega.proyectodivisacontentprividera/monedas/*"
            //query
            2 -> {

            }

            //"content://net.ivanvega.proyectodivisacontentprividera/monedas/#"
            //query / update  /  delete
            3 -> {
                    //mandar llamar el dao para un delete
            }
            else -> {

            }

        }
        return  0
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        //TODO("Not yet implemented")
        when( sUriMatcher.match(p0)){

            //"content://net.ivanvega.proyectodivisacontentprividera/monedas"
            //query / insert
            1 -> {
                //ir  a la bd y traer el getall
            }

            //"content://net.ivanvega.proyectodivisacontentprividera/monedas/*"
            //query
            2 -> {

            }

            //"content://net.ivanvega.proyectodivisacontentprividera/monedas/#"
            //query / update  /  delete
            3 -> {
                    //Mandar llamar el dao para actuailizar un registro por ID
            }
            else -> {

            }

        }
        return  0
    }

}