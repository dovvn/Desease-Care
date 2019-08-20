package ttokk.com.swproject.activity.main.dailyCheck;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.eazegraph.lib.BuildConfig;
import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import ttokk.com.swproject.R;
import ttokk.com.swproject.activity.main.MainActivity;

import static java.lang.Math.*;

public class ExerciseActivity extends AppCompatActivity {

    TextView stepsView, steps, unit;
    PieModel sliceGoal, sliceCurrent;
    PieChart pg;
    SensorListener listener;
    SensorManager manager;
    int DEFAULT_GOAL = 10000; //목표 걸음수

    int todayOffset, goal, since_boot;
    NumberFormat formatter = NumberFormat.getInstance(Locale.getDefault());
    boolean showSteps = true;

    TextView date_view;
    TextView km_length;

    //걸음수>km
    double km;
    int stemps_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        date_view = (TextView) findViewById(R.id.date_view);
        unit = (TextView) findViewById(R.id.unit);
        km_length = (TextView) findViewById(R.id.km_length);
        Date today = new Date();

        SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
        date_view.setText(date.format(today));

        stepsView = (TextView) findViewById(R.id.steps);
        steps = (TextView) findViewById(R.id.steps);
        listener = new SensorListener();

        pg = (PieChart) findViewById(R.id.graph);


        sliceCurrent = new PieModel("", 0, Color.parseColor("#43BFB0")); //현재 걸음수
        pg.addPieSlice(sliceCurrent);

//        //걸음수 > km
//        String str = steps.getText().toString();
//        Log.v("str",str+"");
//        if(str == null || str.trim().equals("")){
//            str = "0";
//        }
//        stemps_value = Integer.parseInt(str); //걸음 수
//        Log.i("unit_value",stemps_value+"");
//        km = Math.round((stemps_value * 0.0007) * 100) / 100.0;
//        Log.i("km값",km+"");
//        km_length.setText(Double.toString(km));

        sliceGoal = new PieModel("", DEFAULT_GOAL, Color.parseColor("#C8C7C7")); //목표 걸음수
        pg.addPieSlice(sliceGoal);

        pg.setDrawValueInPie(false);
        pg.setUsePieRotation(true);
        pg.startAnimation();
    }


    @Override
    public void onResume() {
        super.onResume();

        if (BuildConfig.DEBUG) ;

        SharedPreferences prefs = getSharedPreferences("", Context.MODE_PRIVATE);

        goal = prefs.getInt("goal", DEFAULT_GOAL);
        int pauseDifference = since_boot - prefs.getInt("", since_boot);

        manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = manager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        manager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_UI);


        since_boot -= pauseDifference;


    }

    public void updatePie() {

        int steps_today = max(todayOffset + since_boot, 0);
        float km = (float)steps_today/(float)1000;

        sliceCurrent.setValue(steps_today);
        if (goal - steps_today > 0) {
            if (pg.getData().size() == 1) {
                pg.addPieSlice(sliceGoal);
            }
            sliceGoal.setValue(goal - steps_today);
        } else {
            // goal reached
            pg.clearChart();
            pg.addPieSlice(sliceCurrent);
        }
        pg.update();
        if (showSteps) {
            stepsView.setText(formatter.format(steps_today));
            km_length.setText(formatter.format(km));

        } else {
            SharedPreferences prefs = getSharedPreferences("", Context.MODE_PRIVATE);
        }
    }

    class SensorListener implements SensorEventListener {
        @Override
        public void onAccuracyChanged(final Sensor sensor, int accuracy) {

        }

        @Override
        public void onSensorChanged(final SensorEvent event) {

            if (event.values[0] > Integer.MAX_VALUE || event.values[0] == 0) {
                return;
            }
            if (todayOffset == Integer.MIN_VALUE) {

                todayOffset = -(int) event.values[0];

            }
            since_boot = (int) event.values[0];
            updatePie();
        }
    }

    //뒤로 가기
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ExerciseActivity.this, MainActivity.class);
        startActivity(intent);
    }
}


