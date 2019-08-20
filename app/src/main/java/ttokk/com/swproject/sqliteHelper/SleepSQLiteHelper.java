package ttokk.com.swproject.sqliteHelper;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SleepSQLiteHelper extends SQLiteOpenHelper {

    public SleepSQLiteHelper(Context context, String name,
                             CursorFactory factory, int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub
    }

    //1. getWritableDatabase() 혹은 getWritableDatabase()가 실행될 때
    //2. 시스템 안에 open으로 지정한 db파일이 없다면 실행된다.
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

        String sql = "create table if not exists sleepTBL" +
                "(nmb integer primary key autoincrement, " +
                "sleep_hour integer, " +
                "sleep_minute integer, " +
                "sleep_sum integer, " +
                "date text);";
        db.execSQL(sql);
        Log.i("생성","생성");
    }

    //db버전이 바뀔때
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        String sql = "drop table if exists water;";
        onCreate(db);
    }
}