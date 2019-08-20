package ttokk.com.swproject.activity.main.dailyCheck;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import ttokk.com.swproject.sqliteHelper.WaterSQLiteHelper;
import ttokk.com.swproject.R;
import ttokk.com.swproject.activity.main.MainActivity;

public class WaterActivity extends AppCompatActivity implements View.OnClickListener {
    WaterSQLiteHelper helper;
    SQLiteDatabase db;

    Button btnAdd, btnMinus, dateBtn;
    TextView waterView, water_ml, goWaterList, dateView, water_submit;


    protected int year, month, date;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);

        final Calendar cal = Calendar.getInstance();
        year = cal.get(cal.YEAR);
        month = cal.get(cal.MONTH) + 1;
        date = cal.get(cal.DATE);

        btnAdd = (Button) findViewById(R.id.addButton);
        btnMinus = (Button) findViewById(R.id.minusButton);
        dateBtn = (Button) findViewById(R.id.date_btn);
        water_submit = (Button) findViewById(R.id.water_submit);

        waterView = (TextView) findViewById(R.id.water_view); //물 입력
        dateView = (TextView) findViewById(R.id.date_view); //날짜 입력

        water_ml = (TextView) findViewById(R.id.waterml);
        goWaterList = (TextView) findViewById(R.id.go_water_list); //목록 보기
        dateView.setText(year + "년 " + month + "월 " + date + "일"); //default 현재 날짜

        btnAdd.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        //db생성
        helper = new WaterSQLiteHelper(this, "waterTBL.db", null, 3);

        //insert후 목록 이동
        water_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = helper.getWritableDatabase();
                Log.i("현재 db url", db + "");
                //칼럼 명칭과 짝
                ContentValues values = new ContentValues();

                values.put("water_data", Integer.parseInt(waterView.getText().toString()));
                values.put("date", dateView.getText().toString());
                values.put("water_ml", Integer.parseInt(water_ml.getText().toString()));

                Log.i("water_data", Integer.parseInt(waterView.getText().toString()) + "");
                Log.i("date", dateView.getText().toString() + "");
                Log.i("water_ml", Integer.parseInt(water_ml.getText().toString()) + "");

                db.insert("waterTBL", null, values);
                Intent intent = new Intent(WaterActivity.this, WaterListActivity.class);
                startActivity(intent);
            }
        });

        //목록
        goWaterList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = helper.getWritableDatabase();
                Cursor cursor = db.query("waterTBL", null, null, null, null, null, null);

                //레코드가 있는 경우
                if (cursor != null && cursor.moveToFirst()) {
                    Intent intent = new Intent(WaterActivity.this, WaterListActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(WaterActivity.this, "등록된 데이터가 없습니다 ",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        //날짜 설정 다이얼로그
        dateBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                DatePickerDialog dialog = new DatePickerDialog(WaterActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int date) {

                        String msg = String.format("%d 년 %d 월 %d 일", year, month + 1, date);
                        Toast.makeText(WaterActivity.this, msg, Toast.LENGTH_SHORT).show();
                        dateView.setText(year + "년 " + (month + 1) + "월 " + date + "일");
                    }
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));

                dialog.getDatePicker().setMaxDate(new Date().getTime());    //입력한 날짜 이후로 클릭 안되게 옵션
                dialog.show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addButton:
                count++;
                water_ml.setText(String.valueOf(count * 250));
                break;
            case R.id.minusButton:
                if (count == 0) {
                    break;
                }
                count--;
                water_ml.setText(String.valueOf(count * 250));
                break;
            default:
                break;
        }
        waterView.setText(String.valueOf(count));
    }

    //뒤로 가기
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(WaterActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
