package ttokk.com.swproject.activity.main;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import ttokk.com.swproject.R;
import ttokk.com.swproject.activity.main.ToolBar.ChangeServeyActivity;
import ttokk.com.swproject.activity.main.ToolBar.developerActivity;
import ttokk.com.swproject.activity.main.ToolBar.editmemberActivity;
import ttokk.com.swproject.activity.main.dailyCheck.AlcoholActivity;
import ttokk.com.swproject.activity.main.dailyCheck.ExerciseActivity;
import ttokk.com.swproject.activity.main.dailyCheck.FoodActivity;
import ttokk.com.swproject.activity.main.dailyCheck.SleepActivity;
import ttokk.com.swproject.activity.main.dailyCheck.SmokeActivity;
import ttokk.com.swproject.activity.main.dailyCheck.WaterActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView card_exercise, card_water, card_sleep, card_food, card_alcohol, card_smoke;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false); //타이틀 문자열 표시 설정

        card_exercise = (CardView) findViewById(R.id.card_exercise);
        card_water = (CardView) findViewById(R.id.card_water);
        card_sleep = (CardView) findViewById(R.id.card_sleep);
        card_food = (CardView) findViewById(R.id.card_food);
        card_alcohol = (CardView) findViewById(R.id.card_alcohol);
        card_smoke = (CardView) findViewById(R.id.card_smoke);

        card_exercise.setOnClickListener(this);
        card_water.setOnClickListener(this);
        card_sleep.setOnClickListener(this);
        card_food.setOnClickListener(this);
        card_alcohol.setOnClickListener(this);
        card_smoke.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.card_exercise:
                i = new Intent(this, ExerciseActivity.class);
                startActivity(i);
                break;
            case R.id.card_water:
                i = new Intent(this, WaterActivity.class);
                startActivity(i);
                break;
            case R.id.card_sleep:
                i = new Intent(this, SleepActivity.class);
                startActivity(i);
                break;
            case R.id.card_food:
                i = new Intent(this, FoodActivity.class);
                startActivity(i);
                break;
            case R.id.card_alcohol:
                i = new Intent(this, AlcoholActivity.class);
                startActivity(i);
                break;
            case R.id.card_smoke:
                i = new Intent(this, SmokeActivity.class);
                startActivity(i);
                break;
            default:
                break;
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.Item1:
                Intent intent = new Intent(MainActivity.this, editmemberActivity.class);
                startActivity(intent);
                break;
            case R.id.Item2:
                Intent intent2 = new Intent(MainActivity.this, ChangeServeyActivity.class);
                startActivity(intent2);
                break;
            case R.id.Item3:
                Intent intent3 = new Intent(MainActivity.this, developerActivity.class);
                startActivity(intent3);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
