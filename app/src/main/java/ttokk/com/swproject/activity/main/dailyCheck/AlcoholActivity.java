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

import com.amitshekhar.DebugDB;

import java.util.Calendar;
import java.util.Date;

import ttokk.com.swproject.R;
import ttokk.com.swproject.sqliteHelper.AlcoholSQLiteHelper;
import ttokk.com.swproject.activity.main.MainActivity;

public class AlcoholActivity extends AppCompatActivity implements View.OnClickListener {

    //db
    AlcoholSQLiteHelper helper;
    SQLiteDatabase db;
    String dbName = "alcoholTBL.db";
    String tableName = "alcoholTBL";

    protected int year, month, date;
    Button btnAdd, btnMinus, dateBtn, alcohol_submit;
    TextView alcoholCounter, goAlcoholList, dateView;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alcohol);


        final Calendar cal = Calendar.getInstance();
        year = cal.get(cal.YEAR);
        month = cal.get(cal.MONTH) + 1;
        date = cal.get(cal.DATE);

        btnAdd = (Button) findViewById(R.id.addButton);
        btnMinus = (Button) findViewById(R.id.minusButton);
        dateBtn = (Button) findViewById(R.id.date_btn);
        alcohol_submit = (Button) findViewById(R.id.alcohol_submit);

        alcoholCounter = (TextView) findViewById(R.id.alcoholView);
        goAlcoholList = (TextView) findViewById(R.id.go_alcohol_list);
        dateView = (TextView) findViewById(R.id.date_view);
        dateView.setText(year + "년 " + month + "월 " + date + "일"); //현재 날짜 초기화

        btnAdd.setOnClickListener(this);
        btnMinus.setOnClickListener(this);

        Log.i("주소: ",""+DebugDB.getAddressLog());

        //db생성
        helper = new AlcoholSQLiteHelper(this, dbName, null, 3);

        //insert후 목록 이동
        alcohol_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = helper.getWritableDatabase();
                Log.i("현재 db url", db + "");

                //칼럼 명칭과 짝
                ContentValues values = new ContentValues();

                values.put("alcohol_data", Integer.parseInt(alcoholCounter.getText().toString()));
                values.put("date", dateView.getText().toString());

                Log.i("alcohol_data", Integer.parseInt(alcoholCounter.getText().toString()) + "");
                Log.i("date", dateView.getText().toString() + "");

                db.insert(tableName, null, values);
                Intent intent = new Intent(AlcoholActivity.this, AlcoholListActivity.class);
                startActivity(intent);
            }
        });


        //목록
        goAlcoholList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = helper.getWritableDatabase();
                Cursor cursor = db.query(tableName, null, null, null, null, null, null);

                //레코드가 있는 경우
                if (cursor != null && cursor.moveToFirst()) {
                    Intent intent = new Intent(AlcoholActivity.this, AlcoholListActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(AlcoholActivity.this, "등록된 데이터가 없습니다 ",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        //날짜 설정 다이얼로그
        dateBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                DatePickerDialog dialog = new DatePickerDialog(AlcoholActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int date) {

                        String msg = String.format("%d 년 %d 월 %d 일", year, month + 1, date);
                        Toast.makeText(AlcoholActivity.this, msg, Toast.LENGTH_SHORT).show();
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
                break;
            case R.id.minusButton:
                if (count == 0) {
                    break;
                }
                count--;
                break;
            default:
                break;
        }
        alcoholCounter.setText(String.valueOf(count));
    }

    //뒤로 가기
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(AlcoholActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
