package com.midlandstech.student.treywyates.libraryrentals;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

//Controls all of our DB functions
public class DatabaseManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "RentalDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_RENTAL = "Rental";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String BOOK_NAME = "bookName";
    private static final String DATE = "date";

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Creates the DB fields
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate = "create table " + TABLE_RENTAL + "( " + ID;
        sqlCreate += " integer primary key autoincrement, " + NAME;
        sqlCreate += " text, " + BOOK_NAME + " text, " + DATE + " text )";

        db.execSQL(sqlCreate);
    }

    //Replaces the old DB with the updated current one
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop old table if it exists
        db.execSQL("drop table if exists " + TABLE_RENTAL);
        // Re-create tables
        onCreate(db);
    }

    //Inserts the rental into the database
    public void insert(Rental rental) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into " + TABLE_RENTAL;
        sqlInsert += " values( null, '" + rental.getName();
        sqlInsert += "', '" + rental.getBookName() + "', '" + rental.getDate() + "' )";

        db.execSQL(sqlInsert);
        db.close();
    }

    //Selects all entries in the database
    public ArrayList<Rental> selectAll() {
        String sqlQuery = "select * from " + TABLE_RENTAL;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        ArrayList<Rental> rentals = new ArrayList<Rental>();
        while (cursor.moveToNext()) {
            Rental currentRental
                    = new Rental(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getString(2),
                    cursor.getString(3));
            rentals.add(currentRental);
        }
        db.close();
        return rentals;
    }
}
