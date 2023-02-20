package net.ivanvega.proyectodivisacontentprividera.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Database(entities = arrayOf(Moneda::class), version = 1)
abstract class MiDbMonedas : RoomDatabase() {
    abstract fun monedaDao(): MonedaDao

    private class MiDbMonedasCallback( private val scope: CoroutineScope)
        : RoomDatabase.Callback(){
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                INSTANCE?.let { database ->
                    scope.launch (Dispatchers.IO){
                        var monedaDao = database.monedaDao()

                        // Delete all content here.
                        monedaDao.deleteAll()

                        // Add sample words.
                        //val modenada1 = Moneda(0,"","", "")

                        monedaDao.insertar(Moneda(0,"AED","UAE Dirham","United Arab Emirates"))
                        monedaDao.insertar(Moneda(0,"AFN","Afghan Afghani","Afghanistan"))
                        monedaDao.insertar(Moneda(0,"ALL","Albanian Lek","Albania"))
                        monedaDao.insertar(Moneda(0,"AMD","Armenian Dram","Armenia"))
                        monedaDao.insertar(Moneda(0,"ANG","Netherlands Antillian Guilder","Netherlands Antilles"))
                        monedaDao.insertar(Moneda(0,"AOA","Angolan Kwanza","Angola"))
                        monedaDao.insertar(Moneda(0,"ARS","Argentine Peso","Argentina"))
                        monedaDao.insertar(Moneda(0,"AUD","Australian Dollar","Australia"))
                        monedaDao.insertar(Moneda(0,"AWG","Aruban Florin","Aruba"))
                        monedaDao.insertar(Moneda(0,"AZN","Azerbaijani Manat","Azerbaijan"))
                        monedaDao.insertar(Moneda(0,"BAM","Bosnia and Herzegovina Mark","Bosnia and Herzegovina"))
                        monedaDao.insertar(Moneda(0,"BBD","Barbados Dollar","Barbados"))
                        monedaDao.insertar(Moneda(0,"BDT","Bangladeshi Taka","Bangladesh"))
                        monedaDao.insertar(Moneda(0,"BGN","Bulgarian Lev","Bulgaria"))
                        monedaDao.insertar(Moneda(0,"BHD","Bahraini Dinar","Bahrain"))
                        monedaDao.insertar(Moneda(0,"BIF","Burundian Franc","Burundi"))
                        monedaDao.insertar(Moneda(0,"BMD","Bermudian Dollar","Bermuda"))
                        monedaDao.insertar(Moneda(0,"BND","Brunei Dollar","Brunei"))
                        monedaDao.insertar(Moneda(0,"BOB","Bolivian Boliviano","Bolivia"))
                        monedaDao.insertar(Moneda(0,"BRL","Brazilian Real","Brazil"))
                        monedaDao.insertar(Moneda(0,"BSD","Bahamian Dollar","Bahamas"))
                        monedaDao.insertar(Moneda(0,"BTN","Bhutanese Ngultrum","Bhutan"))
                        monedaDao.insertar(Moneda(0,"BWP","Botswana Pula","Botswana"))
                        monedaDao.insertar(Moneda(0,"BYN","Belarusian Ruble","Belarus"))
                        monedaDao.insertar(Moneda(0,"BZD","Belize Dollar","Belize"))
                        monedaDao.insertar(Moneda(0,"CAD","Canadian Dollar","Canada"))
                        monedaDao.insertar(Moneda(0,"CDF","Congolese Franc","Democratic Republic of the Congo"))
                        monedaDao.insertar(Moneda(0,"CHF","Swiss Franc","Switzerland"))
                        monedaDao.insertar(Moneda(0,"CLP","Chilean Peso","Chile"))
                        monedaDao.insertar(Moneda(0,"CNY","Chinese Renminbi","China"))
                        monedaDao.insertar(Moneda(0,"COP","Colombian Peso","Colombia"))
                        monedaDao.insertar(Moneda(0,"CRC","Costa Rican Colon","Costa Rica"))
                        monedaDao.insertar(Moneda(0,"CUP","Cuban Peso","Cuba"))
                        monedaDao.insertar(Moneda(0,"CVE","Cape Verdean Escudo","Cape Verde"))
                        monedaDao.insertar(Moneda(0,"CZK","Czech Koruna","Czech Republic"))
                        monedaDao.insertar(Moneda(0,"DJF","Djiboutian Franc","Djibouti"))
                        monedaDao.insertar(Moneda(0,"DKK","Danish Krone","Denmark"))
                        monedaDao.insertar(Moneda(0,"DOP","Dominican Peso","Dominican Republic"))
                        monedaDao.insertar(Moneda(0,"DZD","Algerian Dinar","Algeria"))
                        monedaDao.insertar(Moneda(0,"EGP","Egyptian Pound","Egypt"))
                        monedaDao.insertar(Moneda(0,"ERN","Eritrean Nakfa","Eritrea"))
                        monedaDao.insertar(Moneda(0,"ETB","Ethiopian Birr","Ethiopia"))
                        monedaDao.insertar(Moneda(0,"EUR","Euro","European Union"))
                        monedaDao.insertar(Moneda(0,"FJD","Fiji Dollar","Fiji"))
                        monedaDao.insertar(Moneda(0,"FKP","Falkland Islands Pound","Falkland Islands"))
                        monedaDao.insertar(Moneda(0,"FOK","Faroese Króna","Faroe Islands"))
                        monedaDao.insertar(Moneda(0,"GBP","Pound Sterling","United Kingdom"))
                        monedaDao.insertar(Moneda(0,"GEL","Georgian Lari","Georgia"))
                        monedaDao.insertar(Moneda(0,"GGP","Guernsey Pound","Guernsey"))
                        monedaDao.insertar(Moneda(0,"GHS","Ghanaian Cedi","Ghana"))
                        monedaDao.insertar(Moneda(0,"GIP","Gibraltar Pound","Gibraltar"))
                        monedaDao.insertar(Moneda(0,"GMD","Gambian Dalasi","The Gambia"))
                        monedaDao.insertar(Moneda(0,"GNF","Guinean Franc","Guinea"))
                        monedaDao.insertar(Moneda(0,"GTQ","Guatemalan Quetzal","Guatemala"))
                        monedaDao.insertar(Moneda(0,"GYD","Guyanese Dollar","Guyana"))
                        monedaDao.insertar(Moneda(0,"HKD","Hong Kong Dollar","Hong Kong"))
                        monedaDao.insertar(Moneda(0,"HNL","Honduran Lempira","Honduras"))
                        monedaDao.insertar(Moneda(0,"HRK","Croatian Kuna","Croatia"))
                        monedaDao.insertar(Moneda(0,"HTG","Haitian Gourde","Haiti"))
                        monedaDao.insertar(Moneda(0,"HUF","Hungarian Forint","Hungary"))
                        monedaDao.insertar(Moneda(0,"IDR","Indonesian Rupiah","Indonesia"))
                        monedaDao.insertar(Moneda(0,"ILS","Israeli New Shekel","Israel"))
                        monedaDao.insertar(Moneda(0,"IMP","Manx Pound","Isle of Man"))
                        monedaDao.insertar(Moneda(0,"INR","Indian Rupee","India"))
                        monedaDao.insertar(Moneda(0,"IQD","Iraqi Dinar","Iraq"))
                        monedaDao.insertar(Moneda(0,"IRR","Iranian Rial","Iran"))
                        monedaDao.insertar(Moneda(0,"ISK","Icelandic Króna","Iceland"))
                        monedaDao.insertar(Moneda(0,"JEP","Jersey Pound","Jersey"))
                        monedaDao.insertar(Moneda(0,"JMD","Jamaican Dollar","Jamaica"))
                        monedaDao.insertar(Moneda(0,"JOD","Jordanian Dinar","Jordan"))
                        monedaDao.insertar(Moneda(0,"JPY","Japanese Yen","Japan"))
                        monedaDao.insertar(Moneda(0,"KES","Kenyan Shilling","Kenya"))
                        monedaDao.insertar(Moneda(0,"KGS","Kyrgyzstani Som","Kyrgyzstan"))
                        monedaDao.insertar(Moneda(0,"KHR","Cambodian Riel","Cambodia"))
                        monedaDao.insertar(Moneda(0,"KID","Kiribati Dollar","Kiribati"))
                        monedaDao.insertar(Moneda(0,"KMF","Comorian Franc","Comoros"))
                        monedaDao.insertar(Moneda(0,"KRW","South Korean Won","South Korea"))
                        monedaDao.insertar(Moneda(0,"KWD","Kuwaiti Dinar","Kuwait"))
                        monedaDao.insertar(Moneda(0,"KYD","Cayman Islands Dollar","Cayman Islands"))
                        monedaDao.insertar(Moneda(0,"KZT","Kazakhstani Tenge","Kazakhstan"))
                        monedaDao.insertar(Moneda(0,"LAK","Lao Kip","Laos"))
                        monedaDao.insertar(Moneda(0,"LBP","Lebanese Pound","Lebanon"))
                        monedaDao.insertar(Moneda(0,"LKR","Sri Lanka Rupee","Sri Lanka"))
                        monedaDao.insertar(Moneda(0,"LRD","Liberian Dollar","Liberia"))
                        monedaDao.insertar(Moneda(0,"LSL","Lesotho Loti","Lesotho"))
                        monedaDao.insertar(Moneda(0,"LYD","Libyan Dinar","Libya"))
                        monedaDao.insertar(Moneda(0,"MAD","Moroccan Dirham","Morocco"))
                        monedaDao.insertar(Moneda(0,"MDL","Moldovan Leu","Moldova"))
                        monedaDao.insertar(Moneda(0,"MGA","Malagasy Ariary","Madagascar"))
                        monedaDao.insertar(Moneda(0,"MKD","Macedonian Denar","North Macedonia"))
                        monedaDao.insertar(Moneda(0,"MMK","Burmese Kyat","Myanmar"))
                        monedaDao.insertar(Moneda(0,"MNT","Mongolian Tögrög","Mongolia"))
                        monedaDao.insertar(Moneda(0,"MOP","Macanese Pataca","Macau"))
                        monedaDao.insertar(Moneda(0,"MRU","Mauritanian Ouguiya","Mauritania"))
                        monedaDao.insertar(Moneda(0,"MUR","Mauritian Rupee","Mauritius"))
                        monedaDao.insertar(Moneda(0,"MVR","Maldivian Rufiyaa","Maldives"))
                        monedaDao.insertar(Moneda(0,"MWK","Malawian Kwacha","Malawi"))
                        monedaDao.insertar(Moneda(0,"MXN","Mexican Peso","Mexico"))
                        monedaDao.insertar(Moneda(0,"MYR","Malaysian Ringgit","Malaysia"))
                        monedaDao.insertar(Moneda(0,"MZN","Mozambican Metical","Mozambique"))
                        monedaDao.insertar(Moneda(0,"NAD","Namibian Dollar","Namibia"))
                        monedaDao.insertar(Moneda(0,"NGN","Nigerian Naira","Nigeria"))
                        monedaDao.insertar(Moneda(0,"NIO","Nicaraguan Córdoba","Nicaragua"))
                        monedaDao.insertar(Moneda(0,"NOK","Norwegian Krone","Norway"))
                        monedaDao.insertar(Moneda(0,"NPR","Nepalese Rupee","Nepal"))
                        monedaDao.insertar(Moneda(0,"NZD","New Zealand Dollar","New Zealand"))
                        monedaDao.insertar(Moneda(0,"OMR","Omani Rial","Oman"))
                        monedaDao.insertar(Moneda(0,"PAB","Panamanian Balboa","Panama"))
                        monedaDao.insertar(Moneda(0,"PEN","Peruvian Sol","Peru"))
                        monedaDao.insertar(Moneda(0,"PGK","Papua New Guinean Kina","Papua New Guinea"))
                        monedaDao.insertar(Moneda(0,"PHP","Philippine Peso","Philippines"))
                        monedaDao.insertar(Moneda(0,"PKR","Pakistani Rupee","Pakistan"))
                        monedaDao.insertar(Moneda(0,"PLN","Polish Złoty","Poland"))
                        monedaDao.insertar(Moneda(0,"PYG","Paraguayan Guaraní","Paraguay"))
                        monedaDao.insertar(Moneda(0,"QAR","Qatari Riyal","Qatar"))
                        monedaDao.insertar(Moneda(0,"RON","Romanian Leu","Romania"))
                        monedaDao.insertar(Moneda(0,"RSD","Serbian Dinar","Serbia"))
                        monedaDao.insertar(Moneda(0,"RUB","Russian Ruble","Russia"))
                        monedaDao.insertar(Moneda(0,"RWF","Rwandan Franc","Rwanda"))
                        monedaDao.insertar(Moneda(0,"SAR","Saudi Riyal","Saudi Arabia"))
                        monedaDao.insertar(Moneda(0,"SBD","Solomon Islands Dollar","Solomon Islands"))
                        monedaDao.insertar(Moneda(0,"SCR","Seychellois Rupee","Seychelles"))
                        monedaDao.insertar(Moneda(0,"SDG","Sudanese Pound","Sudan"))
                        monedaDao.insertar(Moneda(0,"SEK","Swedish Krona","Sweden"))
                        monedaDao.insertar(Moneda(0,"SGD","Singapore Dollar","Singapore"))
                        monedaDao.insertar(Moneda(0,"SHP","Saint Helena Pound","Saint Helena"))
                        monedaDao.insertar(Moneda(0,"SLE","Sierra Leonean Leone","Sierra Leone"))
                        monedaDao.insertar(Moneda(0,"SOS","Somali Shilling","Somalia"))
                        monedaDao.insertar(Moneda(0,"SRD","Surinamese Dollar","Suriname"))
                        monedaDao.insertar(Moneda(0,"SSP","South Sudanese Pound","South Sudan"))
                        monedaDao.insertar(Moneda(0,"STN","São Tomé and Príncipe Dobra","São Tomé and Príncipe"))
                        monedaDao.insertar(Moneda(0,"SYP","Syrian Pound","Syria"))
                        monedaDao.insertar(Moneda(0,"SZL","Eswatini Lilangeni","Eswatini"))
                        monedaDao.insertar(Moneda(0,"THB","Thai Baht","Thailand"))
                        monedaDao.insertar(Moneda(0,"TJS","Tajikistani Somoni","Tajikistan"))
                        monedaDao.insertar(Moneda(0,"TMT","Turkmenistan Manat","Turkmenistan"))
                        monedaDao.insertar(Moneda(0,"TND","Tunisian Dinar","Tunisia"))
                        monedaDao.insertar(Moneda(0,"TOP","Tongan Paʻanga","Tonga"))
                        monedaDao.insertar(Moneda(0,"TRY","Turkish Lira","Turkey"))
                        monedaDao.insertar(Moneda(0,"TTD","Trinidad and Tobago Dollar","Trinidad and Tobago"))
                        monedaDao.insertar(Moneda(0,"TVD","Tuvaluan Dollar","Tuvalu"))
                        monedaDao.insertar(Moneda(0,"TWD","New Taiwan Dollar","Taiwan"))
                        monedaDao.insertar(Moneda(0,"TZS","Tanzanian Shilling","Tanzania"))
                        monedaDao.insertar(Moneda(0,"UAH","Ukrainian Hryvnia","Ukraine"))
                        monedaDao.insertar(Moneda(0,"UGX","Ugandan Shilling","Uganda"))
                        monedaDao.insertar(Moneda(0,"USD","United States Dollar","United States"))
                        monedaDao.insertar(Moneda(0,"UYU","Uruguayan Peso","Uruguay"))
                        monedaDao.insertar(Moneda(0,"UZS","Uzbekistani So'm","Uzbekistan"))
                        monedaDao.insertar(Moneda(0,"VES","Venezuelan Bolívar Soberano","Venezuela"))
                        monedaDao.insertar(Moneda(0,"VND","Vietnamese Đồng","Vietnam"))
                        monedaDao.insertar(Moneda(0,"VUV","Vanuatu Vatu","Vanuatu"))
                        monedaDao.insertar(Moneda(0,"WST","Samoan Tālā","Samoa"))
                        monedaDao.insertar(Moneda(0,"XAF","Central African CFA Franc","CEMAC"))
                        monedaDao.insertar(Moneda(0,"XCD","East Caribbean Dollar","Organisation of Eastern Caribbean States"))
                        monedaDao.insertar(Moneda(0,"XDR","Special Drawing Rights","International Monetary Fund"))
                        monedaDao.insertar(Moneda(0,"XOF","West African CFA franc","CFA"))
                        monedaDao.insertar(Moneda(0,"XPF","CFP Franc","Collectivités d'Outre-Mer"))
                        monedaDao.insertar(Moneda(0,"YER","Yemeni Rial","Yemen"))
                        monedaDao.insertar(Moneda(0,"ZAR","South African Rand","South Africa"))
                        monedaDao.insertar(Moneda(0,"ZMW","Zambian Kwacha","Zambia"))
                        monedaDao.insertar(Moneda(0,"ZWL","Zimbabwean Dollar","Zimbabwe"))

                    }
                }

            }
    }

    companion object{
        @Volatile
        private var INSTANCE: MiDbMonedas?=null

        val databaseexecutor :
                ExecutorService =
            Executors.newFixedThreadPool(4)

        fun getDatabase(context: Context, scope: CoroutineScope): MiDbMonedas {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context, MiDbMonedas::class.java,
                    "midbmonedas"
                ).addCallback(MiDbMonedasCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}