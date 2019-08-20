package ttokk.com.swproject.activity.login;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import ttokk.com.swproject.R;
import ttokk.com.swproject.activity.main.MainActivity;

public class ServeyActivity extends AppCompatActivity {

    Button go_mainBtn;
    Spinner D_1_1, BO1_1, BS3_1, BE5_1, BO2_1, DI1_2, DI2_2, BP1;

    RadioButton radio1, radio2, radio3, radio4, radio5, radio6, radio7, radio8, radio9, radio10, radio11, radio12, radio13, radio14, radio15, radio16, radio17, radio18, radio19, radio20, radio21, radio22, radio23, radio24, radio25, radio26, radio27, radio28, radio29, radio30, radio31, radio32, radio33, radio34, radio35, radio36, radio37, radio38, radio39, radio40, radio41, radio42, radio43, radio44, radio45, radio46, radio47, radio48, radio49, radio50, radio51, radio52, radio53, radio54, radio55, radio56, radio57, radio58, radio59, radio60, radio61, radio62, radio63, radio64, radio65, radio66, radio67;

    RadioGroup sex, BH2_61, HE_fh, HE_HPfh, HE_HLfh, HE_IHDfh, HE_STRfh, HE_HBfh, HE_DMfh, DI1_dg, DI2_dg, DI3_dg, DI4_dg, DJ2_dg,
            DE1_dg, DE1_32, DC1_dg, DC2_dg, DC6_dg, DJ8_dg, DJ6_dg, DK8_dg, DK9_dg, DK4_dg, DJ4_dg;

    TextView age, BS3_2, HE_sput2, EC_wht_23, Total_slp_wk, Total_slp_wd, HE_ht, HE_wt;

    String sex_1, BH2_61_1, HE_fh_1, HE_HPfh_1, HE_HLfh_1, HE_IHDfh_1, HE_STRfh_1, HE_HBfh_1, HE_DMfh_1, DI1_dg_1, DI2_dg_1, DI3_dg_1, DI4_dg_1,
            DJ2_dg_1, DE1_dg_1, DE1_32_1, DC1_dg_1, DC2_dg_1, DC6_dg_1, DJ8_dg_1, DJ6_dg_1, DK8_dg_1, DK9_dg_1, DK4_dg_1, DJ4_dg_1, D_1_1_1, BO1_1_1, BS3_1_1, BE5_1_1, BO2_1_1, DI1_2_1, DI2_2_1, BP1_1;

    String[] data1 = {
            "1. 매우좋음", "2. 좋음", "3. 보통", "4. 나쁨", "5. 매우나쁨"
    };

    String[] data2 = {
            "1. 변화없음", "2. 체중감소", "3. 증가", "8. 비해당(청소년, 소아)"
    };
    String[] data3 = {
            "1. 매일", "2. 가끔", "3. 과거에피웠으나현재안피움", "4. 비해당 (비흡연)"
    };
    String[] data4 = {
            "1. 안한다", "2. 1일", "3. 2일", "4. 3일", "5. 4일", "6. 5일 이상"
    };
    String[] data5 = {
            "1. 1~2잔", "2. 3~4잔", "3. 5~6잔", "4. 7~9잔", "5. 10잔 이상", "8. 비해당 (0잔)"
    };
    String[] data6 = {
            "1. 매일 복용함", "2. 한달에 20일 이상 복용한다", "3. 한달에 15일 이상 복용한다", "4. 한달에 15일 미만 복용한다", "5. 복용하지 않는다", "6. 비해당(청소년, 소아, 의사진단 받지 않음)"
    };
    String[] data7 = {
            "1. 매일 복용함", "2. 한달에 20일 이상 복용한다", "3. 한달에 15일 이상 복용한다", "4. 한달에 15일 미만 복용한다", "5. 복용하지 않는다", "6. 비해당(청소년, 소아)"
    };
    String[] data8 = {
            "1. 대단히 많이 느낀다.", "2. 많이 느끼는 편이다.", "3. 조금 느끼는 편이다.", "4. 거의 느끼지 않는다", "5. 해당하지 않는다(소아)"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_servey);

