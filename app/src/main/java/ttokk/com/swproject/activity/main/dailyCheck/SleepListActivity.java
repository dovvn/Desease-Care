package ttokk.com.swproject.activity.main.dailyCheck;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import ttokk.com.swproject.adapter.SleepAdapter;
import ttokk.com.swproject.model.sqliteDB.Sleep;
import ttokk.com.swproject.R;
import ttokk.com.swproject.sqliteHelper.SleepSQLiteHelper;

public class SleepListActivity extends AppCompatActivity {

    //db
    SleepSQLiteHelper helper;
    SQLiteDatabase db;
    private static final String dbName = "sleepTBL.db";
    private static final String tableName = "sleepTBL";


    ArrayList<Sleep> wl = new ArrayList<Sleep>();
    SleepAdapter adapter;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_list);


        lv = (ListView) findViewById(R.id.water_list);

        // db 연결
        helper = new SleepSQLiteHelper(SleepListActivity.this, dbName, null, 3);
        db = helper.getReadableDatabase();

        //selectAll
        Cursor c = db.query(tableName, null, null, null, null, null, null);

        c.moveToLast();
        do {
            int sleep_hour = c.getInt(c.getColumnIndex("sleep_hour"));
            int sleep_minute = c.getInt(c.getColumnIndex("sleep_minute"));
            int sleep_sum = c.getInt(c.getColumnIndex("sleep_sum"));
            String date = c.getString(c.getColumnIndex("date"));

            Sleep w = new Sleep();
            w.sleep_hour = sleep_hour;
            w.sleep_minute = sleep_minute;
            w.sleep_sum = sleep_sum;
            w.date = date;
            wl.add(w);

            adapter = new SleepAdapter(SleepListActivity.this, wl, R.layout.sleep_row);
            lv.setAdapter(adapter);
        }
        while (c.moveToPrevious());
        Log.i("DB select", "완료");


        //리스트 선택 시 수정페이지로 이동
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //선택한 position의 날짜
                Log.i("선택한 position의 날짜", wl.get(position).getDate() + "");

                Intent intent = new Intent(SleepListActivity.this, SleepModiActivity.class);

                //선택된 리스트뷰의 데이터 객체 생성
                Sleep selectData = new Sleep();
                selectData.setSleep_hour(wl.get(position).getSleep_hour());
                selectData.setSleep_minute(wl.get(position).getSleep_minute());
                selectData.setDate(wl.get(position).getDate());

                intent.putExtra("selectData", selectData);
                startActivity(intent);
            }
        });
    }

    //뒤로가기
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(SleepListActivity.this, SleepActivity.class);
        startActivity(intent);
    }
}
