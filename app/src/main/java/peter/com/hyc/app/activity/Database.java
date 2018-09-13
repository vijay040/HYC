package peter.com.hyc.app.activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Lenovo on 12-09-2018.
 */

public class Database extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION=1;
    private static final   String DATABASE_NAME="icourse";
    private static final String DATABASE_TABLE="course_tbl";

    public Database(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase arg0) {

        arg0. execSQL("create table course_tbl( name text, code text,color text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {


    }

    public void saveData( String n,String c,String color)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues cn=new ContentValues();

        cn.put("name" , n);
        cn.put("code" , c);
        cn.put("color" , color);
        db.insert(DATABASE_TABLE , null , cn);


    }

    public Cursor fetchData()
    {
        Cursor cursor=null;

        SQLiteDatabase db= this.getReadableDatabase();

        cursor=db.rawQuery("select * from course_tbl" , null);

        if(cursor!=null)
        {
            cursor.moveToFirst();
        }

        return  cursor;
    }


  /*  public void deleteData(int R)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete("student" , "rno"+"="+R , null);
    }
    */

    public void updateData(int R , String N)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues cn=new ContentValues();

        cn.put("name" , N);

        db.update("student" , cn ,"rno"+"="+R , null);
    }
    public void deleteDB(){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete("student", null, null);

    }





}


