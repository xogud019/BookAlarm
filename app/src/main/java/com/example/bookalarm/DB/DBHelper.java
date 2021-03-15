package com.example.bookalarm.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {
    private Context context;
    public static final String DATABASE_NAME = "bookalarm.db";
    public static final String TALBE_NAME = "BOOKS";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "TITLE";
    public static final String COL_3 = "PUB";
    public static final String COL_4 = "AUT";
    public static final String COL_5 = "PUBDATE";
    public static final String COL_6 = "COVERSMALLURL";
    public static final String COL_7 = "PRICE";
    public static final String COL_8 = "SALEPRICE";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TALBE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT, TITLE TEXT,PUB TEXT,AUT TEXT,PUBDATE TEXT,COVERSMALLURL TEXT,PRICE TEXT,SALEPRICE TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TALBE_NAME);
        onCreate(db);
        Toast.makeText(context, "버전이 올라갔습니다.", Toast.LENGTH_SHORT).show();
    }
    public void testDB() {
        SQLiteDatabase db = getReadableDatabase();
        Toast.makeText(context, "Table 생성완료", Toast.LENGTH_SHORT).show();
    }
    public boolean insertData(String title, String pub, String aut, String pubDate,String url, String price, String salePrice) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("TITLE", title);
        values.put("PUB", pub);
        values.put("AUT", aut);
        values.put("PUBDATE", pubDate);
        values.put("COVERSMALLURL", url);
        values.put("PRICE", price);
        values.put("SALEPRICE", salePrice);

        long result = db.insert(TALBE_NAME,null,values);

        return result==-1?false:true;
    }

    public Cursor getAllBookData() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM "+ TALBE_NAME, null);

        return cur;
    }

    public int deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TALBE_NAME, "ID = ?", new String[] {id});
    }

    public Cursor getID(String title){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT ID FROM "+TALBE_NAME+"WHERE "+title+"= TITLE",null);

        return cur;
    }
}
