package be.hogent.dagboekapplicatie.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by jbuy519 on 23/10/2014.
 */
public class MyDBHelper extends SQLiteOpenHelper{


    private static final String TAG =" MyDBHelper.class";

    private static final String CREATE_TABLE ="CREATE TABLE "+Constants.TABLE+ " (" + Constants.ID + " integer primary key autoincrement, " + Constants.TITLE + " text not null, " + Constants.CONTENT + " text not null, " + Constants.DATE + " long);";


    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * Generates the tables in the  de SQLiteDatabase
     * @param db The SQLIteDatabase to add the tables to
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    /**
     * Performs an upgrade of the database in case of version mismatch.
     * Implementation here is to drop all the tables and generate new tables.
     * For actual implementations this should perform a clean data migration
     * @param db The database to update
     * @param oldVersion The old version of the database
     * @param newVersion The new version to perform the update for
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE);
        onCreate(db);
    }
}
