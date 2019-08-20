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

import ttokk.com.swproject.adapter.WaterAdapter;
import ttokk.com.swproject.model.sqliteDB.Water;
import ttokk.com.swproject.R;
import ttokk.com.swproject.sqliteHelper.WaterSQLiteHelper;

public class WaterListActivity extends AppCompatActivity {

    WaterSQLiteHelper helper;
    SQLiteDatabase db;
    ArrayList<Water> wl = new ArrayList<Water>();
    WaterAdapter adapter;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_list);


        lv = (ListView) findViewById(R.id.water_list);

        // db 연결
        helper = new WaterSQLiteHelper(WaterListActivity.this, "waterTBL.db", null, 3);
        db = helper.getReadableDatabase();

        //selectAll
        Cursor c = db.query("waterTBL", null, null, null, null, null, null);

        c.moveToLast();
        do {
            int water_data = c.getInt(c.getColumnIndex("water_data"));
            String date = c.getString(c.getColumnIndex("date"));
            int water_ml = c.getInt(c.getColumnIndex("water_ml"));

            Water w = new Water();
            w.water_data = water_data;
            w.water_ml = water_ml;
            w.date = date;
            wl.add(w);

            adapter = new WaterAdapter(WaterListActivity.this, wl, R.layout.water_row);
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

                Intent intent = new Intent(WaterListActivity.this, WaterModiActivity.class);

                //선택된 리스트뷰의 데이터 객체 생성
                Water selectData = new Water();
                selectData.setWater_data(wl.get(position).getWater_data());
                selectData.setDate(wl.get(position).getDate());
                selectData.setWater_ml(wl.get(position).getWater_ml());

                intent.putExtra("selectData", selectData);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(WaterListActivity.this, WaterActivity.class);
        startActivity(intent);
    }
}
