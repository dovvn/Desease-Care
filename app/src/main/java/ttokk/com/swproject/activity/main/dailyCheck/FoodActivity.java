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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import ttokk.com.swproject.model.sqliteDB.FoodExcel;
import ttokk.com.swproject.R;
import ttokk.com.swproject.sqliteHelper.FoodSQLiteHelper;
import ttokk.com.swproject.activity.main.MainActivity;

public class FoodActivity extends AppCompatActivity {

    //db
    FoodSQLiteHelper helper;
    SQLiteDatabase db;
    String dbName = "foodTBL.db";
    String tableName = "foodTBL";

    ArrayList foodArray;
    ArrayAdapter foodAdapter;
    Spinner food_time;
    Button dateBtn, food_submit;
    TextView dateView, inputFoodBtn, food_name, food_count, food_kcal, go_food_list;
    protected int year, month, date;
    FoodExcel foodData;
    int foodCount = 0;
    LinearLayout lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        //달력
        final Calendar cal = Calendar.getInstance();
        year = cal.get(cal.YEAR);
        month = cal.get(cal.MONTH) + 1;
        date = cal.get(cal.DATE);

        food_time = (Spinner) findViewById(R.id.food_time);
        dateBtn = (Button) findViewById(R.id.date_btn);
        food_submit = (Button) findViewById(R.id.food_submit);
        dateView = (TextView) findViewById(R.id.date_view);
        go_food_list = (TextView) findViewById(R.id.go_food_list);
        go_food_list.setClickable(true);
        dateView.setText(year + "년 " + month + "월 " + date + "일"); //현재 날짜 초기화

        //FoodInfo에서 가져온 리스트뷰의 데이터
        Intent intent = getIntent();
        foodCount = intent.getIntExtra("foodCount", 0); //음식 개수
        Log.i("foodcount", "" + foodCount);
        foodData = (FoodExcel) intent.getSerializableExtra("foodData");//음식 객체

        //스피너 등록
        foodArray = new ArrayList<>();
        foodArray.add("아침");
        foodArray.add("점심");
        foodArray.add("저녁");

        food_time = (Spinner) findViewById(R.id.food_time); //시간
        foodAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, foodArray);
        food_time.setAdapter(foodAdapter);

        //음식 등록 리니어
        lv = (LinearLayout) findViewById(R.id.layout);

        food_name = (TextView) findViewById(R.id.food_name); //음식 이름
        food_count = (TextView) findViewById(R.id.food_count);//음식 개수
        food_kcal = (TextView) findViewById(R.id.food_kcal);//음식 칼로리
        inputFoodBtn = (TextView) findViewById(R.id.input_food_btn);

        lv.setVisibility(View.INVISIBLE); //숨기기

        //음식을 선택하면
        if (foodCount != 0) {
            lv.setVisibility(View.VISIBLE); //숨기기
            food_name.setText(foodData.getName());
            food_count.setText(String.valueOf(foodCount)); //ing>string
            food_kcal.setText(String.valueOf(foodData.getKcal())); //int>string

            //버튼 안보이게
            inputFoodBtn.setVisibility(View.INVISIBLE);
        }

        //db생성
        helper = new FoodSQLiteHelper(this, dbName, null, 3);

        //insert후 목록 이동
        food_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = helper.getWritableDatabase();
                Log.i("현재 db url", db + "");

                //칼럼 명칭과 짝
                ContentValues values = new ContentValues();

                //spinner
                String foodTime = food_time.getSelectedItem().toString();
                values.put("food_name", food_name.getText().toString());//음식 이름
                values.put("food_count", Integer.parseInt(food_count.getText().toString()));//음식 개수
                values.put("food_kcal", Integer.parseInt(food_kcal.getText().toString()));//음식 칼로리
                values.put("food_time", foodTime);//음식 시간
                values.put("date", dateView.getText().toString());//날짜

                db.insert(tableName, null, values);
                Intent intent = new Intent(FoodActivity.this, FoodListActivity.class);
                startActivity(intent);
            }
        });

        //목록
        go_food_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = helper.getWritableDatabase();
                Cursor cursor = db.query(tableName, null, null, null, null, null, null);

                //레코드가 있는 경우
                if (cursor != null && cursor.moveToFirst()) {
                    Intent intent = new Intent(FoodActivity.this, FoodListActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(FoodActivity.this, "등록된 데이터가 없습니다 ",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        //날짜 설정 다이얼로그
        dateBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                DatePickerDialog dialog = new DatePickerDialog(FoodActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int date) {

                        String msg = String.format("%d 년 %d 월 %d 일", year, month + 1, date);
                        Toast.makeText(FoodActivity.this, msg, Toast.LENGTH_SHORT).show();
                        dateView.setText(year + "년 " + (month + 1) + "월 " + date + "일");
                    }
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));

                dialog.getDatePicker().setMaxDate(new Date().getTime());    //입력한 날짜 이후로 클릭 안되게 옵션
                dialog.show();
            }
        });


        //기록 버튼
        inputFoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(FoodActivity.this, FoodSearchActivity.class);
                startActivity(intent);
            }
        });
    }

    //뒤로 가기
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(FoodActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
