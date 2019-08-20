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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import ttokk.com.swproject.R;
import ttokk.com.swproject.sqliteHelper.SleepSQLiteHelper;
import ttokk.com.swproject.activity.main.MainActivity;

public class SleepActivity extends AppCompatActivity {
    //달력
    protected int year, month, date;

    //db
    SleepSQLiteHelper helper;
    SQLiteDatabase db;
    private static final String dbName = "sleepTBL.db";
    private static final String tableName = "sleepTBL";

    ArrayList<Integer> hourList;
    ArrayList<Integer> minuteList;

    ArrayAdapter<Integer> arrayAdapter1;
    ArrayAdapter<Integer> arrayAdapter2;

    Spinner hourData, minuteData;
    Button dateBtn, sleep_submit;
    TextView dateView, go_sleep_list;
    int sleep_sum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);

        //spinner
        hourList = new ArrayList<Integer>(
                Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20));

        minuteList = new ArrayList<Integer>();
        for (int i = 0; i <= 59; i++) {
            minuteList.add(i);
        }

        go_sleep_list = (TextView) findViewById(R.id.go_sleep_list); //목록 이동

        hourData = (Spinner) findViewById(R.id.hour_data);
        minuteData = (Spinner) findViewById(R.id.minute_data);

        arrayAdapter1 = new ArrayAdapter<Integer>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, hourList);
        arrayAdapter2 = new ArrayAdapter<Integer>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, minuteList);

        hourData.setAdapter(arrayAdapter1);
        minuteData.setAdapter(arrayAdapter2);

        sleep_submit = (Button) findViewById(R.id.sleep_submit);

        //db생성
        helper = new SleepSQLiteHelper(this, dbName, null, 3);

        //spinner 시간->분으로 변경
        sleep_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hour = hourData.getSelectedItem().toString();
                String minute = minuteData.getSelectedItem().toString();
                sleep_sum = Integer.parseInt(hour) * 60 + Integer.parseInt(minute);//최종 데이터
                Log.i("sleep_data", sleep_sum + "");

                //db처리
                db = helper.getWritableDatabase();
                Log.i("현재 db url", db + "");

                ContentValues values = new ContentValues();

                values.put("sleep_hour", Integer.parseInt(hour)); //수면 시
                values.put("sleep_minute", Integer.parseInt(minute)); //수면 분
                values.put("sleep_sum", sleep_sum); //총 수면 시간(분)
                values.put("date", dateView.getText().toString()); //날짜

                Log.i("sleep_hour", hour + "");
                Log.i("sleep_minute", minute + "");
                Log.i("sleep_sum", sleep_sum + "");
                Log.i("data", dateView.getText().toString() + "");

                db.insert(tableName, null, values);
                Intent intent = new Intent(SleepActivity.this, SleepListActivity.class);
                startActivity(intent);
            }
        });

        //목록
        go_sleep_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = helper.getWritableDatabase();
                Cursor cursor = db.query(tableName, null, null, null, null, null, null);

                //레코드가 있는 경우
                if (cursor != null && cursor.moveToFirst()) {
                    Intent intent = new Intent(SleepActivity.this, SleepListActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(SleepActivity.this, "등록된 데이터가 없습니다 ",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        //달력
        final Calendar cal = Calendar.getInstance();
        year = cal.get(cal.YEAR);
        month = cal.get(cal.MONTH) + 1;
        date = cal.get(cal.DATE);

        dateBtn = (Button) findViewById(R.id.date_btn);
        dateView = (TextView) findViewById(R.id.date_view);
        dateView.setText(year + "년 " + month + "월 " + date + "일"); //현재 날짜 초기화


        //날짜 설정 다이얼로그
        dateBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                DatePickerDialog dialog = new DatePickerDialog(SleepActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                        String msg = String.format("%d 년 %d 월 %d 일", year, month + 1, date);
                        Toast.makeText(SleepActivity.this, msg, Toast.LENGTH_SHORT).show();
                        dateView.setText(year + "년 " + (month + 1) + "월 " + date + "일");

                    }
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));

                dialog.getDatePicker().setMaxDate(new Date().getTime());    //입력한 날짜 이후로 클릭 안되게 옵션
                dialog.show();
            }
        });
    }

    //뒤로 가기
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(SleepActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
