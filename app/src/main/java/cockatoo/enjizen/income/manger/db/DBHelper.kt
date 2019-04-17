package cockatoo.enjizen.income.manger.db

import android.content.ContentValues
import cockatoo.enjizen.income.constant.DBContract
import cockatoo.enjizen.income.manger.Contextor
import cockatoo.enjizen.income.manger.KeyEncryptData
import net.sqlcipher.Cursor
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SQLiteException
import net.sqlcipher.database.SQLiteOpenHelper

class DBHelper : SQLiteOpenHelper(Contextor.getInstance().context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ACCOUNT_TABLE)
        sqLiteDatabase.execSQL(SQL_CREATE_BANK_TABLE)
        sqLiteDatabase.execSQL(SQL_CREATE_INCOME_TABLE)
        sqLiteDatabase.execSQL(SQL_CREATE_OUT_INCOME_TABLE)
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {

    }

    fun insert(tableName: String, values: ContentValues ) {
        val db = instance!!.getWritableDatabase(pass)
        db.insert(tableName, null, values)
        db.close()

    }

    fun insert(tableName: String, values:  ArrayList<ContentValues> ) {
        val db = instance!!.getWritableDatabase(pass)
        values.forEach {
            db.insert(tableName, null, it)
        }

        db.close()

    }

    fun update(tableName: String, values: ContentValues, columnsValue: String, id: String) {
        val db = instance!!.getWritableDatabase(pass)
        var selection = ""

        columnsValue.forEach {
            selection += "$it = ?"
        }
        db.update(tableName, values, selection, arrayOf(id))
        db.close()

    }

    fun delete(tableName: String, columnsValue: String, id: String) {
        val db = instance!!.getWritableDatabase(pass)

        val values = ContentValues()
        values.put(id, id)
        db.delete(tableName, "$columnsValue='$id'", null)
        db.close()

    }


    fun getAll(tableName: String): Cursor? {
        val db = instance!!.getReadableDatabase(pass)

        return try {
            val cursor = db.query(
                tableName
                , null// value - null will give all
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

    fun rawQuery(sql: String) : Cursor{
        val db = instance!!.getReadableDatabase(pass)
        return db.rawQuery(sql, null)
    }


    fun get(tableName: String, columnsValue: Array<String>, value: Array<String>): Cursor? {
        val db = instance!!.getReadableDatabase(pass)

        var selection = ""

        columnsValue.forEach {
            selection += "$it = ?"
        }

        return try {
            val cursor = db.query(
                tableName
                , null// value - null will give all
                , selection// selection
                , value// selection arguments
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

        private const val DATABASE_VERSION = DBContract.DATABASE_VERSION
        private const val DATABASE_NAME = DBContract.DATABASE_INCOME


        private val pass = KeyEncryptData.getInstance().key // password encrypt

        private val SQL_CREATE_ACCOUNT_TABLE = "CREATE TABLE ${DBContract.AccountEntry.TABLE_NAME.value} ( " +
                "${DBContract.AccountEntry.COLUMN_ID.value} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${DBContract.AccountEntry.COLUMN_BANK_ID.value} INT," +
                "${DBContract.AccountEntry.COLUMN_ACCOUNT_NUMBER.value} TEXT," +
                "${DBContract.AccountEntry.COLUMN_NAME.value} TEXT," +
                "${DBContract.AccountEntry.COLUMN_BALANCE.value} NUMERIC(10,2) NOT NULL DEFAULT 0.00)"


        private val SQL_CREATE_BANK_TABLE = "CREATE TABLE ${DBContract.BankEntry.TABLE_NAME.value} (" +
                "${DBContract.BankEntry.COLUMN_ID.value} INTEGER PRIMARY KEY ," +
                " ${DBContract.BankEntry.COLUMN_NAME.value} TEXT, " +
                "${DBContract.BankEntry.COLUMN_INITIALS.value} TEXT," +
                " ${DBContract.BankEntry.COLUMN_LOGO.value} TEXT)"

        private val SQL_CREATE_INCOME_TABLE ="CREATE TABLE ${DBContract.IncomeEntry.TABLE_NAME.value} (" +
                "${DBContract.IncomeEntry.COLUMN_ID.value} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${DBContract.IncomeEntry.COLUMN_DETAIL.value} TEXT," +
                "${DBContract.IncomeEntry.COLUMN_MONEY_INCOME.value} NUMERIC(10,2) NOT NULL DEFAULT 0.00," +
                "${DBContract.IncomeEntry.COLUMN_ACCOUNT_ID.value} INT," +
                "${DBContract.IncomeEntry.COLUMN_CREATED_DATE.value} DATE)"

        private val SQL_CREATE_OUT_INCOME_TABLE ="CREATE TABLE ${DBContract.OutcomeEntry.TABLE_NAME.value} (" +
                "${DBContract.OutcomeEntry.COLUMN_ID.value} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${DBContract.OutcomeEntry.COLUMN_DETAIL.value} TEXT," +
                "${DBContract.OutcomeEntry.COLUMN_MONEY_OUTCOME.value} NUMERIC(10,2) NOT NULL DEFAULT 0.00," +
                "${DBContract.OutcomeEntry.COLUMN_ACCOUNT_ID.value} INT," +
                "${DBContract.OutcomeEntry.COLUMN_CREATED_DATE.value} DATE)"

        @Synchronized
        fun getInstance(): DBHelper {
            if (instance == null)
                instance = DBHelper()
            return instance as DBHelper
        }
    }
}
