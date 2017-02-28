package com.example.android.drinkingappwithlogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.ResultSet;
import java.sql.Statement;

import static android.R.attr.name;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;


/**
 * Created by jpower707 on 20/02/2017.
 */

public class Sqlite extends SQLiteOpenHelper {

    public static final String database="db";
    public static final String table="users";
    public static final String id_col="id";
    public static final String name_col="name";
    public static final String surname_col="surname";
    public static final String email_col="email";
    public static final String password_col="password";
    public static final int version = 1;

    public Sqlite(Context context)
    {
        super(context, database, null, version);
    }
        @Override
        public void onCreate(SQLiteDatabase db)
        {
            String query = " CREATE TABLE " + table + "("+
                    id_col+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    name_col+ " TEXT NOT NULL, " +
                    surname_col+ " TEXT NOT NULL, " +
                    email_col+ " TEXT NOT NULL, " +
                    password_col+ " TEXT NOT NULL )";

            db.execSQL(query);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion , int newVersion)
        {
            db.execSQL("DROP TABLE IF EXISTS " + table);
            onCreate(db);

        }
//    public boolean SearchEmail(String Email) {
//        try {
//            // our SQL SELECT query.
//            // if you only need a few columns, specify them by name instead of using "*"
//            String query = "SELECT * FROM users";
//
//            // create the java statement
//            Statement st = conn.createStatement();
//
//            // execute the query, and get a java resultset
//            ResultSet rs = st.executeQuery(query);
//
//            // iterate through the java resultset
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String firstName = rs.getString("first_name");
//                String lastName = rs.getString("Surname");
//                boolean isAdmin = rs.getBoolean("is_admin");
//                int numPoints = rs.getInt("num_points");
//
//
//            }
//
//        }
//        catch (Exception e)
//        {
//            System.err.println("Got an exception! ");
//            System.err.println(e.getMessage());
//        }
//        st.close();
//        return false;
//    }

    public boolean AddData(String name, String surname, String email, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(name_col,name);
        cv.put(surname_col,surname);
        cv.put(email_col,email);
        cv.put(password_col,password);

        long result  = db.insert(table, null, cv);
        if (result == -1) {
            return false;
        }
        else{
            return true;
        }
    }
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+table,null);
        return res;
    }

    public boolean updateData(String id,String name,String surname,String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(id_col,id);
        contentValues.put(name_col,name);
        contentValues.put(surname_col,surname);
        contentValues.put(email_col,email);
        contentValues.put(password_col,password);
        db.update(table, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(table, "ID = ?",new String[] {id});
    }
}