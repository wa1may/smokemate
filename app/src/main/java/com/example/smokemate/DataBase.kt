package com.example.smokemate

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DataBase(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    private val context: Context? = context

    companion object {
        private const val DATABASE_NAME = "AccountData.db"
        private const val DATABASE_VERSION = 1

        const val TABLE_USERS = "Users"
        const val COLUMN_ID = "_id"
        const val COLUMN_LOGIN = "login"
        const val COLUMN_PASSWORD = "password"
        const val COLUMN_DAY = "day"
        const val COLUMN_MONTH = "month"
        const val COLUMN_YEAR = "year"

        const val TABLE_USER_DATA = "UserData"
        const val COLUMN_ID_USER_DATA = "_id"
        const val COLUMN_NAME_USER_DATA = "user_name"
        const val COLUMN_SURNAME_USER_DATA = "user_surname"
        const val COLUMN_PHONE_NUMBER_USER_DATA = "phone_number"
        const val COLUMN_COUNTRY_USER_DATA = "country"
        const val COLUMN_CITY_USER_DATA = "city"
        const val COLUMN_CIGARETTE_USER_DATA = "cigarette"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val queryUsers = ("CREATE TABLE " + TABLE_USERS
                + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_LOGIN + " TEXT, "
                + COLUMN_PASSWORD + " TEXT, "
                + COLUMN_DAY + " TEXT, "
                + COLUMN_MONTH + " TEXT, "
                + COLUMN_YEAR + " TEXT); ")

        val queryUserData = ("CREATE TABLE " + TABLE_USER_DATA
                + " (" + COLUMN_ID_USER_DATA + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME_USER_DATA + " TEXT, "
                + COLUMN_SURNAME_USER_DATA + " TEXT, "
                + COLUMN_PHONE_NUMBER_USER_DATA + " TEXT, "
                + COLUMN_COUNTRY_USER_DATA + " TEXT, "
                + COLUMN_CITY_USER_DATA + " TEXT, "
                + COLUMN_CIGARETTE_USER_DATA + " TEXT); ")

        db.execSQL(queryUsers)
        db.execSQL(queryUserData)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USER_DATA")
        onCreate(db)
    }

    fun addUsers(login: String, password: String, day: String, month: String, year: String) {
        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put(COLUMN_LOGIN, login)
        cv.put(COLUMN_PASSWORD, password)
        cv.put(COLUMN_DAY, day)
        cv.put(COLUMN_MONTH, month)
        cv.put(COLUMN_YEAR, year)

        val result = db.insert(TABLE_USERS, null, cv)
        if (result == -1L) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show()
        }
    }

    fun addUserData(name: String, surname: String, phoneNumber: String, country: String, city: String, cigarette: String) {
        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put(COLUMN_NAME_USER_DATA, name)
        cv.put(COLUMN_SURNAME_USER_DATA, surname)
        cv.put(COLUMN_PHONE_NUMBER_USER_DATA, phoneNumber)
        cv.put(COLUMN_COUNTRY_USER_DATA, country)
        cv.put(COLUMN_CITY_USER_DATA, city)
        cv.put(COLUMN_CIGARETTE_USER_DATA, cigarette)

        val result = db.insert(TABLE_USER_DATA, null, cv)
        if (result == -1L) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show()
        }
    }

    fun authenticateUser(login: String, password: String): Boolean {
        val db = this.readableDatabase
        val columns = arrayOf(COLUMN_ID, COLUMN_LOGIN, COLUMN_PASSWORD)
        val selection = "$COLUMN_LOGIN = ? AND $COLUMN_PASSWORD = ?"
        val selectionArgs = arrayOf(login, password)

        val cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null)
        val count = cursor.count
        cursor.close()

        return count > 0
    }

    fun authenticateUserByPhoneNumber(phoneNumber: String, password: String): Boolean {
        val db = this.readableDatabase
        val columns = arrayOf(COLUMN_ID_USER_DATA, COLUMN_PHONE_NUMBER_USER_DATA, COLUMN_PASSWORD)
        val selection = "$COLUMN_PHONE_NUMBER_USER_DATA = ? AND $COLUMN_PASSWORD = ?"
        val selectionArgs = arrayOf(phoneNumber, password)

        val cursor = db.query(TABLE_USER_DATA, columns, selection, selectionArgs, null, null, null)
        val count = cursor.count
        cursor.close()

        return count > 0
    }

    fun isPhoneNumberExists(phoneNumber: String): Boolean {
        val db = this@DataBase.readableDatabase
        val columns = arrayOf(COLUMN_PHONE_NUMBER_USER_DATA)
        val selection = "$COLUMN_PHONE_NUMBER_USER_DATA = ?"
        val selectionArgs = arrayOf(phoneNumber)

        val cursor = db.query(TABLE_USER_DATA, columns, selection, selectionArgs, null, null, null)
        val count = cursor.count
        cursor.close()

        return count > 0
    }
}