        findMethod();
        spinMethod();

    }

    public void btnMethod(View view) {
        if (radio64.isChecked()) {
            Intent intent = new Intent(ServeyActivity.this, HealthServeyActivity.class);
            startActivity(intent);
        } else if (radio65.isChecked()) {
            Intent intent = new Intent(ServeyActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(ServeyActivity.this, "건강검진 여부를 선택해주세요", Toast.LENGTH_SHORT).show();
        }

        NetworkThread thread = new NetworkThread();
        thread.start();
    }

    public void findMethod() {
        D_1_1 = findViewById(R.id.D_1_1);
        BO1_1 = findViewById(R.id.BO1_1);
        BS3_1 = findViewById(R.id.BS3_1);
        BE5_1 = findViewById(R.id.BE5_1);
        BO2_1 = findViewById(R.id.BO2_1);
        DI1_2 = findViewById(R.id.DI1_2);
        DI2_2 = findViewById(R.id.DI2_2);
        BP1 = findViewById(R.id.BP1);

        go_mainBtn = findViewById(R.id.go_main);
        radio1 = findViewById(R.id.radio1);
        radio2 = findViewById(R.id.radio2);
        radio3 = findViewById(R.id.radio3);
        radio4 = findViewById(R.id.radio4);
        radio5 = findViewById(R.id.radio5);
        radio6 = findViewById(R.id.radio6);
        radio7 = findViewById(R.id.radio7);
        radio8 = findViewById(R.id.radio8);
        radio9 = findViewById(R.id.radio9);
        radio10 = findViewById(R.id.radio10);
        radio11 = findViewById(R.id.radio11);
        radio12 = findViewById(R.id.radio12);
        radio13 = findViewById(R.id.radio13);
        radio14 = findViewById(R.id.radio14);
        radio15 = findViewById(R.id.radio15);
        radio16 = findViewById(R.id.radio16);
        radio17 = findViewById(R.id.radio17);
        radio18 = findViewById(R.id.radio18);
        radio19 = findViewById(R.id.radio19);
        radio20 = findViewById(R.id.radio20);
        radio21 = findViewById(R.id.radio21);
        radio22 = findViewById(R.id.radio22);
        radio23 = findViewById(R.id.radio23);
        radio24 = findViewById(R.id.radio24);
        radio25 = findViewById(R.id.radio25);
        radio26 = findViewById(R.id.radio26);
        radio27 = findViewById(R.id.radio27);
        radio28 = findViewById(R.id.radio28);
        radio29 = findViewById(R.id.radio29);
        radio30 = findViewById(R.id.radio30);
        radio31 = findViewById(R.id.radio31);
        radio32 = findViewById(R.id.radio32);
        radio33 = findViewById(R.id.radio33);
        radio34 = findViewById(R.id.radio34);
        radio35 = findViewById(R.id.radio35);
        radio36 = findViewById(R.id.radio36);
        radio37 = findViewById(R.id.radio37);
        radio38 = findViewById(R.id.radio38);
        radio39 = findViewById(R.id.radio39);
        radio40 = findViewById(R.id.radio40);
        radio41 = findViewById(R.id.radio41);
        radio42 = findViewById(R.id.radio42);
        radio43 = findViewById(R.id.radio43);
        radio44 = findViewById(R.id.radio44);
        radio45 = findViewById(R.id.radio45);
        radio46 = findViewById(R.id.radio46);
        radio47 = findViewById(R.id.radio47);
        radio48 = findViewById(R.id.radio48);
        radio49 = findViewById(R.id.radio49);
        radio50 = findViewById(R.id.radio50);
        radio51 = findViewById(R.id.radio51);
        radio52 = findViewById(R.id.radio52);
        radio53 = findViewById(R.id.radio53);
        radio54 = findViewById(R.id.radio54);
        radio55 = findViewById(R.id.radio55);
        radio56 = findViewById(R.id.radio56);
        radio57 = findViewById(R.id.radio57);
        radio58 = findViewById(R.id.radio58);
        radio59 = findViewById(R.id.radio59);
        radio60 = findViewById(R.id.radio60);
        radio61 = findViewById(R.id.radio61);
        radio62 = findViewById(R.id.radio62);
        radio63 = findViewById(R.id.radio63);
        radio64 = findViewById(R.id.radio64);
        radio65 = findViewById(R.id.radio65);
        radio66 = findViewById(R.id.radio66);
        radio67 = findViewById(R.id.radio67);

        sex = findViewById(R.id.sex);
        BH2_61 = findViewById(R.id.BH2_61);
        HE_fh = findViewById(R.id.HE_fh);
        HE_HPfh = findViewById(R.id.HE_HPfh);
        HE_HLfh = findViewById(R.id.HE_HLfh);
        HE_IHDfh = findViewById(R.id.HE_IHDfh);
        HE_STRfh = findViewById(R.id.HE_STRfh);
        HE_HBfh = findViewById(R.id.HE_HBfh);
        HE_DMfh = findViewById(R.id.HE_DMfh);
        DI1_dg = findViewById(R.id.DI1_dg);
        DI2_dg = findViewById(R.id.DI2_dg);
        DI3_dg = findViewById(R.id.DI3_dg);
        DI4_dg = findViewById(R.id.DI4_dg);
        DJ2_dg = findViewById(R.id.DJ2_dg);
        DE1_dg = findViewById(R.id.DE1_dg);
        DE1_32 = findViewById(R.id.DE1_32);
        DC1_dg = findViewById(R.id.DC1_dg);
        DC2_dg = findViewById(R.id.DC2_dg);
        DC6_dg = findViewById(R.id.DC6_dg);
        DJ8_dg = findViewById(R.id.DJ8_dg);
        DJ6_dg = findViewById(R.id.DJ6_dg);
        DK8_dg = findViewById(R.id.DK8_dg);
        DK9_dg = findViewById(R.id.DK9_dg);
        DK4_dg = findViewById(R.id.DK4_dg);
        DJ4_dg = findViewById(R.id.DJ4_dg);

        age = findViewById(R.id.age);
        BS3_2 = findViewById(R.id.BS3_2);
        HE_sput2 = findViewById(R.id.HE_sput2);
        EC_wht_23 = findViewById(R.id.EC_wht_23);
        Total_slp_wk = findViewById(R.id.Total_slp_wk);
        Total_slp_wd = findViewById(R.id.Total_slp_wd);
        HE_ht = findViewById(R.id.HE_ht);
        HE_wt = findViewById(R.id.HE_wt);

    }

    public void spinMethod() {
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        D_1_1.setAdapter(adapter1);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        BO1_1.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data3);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        BS3_1.setAdapter(adapter3);

        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data4);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        BE5_1.setAdapter(adapter4);

        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data5);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        BO2_1.setAdapter(adapter5);

        ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data6);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        DI1_2.setAdapter(adapter6);

        ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data7);
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        DI2_2.setAdapter(adapter7);

        ArrayAdapter<String> adapter8 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data8);
        adapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        BP1.setAdapter(adapter8);
    }


    class NetworkThread extends Thread {
        @Override
        public void run() {
            super.run();

            try {
                OkHttpClient client = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                builder = builder.url("http://172.30.1.36:8080/sha/server.jsp");

                String selectedText1 = ((RadioButton) findViewById(sex.getCheckedRadioButtonId())).getText().toString();
                String selectedText2 = ((RadioButton) findViewById(BH2_61.getCheckedRadioButtonId())).getText().toString();
                String selectedText3 = ((RadioButton) findViewById(HE_fh.getCheckedRadioButtonId())).getText().toString();
                String selectedText4 = ((RadioButton) findViewById(HE_HPfh.getCheckedRadioButtonId())).getText().toString();
                String selectedText5 = ((RadioButton) findViewById(HE_HLfh.getCheckedRadioButtonId())).getText().toString();
                String selectedText6 = ((RadioButton) findViewById(HE_IHDfh.getCheckedRadioButtonId())).getText().toString();
                String selectedText7 = ((RadioButton) findViewById(HE_STRfh.getCheckedRadioButtonId())).getText().toString();
                String selectedText8 = ((RadioButton) findViewById(HE_HBfh.getCheckedRadioButtonId())).getText().toString();
                String selectedText9 = ((RadioButton) findViewById(HE_DMfh.getCheckedRadioButtonId())).getText().toString();
                String selectedText10 = ((RadioButton) findViewById(DI1_dg.getCheckedRadioButtonId())).getText().toString();

                String selectedText11 = ((RadioButton) findViewById(DI2_dg.getCheckedRadioButtonId())).getText().toString();
                String selectedText12 = ((RadioButton) findViewById(DI3_dg.getCheckedRadioButtonId())).getText().toString();
                String selectedText13 = ((RadioButton) findViewById(DI4_dg.getCheckedRadioButtonId())).getText().toString();
                String selectedText14 = ((RadioButton) findViewById(DJ2_dg.getCheckedRadioButtonId())).getText().toString();
                String selectedText15 = ((RadioButton) findViewById(DE1_dg.getCheckedRadioButtonId())).getText().toString();
                String selectedText16 = ((RadioButton) findViewById(DE1_32.getCheckedRadioButtonId())).getText().toString();
                String selectedText17 = ((RadioButton) findViewById(DC1_dg.getCheckedRadioButtonId())).getText().toString();
                String selectedText18 = ((RadioButton) findViewById(DC2_dg.getCheckedRadioButtonId())).getText().toString();
                String selectedText19 = ((RadioButton) findViewById(DC6_dg.getCheckedRadioButtonId())).getText().toString();
                String selectedText20 = ((RadioButton) findViewById(DJ8_dg.getCheckedRadioButtonId())).getText().toString();

                String selectedText21 = ((RadioButton) findViewById(DJ6_dg.getCheckedRadioButtonId())).getText().toString();
                String selectedText22 = ((RadioButton) findViewById(DK8_dg.getCheckedRadioButtonId())).getText().toString();
                String selectedText23 = ((RadioButton) findViewById(DK9_dg.getCheckedRadioButtonId())).getText().toString();
                String selectedText24 = ((RadioButton) findViewById(DK4_dg.getCheckedRadioButtonId())).getText().toString();
                String selectedText25 = ((RadioButton) findViewById(DJ4_dg.getCheckedRadioButtonId())).getText().toString();

                String Text26 = age.getText().toString();
                String Text27 = BS3_2.getText().toString();
                String Text28 = HE_sput2.getText().toString();
                String Text29 = EC_wht_23.getText().toString();
                String Text30 = Total_slp_wk.getText().toString();
                String Text31 = Total_slp_wd.getText().toString();
                String Text32 = HE_ht.getText().toString();
                String Text33 = HE_wt.getText().toString();

                String spn34 = D_1_1.getSelectedItem().toString();
                String spn35 = BO1_1.getSelectedItem().toString();
                String spn36 = BS3_1.getSelectedItem().toString();
                String spn37 = BE5_1.getSelectedItem().toString();
                String spn38 = BO2_1.getSelectedItem().toString();
                String spn39 = DI1_2.getSelectedItem().toString();
                String spn40 = DI2_2.getSelectedItem().toString();
                String spn41 = BP1.getSelectedItem().toString();

                int bmi2 = Integer.parseInt(Text32) * Integer.parseInt(Text32) / Integer.parseInt(Text33);
                String bmi = String.valueOf(bmi2);

                FormBody.Builder bodyBuilder = new FormBody.Builder();


                if (selectedText1.equals("남자")) {
                    sex_1 = "1";
                    bodyBuilder.add("data1", sex_1);
                } else {
                    sex_1 = "2";
                    bodyBuilder.add("data1", sex_1);
                }

                if (selectedText2.equals("예")) {
                    BH2_61_1 = "1";
                    bodyBuilder.add("data2", BH2_61_1);
                } else if (selectedText2.equals("아니오")) {
                    BH2_61_1 = "0";
                    bodyBuilder.add("data2", BH2_61_1);
                } else {
                    BH2_61_1 = "8";
                    bodyBuilder.add("data2", BH2_61_1);
                }

                if (selectedText3.equals("예")) {
                    HE_fh_1 = "1";
                    bodyBuilder.add("data3", HE_fh_1);
                } else {
                    HE_fh_1 = "0";
                    bodyBuilder.add("data3", HE_fh_1);
                }

                if (selectedText4.equals("예")) {
                    HE_HPfh_1 = "1";
                    bodyBuilder.add("data4", HE_HPfh_1);
                } else {
                    HE_HPfh_1 = "0";
                    bodyBuilder.add("data4", HE_HPfh_1);
                }

                if (selectedText5.equals("예")) {
                    HE_IHDfh_1 = "1";
                    bodyBuilder.add("data5", HE_IHDfh_1);
                } else {
                    HE_IHDfh_1 = "0";
                    bodyBuilder.add("data5", HE_IHDfh_1);
                }

                if (selectedText6.equals("예")) {
                    HE_STRfh_1 = "1";
                    bodyBuilder.add("data6", HE_STRfh_1);
                } else {
                    HE_STRfh_1 = "0";
                    bodyBuilder.add("data6", HE_STRfh_1);
                }

                if (selectedText7.equals("예")) {
                    HE_HBfh_1 = "1";
                    bodyBuilder.add("data7", HE_HBfh_1);
                } else {
                    HE_HBfh_1 = "0";
                    bodyBuilder.add("data7", HE_HBfh_1);
                }

                if (selectedText8.equals("예")) {
                    HE_DMfh_1 = "1";
                    bodyBuilder.add("data8", HE_DMfh_1);
                } else {
                    HE_DMfh_1 = "0";
                    bodyBuilder.add("data8", HE_DMfh_1);
                }

                if (selectedText9.equals("예")) {
                    DI1_dg_1 = "1";
                    bodyBuilder.add("data9", DI1_dg_1);
                } else if (selectedText9.equals("아니오")) {
                    DI1_dg_1 = "0";
                    bodyBuilder.add("data9", DI1_dg_1);
                } else {
                    DI1_dg_1 = "8";
                    bodyBuilder.add("data9", DI1_dg_1);
                }

                if (selectedText10.equals("예")) {
                    DI2_dg_1 = "1";
                    bodyBuilder.add("data10", DI2_dg_1);
                } else if (selectedText10.equals("아니오")) {
                    DI2_dg_1 = "0";
                    bodyBuilder.add("data10", DI2_dg_1);
                } else {
                    DI2_dg_1 = "8";
                    bodyBuilder.add("data10", DI2_dg_1);
                }

                if (selectedText11.equals("예")) {
                    DI3_dg_1 = "1";
                    bodyBuilder.add("data11", DI3_dg_1);
                } else if (selectedText11.equals("아니오")) {
                    DI3_dg_1 = "0";
                    bodyBuilder.add("data11", DI3_dg_1);
                } else {
                    DI3_dg_1 = "8";
                    bodyBuilder.add("data11", DI3_dg_1);
                }

                if (selectedText12.equals("예")) {
                    DI4_dg_1 = "1";
                    bodyBuilder.add("data12", DI4_dg_1);
                } else if (selectedText12.equals("아니오")) {
                    DI4_dg_1 = "0";
                    bodyBuilder.add("data12", DI4_dg_1);
                } else {
                    DI4_dg_1 = "8";
                    bodyBuilder.add("data12", DI4_dg_1);
                }

                if (selectedText13.equals("예")) {
                    DJ2_dg_1 = "1";
                    bodyBuilder.add("data13", DJ2_dg_1);
                } else if (selectedText13.equals("아니오")) {
                    DJ2_dg_1 = "0";
                    bodyBuilder.add("data13", DJ2_dg_1);
                } else {
                    DJ2_dg_1 = "8";
                    bodyBuilder.add("data13", DJ2_dg_1);
                }

                if (selectedText14.equals("예")) {
                    HE_HLfh_1 = "1";
                    bodyBuilder.add("data14", HE_HLfh_1);
                } else {
                    HE_HLfh_1 = "0";
                    bodyBuilder.add("data14", HE_HLfh_1);
                }

                if (selectedText15.equals("예")) {
                    DE1_dg_1 = "1";
                    bodyBuilder.add("data15", DE1_dg_1);
                } else {
                    DE1_dg_1 = "0";
                    bodyBuilder.add("data15", DE1_dg_1);
                }

                if (selectedText16.equals("예")) {
                    DE1_32_1 = "1";
                    bodyBuilder.add("data16", DE1_32_1);
                } else if (selectedText16.equals("아니오")) {
                    DE1_32_1 = "0";
                    bodyBuilder.add("data16", DE1_32_1);
                } else {
                    DE1_32_1 = "8";
                    bodyBuilder.add("data16", DE1_32_1);
                }

                if (selectedText17.equals("예")) {
                    DC1_dg_1 = "1";
                    bodyBuilder.add("data17", DC1_dg_1);
                } else if (selectedText17.equals("아니오")) {
                    DC1_dg_1 = "0";
                    bodyBuilder.add("data17", DC1_dg_1);
                } else {
                    DC1_dg_1 = "8";
                    bodyBuilder.add("data17", DC1_dg_1);
                }

                if (selectedText18.equals("예")) {
                    DC2_dg_1 = "1";
                    bodyBuilder.add("data18", DC2_dg_1);
                } else if (selectedText18.equals("아니오")) {
                    DC2_dg_1 = "0";
                    bodyBuilder.add("data18", DC2_dg_1);
                } else {
                    DC2_dg_1 = "8";
                    bodyBuilder.add("data18", DC2_dg_1);
                }

                if (selectedText19.equals("예")) {
                    DC6_dg_1 = "1";
                    bodyBuilder.add("data19", DC6_dg_1);
                } else if (selectedText19.equals("아니오")) {
                    DC6_dg_1 = "0";
                    bodyBuilder.add("data19", DC6_dg_1);
                } else {
                    DC6_dg_1 = "8";
                    bodyBuilder.add("data19", DC6_dg_1);
                }

                if (selectedText20.equals("예")) {
                    DJ8_dg_1 = "1";
                    bodyBuilder.add("data20", DJ8_dg_1);
                } else {
                    DJ8_dg_1 = "0";
                    bodyBuilder.add("data20", DJ8_dg_1);
                }

                if (selectedText21.equals("예")) {
                    DJ6_dg_1 = "1";
                    bodyBuilder.add("data21", DJ6_dg_1);
                } else {
                    DJ6_dg_1 = "0";
                    bodyBuilder.add("data21", DJ6_dg_1);
                }

                if (selectedText22.equals("예")) {
                    DK8_dg_1 = "1";
                    bodyBuilder.add("data22", DK8_dg_1);
                } else if (selectedText22.equals("아니오")) {
                    DK8_dg_1 = "0";
                    bodyBuilder.add("data22", DK8_dg_1);
                } else {
                    DK8_dg_1 = "8";
                    bodyBuilder.add("data22", DK8_dg_1);
                }

                if (selectedText23.equals("예")) {
                    DK9_dg_1 = "1";
                    bodyBuilder.add("data23", DK9_dg_1);
                } else if (selectedText23.equals("아니오")) {
                    DK9_dg_1 = "0";
                    bodyBuilder.add("data23", DK9_dg_1);
                } else {
                    DK9_dg_1 = "8";
                    bodyBuilder.add("data23", DK9_dg_1);
                }

                if (selectedText24.equals("예")) {
                    DK4_dg_1 = "1";
                    bodyBuilder.add("data24", DK4_dg_1);
                } else if (selectedText24.equals("아니오")) {
                    DK4_dg_1 = "0";
                    bodyBuilder.add("data24", DK4_dg_1);
                } else {
                    DK4_dg_1 = "8";
                    bodyBuilder.add("data24", DK4_dg_1);
                }

                if (selectedText25.equals("예")) {
                    DJ4_dg_1 = "1";
                    bodyBuilder.add("data25", DJ4_dg_1);
                } else {
                    DJ4_dg_1 = "0";
                    bodyBuilder.add("data25", DJ4_dg_1);
                }

                //서버로 보낼 데이터
                bodyBuilder.add("data26", Text26);
                bodyBuilder.add("data27", Text27);
                bodyBuilder.add("data28", Text28);
                bodyBuilder.add("data29", Text29);
                bodyBuilder.add("data30", Text30);
                bodyBuilder.add("data31", Text31);
                bodyBuilder.add("data32", Text32);
                bodyBuilder.add("data33", Text33);

                if (spn34.equals("1. 매우좋음")) {
                    D_1_1_1 = "1";
                    bodyBuilder.add("data34", D_1_1_1);
                } else if (spn34.equals("2. 좋음")) {
                    D_1_1_1 = "2";
                    bodyBuilder.add("data34", D_1_1_1);
                } else if (spn34.equals("3. 보통")) {
                    D_1_1_1 = "3";
                    bodyBuilder.add("data34", D_1_1_1);
                } else if (spn34.equals("5. 매우나쁨")) {
                    D_1_1_1 = "5";
                    bodyBuilder.add("data34", D_1_1_1);
                } else {
                    D_1_1_1 = "4";
                    bodyBuilder.add("data34", D_1_1_1);
                }

                if (spn35.equals("1. 변화없음")) {
                    BO1_1_1 = "1";
                    bodyBuilder.add("data35", BO1_1_1);
                } else if (spn35.equals("2. 체중감소")) {
                    BO1_1_1 = "2";
                    bodyBuilder.add("data35", BO1_1_1);
                } else if (spn35.equals("3. 증가")) {
                    BO1_1_1 = "3";
                    bodyBuilder.add("data35", BO1_1_1);
                } else {
                    BO1_1_1 = "8";
                    bodyBuilder.add("data35", BO1_1_1);
                }

                if (spn36.equals("1. 매일")) {
                    BS3_1_1 = "1";
                    bodyBuilder.add("data36", BS3_1_1);
                } else if (spn36.equals("2. 가끔")) {
                    BS3_1_1 = "2";
                    bodyBuilder.add("data36", BS3_1_1);
                } else if (spn36.equals("3. 과거에피웠으나현재안피움")) {
                    BS3_1_1 = "3";
                    bodyBuilder.add("data36", BS3_1_1);
                } else {
                    BS3_1_1 = "8";
                    bodyBuilder.add("data36", BS3_1_1);
                }

                if (spn37.equals("1. 안한다")) {
                    BE5_1_1 = "1";
                    bodyBuilder.add("data37", BE5_1_1);
                } else if (spn37.equals("2. 1일")) {
                    BE5_1_1 = "2";
                    bodyBuilder.add("data37", BE5_1_1);
                } else if (spn37.equals("3. 2일")) {
                    BE5_1_1 = "3";
                    bodyBuilder.add("data37", BE5_1_1);
                } else if (spn37.equals("4. 3일")) {
                    BE5_1_1 = "4";
                    bodyBuilder.add("data37", BE5_1_1);
                } else if (spn37.equals("5. 4일")) {
                    BE5_1_1 = "5";
                    bodyBuilder.add("data37", BE5_1_1);
                } else {
                    BE5_1_1 = "6";
                    bodyBuilder.add("data37", BE5_1_1);
                }

                if (spn38.equals("1. 1~2잔")) {
                    BO2_1_1 = "1";
                    bodyBuilder.add("data38", BO2_1_1);
                } else if (spn38.equals("2. 3~4잔")) {
                    BO2_1_1 = "2";
                    bodyBuilder.add("data38", BO2_1_1);
                } else if (spn38.equals("3. 5~6잔")) {
                    BO2_1_1 = "3";
                    bodyBuilder.add("data38", BO2_1_1);
                } else if (spn38.equals("4. 7~9잔")) {
                    BO2_1_1 = "4";
                    bodyBuilder.add("data38", BO2_1_1);
                } else if (spn38.equals("5. 10잔 이상")) {
                    BO2_1_1 = "5";
                    bodyBuilder.add("data38", BO2_1_1);
                } else {
                    BO2_1_1 = "8";
                    bodyBuilder.add("data38", BO2_1_1);
                }

                if (spn39.equals("1. 매일 복용함")) {
                    DI1_2_1 = "1";
                    bodyBuilder.add("data39", DI1_2_1);
                } else if (spn39.equals("2. 한달에 20일 이상 복용한다")) {
                    DI1_2_1 = "2";
                    bodyBuilder.add("data39", DI1_2_1);
                } else if (spn39.equals("3. 한달에 15일 이상 복용한다")) {
                    DI1_2_1 = "3";
                    bodyBuilder.add("data39", DI1_2_1);
                } else if (spn39.equals("4. 한달에 15일 미만 복용한다")) {
                    DI1_2_1 = "4";
                    bodyBuilder.add("data39", DI1_2_1);
                } else if (spn39.equals("5. 복용하지 않는다")) {
                    DI1_2_1 = "5";
                    bodyBuilder.add("data39", DI1_2_1);
                } else {
                    DI1_2_1 = "6";
                    bodyBuilder.add("data39", DI1_2_1);
                }

                if (spn40.equals("1. 매일 복용함")) {
                    DI2_2_1 = "1";
                    bodyBuilder.add("data40", DI2_2_1);
                } else if (spn40.equals("2. 한달에 20일 이상 복용한다")) {
                    DI2_2_1 = "2";
                    bodyBuilder.add("data40", DI2_2_1);
                } else if (spn40.equals("3. 한달에 15일 이상 복용한다")) {
                    DI2_2_1 = "3";
                    bodyBuilder.add("data40", DI2_2_1);
                } else if (spn40.equals("4. 한달에 15일 미만 복용한다")) {
                    DI2_2_1 = "4";
                    bodyBuilder.add("5. data40", DI2_2_1);
                } else if (spn40.equals("복용하지 않는다")) {
                    DI2_2_1 = "5";
                    bodyBuilder.add("data40", DI2_2_1);
                } else {
                    DI2_2_1 = "6";
                    bodyBuilder.add("data40", DI2_2_1);
                }

                if (spn41.equals("1. 대단히 많이 느낀다.")) {
                    BP1_1 = "1";
                    bodyBuilder.add("data41", BP1_1);
                } else if (spn41.equals("2. 많이 느끼는 편이다.")) {
                    BP1_1 = "2";
                    bodyBuilder.add("data41", BP1_1);
                } else if (spn41.equals("3. 조금 느끼는 편이다.")) {
                    BP1_1 = "3";
                    bodyBuilder.add("data41", BP1_1);
                } else if (spn41.equals("4. 거의 느끼지 않는다")) {
                    BP1_1 = "4";
                    bodyBuilder.add("data41", BP1_1);
                } else {
                    BP1_1 = "5";
                    bodyBuilder.add("data41", BP1_1);
                }

                bodyBuilder.add("data42", bmi);

                FormBody body = bodyBuilder.build();
                //post방식
                builder = builder.post(body);

                Request request = builder.build();

                Call call = client.newCall(request);

                NetworkCallback callback = new NetworkCallback();
                call.enqueue(callback);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class NetworkCallback implements Callback {

        @Override
        public void onFailure(Call call, IOException e) {

        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {

            try {
                //서버가 전달한 데이터를 추출한다

                final String result = response.body().string();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //JSONObject obj = new JSONObject(result);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });


            } catch (Exception e) {

            }
        }
    }
}