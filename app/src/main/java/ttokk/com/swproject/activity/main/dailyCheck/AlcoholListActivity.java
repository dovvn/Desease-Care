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

import ttokk.com.swproject.adapter.AlcoholAdapter;
import ttokk.com.swproject.model.sqliteDB.Alcohol;
import ttokk.com.swproject.R;
import ttokk.com.swproject.sqliteHelper.AlcoholSQLiteHelper;

public class AlcoholListActivity extends AppCompatActivity {

    //db
    AlcoholSQLiteHelper helper;
    SQLiteDatabase db;
    private static final String dbName = "alcoholTBL.db";
    private static final String tableName = "alcoholTBL";
    ArrayList<Alcohol> wl = new ArrayList<Alcohol>();
    ListView lv;
    AlcoholAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alcohol_list);

        lv = (ListView) findViewById(R.id.alcohol_list);
        // db 연결
        helper = new AlcoholSQLiteHelper(AlcoholListActivity.this, dbName, null, 3);
        db = helper.getReadableDatabase();

        //selectAll
        Cursor c = db.query(tableName, null, null, null, null, null, null);

        c.moveToLast();
        do {
            int alcohol_data = c.getInt(c.getColumnIndex("alcohol_data"));
            String date = c.getString(c.getColumnIndex("date"));

            Alcohol w = new Alcohol();
            w.alcohol_data = alcohol_data;
            w.date = date;
            wl.add(w);

            adapter = new AlcoholAdapter(AlcoholListActivity.this, wl, R.layout.alcohol_row);
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

                Intent intent = new Intent(AlcoholListActivity.this, AlcoholModiActivity.class);

                //선택된 리스트뷰의 데이터 객체 생성
                Alcohol selectData = new Alcohol();
                selectData.setAlcohol_data(wl.get(position).getAlcohol_data());
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
        Intent intent = new Intent(AlcoholListActivity.this, AlcoholActivity.class);
        startActivity(intent);
    }
}
