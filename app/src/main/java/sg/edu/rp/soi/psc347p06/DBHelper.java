package sg.edu.rp.soi.psc347p06;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "revisionnote.db";

    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_TASK = "task";

    private static final String COLUMN_ID = "_id";

    private static final String COLUMN_TASK = "task";

    private static final String COLUMN_TASK_CONTENT = "task_content";

    private static final String COLUMN_SEC = "sec";


    public DBHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }


    @Override

    public void onCreate(SQLiteDatabase db) {



        String createNoteTableSql = "CREATE TABLE " + TABLE_TASK + "("

                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"

                + COLUMN_TASK + " TEXT, "

                + COLUMN_TASK_CONTENT + " TEXT, " + COLUMN_SEC + " INTEGER )";

        db.execSQL(createNoteTableSql);

        Log.i("info", "created tables");

    }


    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);

        onCreate(db);

    }


    public void inserTask(String task, String taskContent, int sec) {



        SQLiteDatabase db = this.getWritableDatabase();



        ContentValues values = new ContentValues();



        values.put(COLUMN_TASK, task);


        values.put(COLUMN_TASK_CONTENT, taskContent);


        values.put(COLUMN_SEC, sec);


        db.insert(TABLE_TASK, null, values);


        db.close();

    }


    public ArrayList<Task> getAllTasks() {

        ArrayList<Task> notes = new ArrayList<Task>();



        String selectQuery = "SELECT * FROM " + TABLE_TASK;


        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);



        if (cursor.moveToFirst()) {

            do {

                int id = cursor.getInt(0);

                String taskTitle = cursor.getString(1);

                String taskContent = cursor.getString(2);

                int sec = cursor.getInt(3);


                Task task = new Task(id, taskTitle, taskContent, sec);

                notes.add(task);

            } while (cursor.moveToNext());

        }



        cursor.close();

        db.close();

        return notes;

    }


    public ArrayList<String> getTask() {

        //TODO return records in Strings




        ArrayList<String> notes = new ArrayList<String>();



        String selectQuery = "SELECT " + COLUMN_TASK + " FROM "

                + TABLE_TASK;




        SQLiteDatabase db = this.getReadableDatabase();



        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {



            do {


                notes.add(cursor.getString(0));

            } while (cursor.moveToNext());

        }


        cursor.close();

        db.close();


        return notes;

    }


}