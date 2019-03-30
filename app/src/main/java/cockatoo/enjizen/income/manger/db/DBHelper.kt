package cockatoo.enjizen.income.manger.db

import android.content.ContentValues
import cockatoo.enjizen.income.constant.DBContract
import cockatoo.enjizen.income.manger.Contextor
import cockatoo.enjizen.income.manger.KeyEncryptData
import net.sqlcipher.Cursor
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SQLiteException
import net.sqlcipher.database.SQLiteOpenHelper

/**
 * Created by reale on 10/26/2017.
 */

class DBHelper : SQLiteOpenHelper(Contextor.getInstance().context, DATABASE_NAME, null, DATABASE_VERSION) {
   /* val allEmail: List<String>
        get() {
            val db = instance!!.getWritableDatabase(PASS_PHARSE)

            val cursor = db.rawQuery(String.format("SELECT * FROM '%s';", TABLE_NAME), null)
            val emails = ArrayList<String>()
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast) {
                    val email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL))
                    emails.add(email)
                    cursor.moveToNext()
                }
            }
            cursor.close()
            db.close()

            return emails
        }*/



    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ACCOUNT_TABLE)
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {
        sqLiteDatabase.execSQL(SQL_DELETE_ACCOUNT_TABLE)
        onCreate(sqLiteDatabase)
    }


    //CRUD Method
    fun insert(tableName: String, values: ContentValues ) {
        val db = instance!!.getWritableDatabase(PASS_PHARSE)
        db.insert(tableName, null, values)
        db.close()

    }

    fun update(tableName: String, values: ContentValues, columnsValue: String, id: String) {
        val db = instance!!.getWritableDatabase(PASS_PHARSE)

      /*  val values = ContentValues()
        values.put(COLUMN_EMAIL, newEmail)*/
        db.update(tableName, values, "id= ?", arrayOf(id))
        db.close()

    }

    fun delete(tableName: String, columnsValue: String, id: String) {
        val db = instance!!.getWritableDatabase(PASS_PHARSE)

        val values = ContentValues()
        values.put(id, id)
        db.delete(tableName, "$columnsValue='$id'", null)
        db.close()

    }

    fun getAll(): Cursor? {
        val db = instance!!.getWritableDatabase(PASS_PHARSE)

        return try {
            val cursor = db.query(
                DBContract.AccountEntry.TABLE_NAME.columns
                , null// columns - null will give all
                , null// selection
                , null// selection arguments
                , null// groupBy
                , null// having
                , null// no need or order by for now;
            )

            cursor
        }catch (ex: SQLiteException){
            null
        }
    }


    companion object {

        private var instance: DBHelper? = null

        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "Income.db"


        private val PASS_PHARSE = KeyEncryptData.getInstance().key // password encrypt

        private val SQL_CREATE_ACCOUNT_TABLE = "CREATE TABLE ${DBContract.AccountEntry.TABLE_NAME.columns} ( " +
                "${DBContract.AccountEntry.COLUMN_ID.columns} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${DBContract.AccountEntry.COLUMN_ACCOUNT_NUMBER.columns} TEXT," +
                "${DBContract.AccountEntry.COLUMN_NAME.columns} TEXT," +
                "${DBContract.AccountEntry.COLUMN_BALANCE.columns} NUMERIC(10,2) NOT NULL DEFAULT 0.00)"

        private val SQL_DELETE_ACCOUNT_TABLE = "DROP TABLE IF EXISTS ${DBContract.AccountEntry.TABLE_NAME.columns}"

        @Synchronized
        fun getInstance(): DBHelper {
            if (instance == null)
                instance = DBHelper()
            return instance as DBHelper
        }
    }
}
