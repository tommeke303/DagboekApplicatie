package be.hogent.dagboekapplicatie.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

/**
 * Created by jbuy519 on 23/10/2014.
 */
public class MyDB {

    private static final String TAG = "MyDB.class";

    private MyDBHelper dbHelper;
    private SQLiteDatabase database;
    private String[] allColumns = { Constants.ID,
            Constants.TITLE, Constants.CONTENT, Constants.DATE };
    /**
     * Initialises this DB. Make sure the dbHelper object is also initialised.
     * @param context
     */
    public MyDB(Context context) {
        dbHelper = new MyDBHelper(context, Constants.DATABASE, null, Constants.VERSION);
    }

    /**
     * Closes the database
     */
    public void close(){
        dbHelper.close();
    }
    /**
     * Tries to open a writable database. If this is not possible, open a readable database.
     * Log the necessary messages for debugging purposes.
     */
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    /**
     * Adds an diary entry to the database.
     * http://developer.android.com/reference/android/database/sqlite/SQLiteDatabase.html#insert%28java.lang.String,%20java.lang.String,%20android.content.ContentValues%29
     * @return the row ID of the newly inserted row, or -1 if an error occurred
     */
    public long insertDiary(ContentValues newDiaryValue){
        try {
            long insertId = database.insert(Constants.TABLE, null,
                    newDiaryValue);
            return insertId;
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return 0;
    }

    /**
     * Fetches all diary entries from the database
     * @return The diary entries from the database.
     */
    public Cursor getDiaries(){
        try {
            Cursor cursor = database.query(Constants.TABLE,
                    allColumns, null, null, null, null, null);
            return cursor;
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
       return null;
    }
}
