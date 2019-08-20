package ttokk.com.swproject.activity.login;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import ttokk.com.swproject.R;
import ttokk.com.swproject.activity.main.MainActivity;


public class HealthServeyActivity extends AppCompatActivity {

    //데이터 변수
    int history_diabetes, smoking, is_hypertension, FPG, HDL, TG, HbA, creatinine, history_coronaryArtery, is_diabetes, leukocyte, total_colesterol,
            LDL, SBP, DBP, is_atrialFibrillation, age, PT_INR, billirubin, ammonia, AFP, albumin, platelet, DLD_serve, alcohol, is_hypercholesterolemia, is_chemicHeartDisease,
            is_previousStroke, is_obesity, area_num, history_cancer, meal_reg, salt_pref, phA, score, carstairs_level;
    double BMI, MT, DTR, SP, H;

    Spinner area;
    Spinner depSpin01, depSpin02, depSpin03, depSpin04, depSpin05, depSpin06, depSpin07, depSpin08, depSpin09, depSpin10,
            depSpin11, depSpin12, depSpin13, depSpin14, depSpin15, depSpin16, depSpin17;

    RadioButton check_A, check_B;
    Button go_main;

    EditText txt_TG, txt_leukocyte, txt_total_colesterol, txt_FPG, txt_HDL, txt_LDL, txt_HbA, txt_SBP, txt_DBP, txt_PT_INR, txt_bilirubin,
            txt_creatinine, txt_ammonia, txt_AFP, txt_albumin, txt_platelet, txt_DLD_serve;
    RadioGroup radio_is_aF, radio_alcohol, radio_hyper, radio_chemic, radio_cancer, radio_meal, radio_salt;
    RadioButton is_aF_value, alcohol_value, hyper_value, chemic_value, cancer_value, meal_value, salt_value;

    private String[] areaData = {"강원도(영서)", "강원도(영동)", "서울", "인천", "경기", "충북", "대전/충남", "대구/경북", "전북", "울산", "경남", "광주", "부산",
            "전남", "제주", "서귀포"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthservey);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        findMethod();
        spinMethod();

        //목록
        go_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                algorythm(); //알고리즘 실행
                Intent intent = new Intent(HealthServeyActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void findMethod() {

        //건강검진 EditTex
        txt_TG = (EditText) findViewById(R.id.txt_TG);
        txt_leukocyte = (EditText) findViewById(R.id.txt_leukocyte);
        txt_total_colesterol = (EditText) findViewById(R.id.txt_total_colesterol);
        txt_FPG = (EditText) findViewById(R.id.txt_FPG);
        txt_HDL = (EditText) findViewById(R.id.txt_HDL);
        txt_LDL = (EditText) findViewById(R.id.txt_LDL);
        txt_HbA = (EditText) findViewById(R.id.txt_HbA);
        txt_SBP = (EditText) findViewById(R.id.txt_SBP);
        txt_DBP = (EditText) findViewById(R.id.txt_DBP);
        txt_PT_INR = (EditText) findViewById(R.id.txt_PT_INR);
        txt_bilirubin = (EditText) findViewById(R.id.txt_bilirubin);
        txt_creatinine = (EditText) findViewById(R.id.txt_creatinine);
        txt_ammonia = (EditText) findViewById(R.id.txt_ammonia);
        txt_AFP = (EditText) findViewById(R.id.txt_AFP);
        txt_albumin = (EditText) findViewById(R.id.txt_albumin);
        txt_platelet = (EditText) findViewById(R.id.txt_platelet);
        txt_DLD_serve = (EditText) findViewById(R.id.txt_DLD_serve);

        //건강검진 라디오버튼
        radio_is_aF = (RadioGroup) findViewById(R.id.radio_is_aF);
        radio_alcohol = (RadioGroup) findViewById(R.id.radio_alcohol);
        radio_hyper = (RadioGroup) findViewById(R.id.radio_hyper);
        radio_chemic = (RadioGroup) findViewById(R.id.radio_chemic);
        radio_cancer = (RadioGroup) findViewById(R.id.radio_cancer);
        radio_meal = (RadioGroup) findViewById(R.id.radio_meal);
        radio_salt = (RadioGroup) findViewById(R.id.radio_salt);

        //라디오 버튼 선택된 값
        RadioButton is_aF_value = (RadioButton) findViewById(radio_is_aF.getCheckedRadioButtonId());
        RadioButton alcohol_value = (RadioButton) findViewById(radio_alcohol.getCheckedRadioButtonId());
        RadioButton hyper_value = (RadioButton) findViewById(radio_hyper.getCheckedRadioButtonId());
        RadioButton chemic_value = (RadioButton) findViewById(radio_chemic.getCheckedRadioButtonId());
        RadioButton cancer_value = (RadioButton) findViewById(radio_cancer.getCheckedRadioButtonId());
        RadioButton meal_value = (RadioButton) findViewById(radio_meal.getCheckedRadioButtonId());
        RadioButton salt_value = (RadioButton) findViewById(radio_salt.getCheckedRadioButtonId());

        //버튼
        go_main = (Button) findViewById(R.id.go_main);

        //지역
        area = (Spinner) findViewById(R.id.area);

        //스피너
        depSpin01 = (Spinner) findViewById(R.id.depSpin01);
        depSpin02 = (Spinner) findViewById(R.id.depSpin02);
        depSpin03 = (Spinner) findViewById(R.id.depSpin03);
        depSpin04 = (Spinner) findViewById(R.id.depSpin04);
        depSpin05 = (Spinner) findViewById(R.id.depSpin05);
        depSpin06 = (Spinner) findViewById(R.id.depSpin06);
        depSpin07 = (Spinner) findViewById(R.id.depSpin07);
        depSpin08 = (Spinner) findViewById(R.id.depSpin08);
        depSpin09 = (Spinner) findViewById(R.id.depSpin09);
        depSpin10 = (Spinner) findViewById(R.id.depSpin10);
        depSpin11 = (Spinner) findViewById(R.id.depSpin11);
        depSpin12 = (Spinner) findViewById(R.id.depSpin12);
        depSpin13 = (Spinner) findViewById(R.id.depSpin13);
        depSpin14 = (Spinner) findViewById(R.id.depSpin14);
        depSpin15 = (Spinner) findViewById(R.id.depSpin15);
        depSpin16 = (Spinner) findViewById(R.id.depSpin16);
        depSpin17 = (Spinner) findViewById(R.id.depSpin17);

        //16번 문항 A,B
        check_A = (RadioButton) findViewById(R.id.check_A);
        check_B = (RadioButton) findViewById(R.id.check_B);

    }

    public void spinMethod() {
        ArrayAdapter<String> areaAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, areaData);
        area.setAdapter(areaAdapter);

        String[] depSpinData01 = {"0. 없다.",
                "1. 물어보았을 때만 우울한 기분이라고 말한다.",
                "2. 자발적으로 우울한 기분이라고 말한다.",
                "3. 얼굴 표정, 자세, 목소리, 쉽게 우는 경향과 같은 비언어적인 표현을 통해 우울한 기분을 나타낸다.",
                "4. 오로지 우울한 기분만을, 언어적·비언어적 표현을 통해 나타낸다."};
        ArrayAdapter<String> depSpinAdapter01 = new ArrayAdapter<String>(this, R.layout.depspin_text_style, android.R.id.text1, depSpinData01);
        depSpinAdapter01.setDropDownViewResource(R.layout.depspin_text_style);
        depSpin01.setAdapter(depSpinAdapter01);

        String[] depSpinData02 = {"0. 없다.",
                "1. 자책하거나 자신이 사람들을 실망시킨다고 느낀다",
                "2. 죄를 지었다고 생각한다던가 과거의 실수나 자신이 한 나쁜 행위에 대해 반복적으로 생각한다.",
                "3. 현재의 병을 벌로 여긴다. 죄책망상이 있다.",
                "4. 비난 또는 탄핵하는 목소리를 듣거나 위협적인 환시를 경험한다."};
        ArrayAdapter<String> depSpinAdapter02 = new ArrayAdapter<String>(this, R.layout.depspin_text_style, android.R.id.text1, depSpinData02);
        depSpinAdapter02.setDropDownViewResource(R.layout.depspin_text_style);
        depSpin02.setAdapter(depSpinAdapter02);

        String[] depSpinData03 = {"0. 없다.",
                "1. 인생이 살 가치가 없다고 느낀다.",
                "2. 차라리 죽었으면 하거나 죽는 것에 대한 상상을 한다",
                "3. 현재의 병을 벌로 여긴다. 죄책망상이 있다.",
                "4. 자살 사고가 있거나 자살기도처럼 볼 수 있는 행동을 한다.",
                "5. 심각한 자살 기도를 한다."};
        ArrayAdapter<String> depSpinAdapter03 = new ArrayAdapter<String>(this, R.layout.depspin_text_style, android.R.id.text1, depSpinData03);
        depSpinAdapter03.setDropDownViewResource(R.layout.depspin_text_style);
        depSpin03.setAdapter(depSpinAdapter03);

        String[] depSpinData04 = {"0. 잠드는 데 어려움이 없다",
                "1. 간간이 잠들기가 어렵다(잠드는 데 30분 이상 걸린다).",
                "2. 매일 밤 잠들기가 어렵다."};
        ArrayAdapter<String> depSpinAdapter04 = new ArrayAdapter<String>(this, R.layout.depspin_text_style, android.R.id.text1, depSpinData04);
        depSpinAdapter04.setDropDownViewResource(R.layout.depspin_text_style);
        depSpin04.setAdapter(depSpinAdapter04);

        String[] depSpinData05 = {"0. 어려움이 없다.",
                "1. 새벽에 깨지만 다시 잠이 든다.",
                "2. 일단 깨어나면 다시 잠들 수 없다."};
        ArrayAdapter<String> depSpinAdapter05 = new ArrayAdapter<String>(this, R.layout.depspin_text_style, android.R.id.text1, depSpinData05);
        depSpinAdapter05.setDropDownViewResource(R.layout.depspin_text_style);
        depSpin05.setAdapter(depSpinAdapter05);

        String[] depSpinData06 = {""};
        ArrayAdapter<String> depSpinAdapter06 = new ArrayAdapter<String>(this, R.layout.depspin_text_style, android.R.id.text1, depSpinData06);
        depSpinAdapter06.setDropDownViewResource(R.layout.depspin_text_style);
        depSpin06.setAdapter(depSpinAdapter03);

        String[] depSpinData07 = {"0. 어려움이 없다.",
                "1. 제대로 할 수 없다고 느낀다. 일이나 취미와 같은 활동에 대해 피로하거나 기력이 떨어졌다고 느낀다.",
                "2. 일이나 취미와 같은 활동에 흥미를 잃는다 - 환자가 직접 이야기하거나 무관심, 우유부단, 망설임을 통해 간접적으로" +
                        "나타낸다(일이나 활동을 억지로 한다고 느낀다).",
                "3. 활동 시간이 줄거나 생산성이 떨어져 있다. 입원 환자의 경우, 병동생활에서의 개인적인 자질구레한 일을 제외한" +
                        "활동(원내 작업이나 취미)에 보내는 시간이 하루 3시간을 넘지 못한다.",
                "4. 현재의 병 때문에 일을 중단한다. 입원 환자의 경우, 병동생활에서의 개인적인 자질구레한 일 이외에는 전혀 활동을" +
                        "하지 않거나 도움 없이는 병동생활에서의 개인적인 자질구레한 일도 해내지 못한다."};
        ArrayAdapter<String> depSpinAdapter07 = new ArrayAdapter<String>(this, R.layout.depspin_text_style, android.R.id.text1, depSpinData07);
        depSpinAdapter07.setDropDownViewResource(R.layout.depspin_text_style);
        depSpin07.setAdapter(depSpinAdapter07);

        String[] depSpinData08 = {"0. 정상적으로 말하고 생각한다.",
                "1. 면담할 때 약간 지체되어 있다.",
                "2. 면담할 때 뚜렷이 지체되어 있다.",
                "3. 면담이 어려울 정도로 지체되어 있다.",
                "4. 완전한 혼미 상태에 있다."};
        ArrayAdapter<String> depSpinAdapter08 = new ArrayAdapter<String>(this, R.layout.depspin_text_style, android.R.id.text1, depSpinData08);
        depSpinAdapter08.setDropDownViewResource(R.layout.depspin_text_style);
        depSpin08.setAdapter(depSpinAdapter08);

        String[] depSpinData09 = {"0. 없다.",
                "1. 조금 초조한 듯하다.",
                "2. 손이나 머리카락 등을 만지작거린다.",
                "3. 가만히 앉아 있지 못하고 몸을 자꾸 움직인다.",
                "4. 손을 비비꼬거나 손톱을 물어뜯거나 머리카락을 잡아당기거나 입술을 깨문다."};
        ArrayAdapter<String> depSpinAdapter09 = new ArrayAdapter<String>(this, R.layout.depspin_text_style, android.R.id.text1, depSpinData09);
        depSpinAdapter09.setDropDownViewResource(R.layout.depspin_text_style);
        depSpin09.setAdapter(depSpinAdapter09);

        String[] depSpinData10 = {"0. 없다.",
                "1. 긴장감과 과민함을 느낀다.",
                "2. 사소한 일들에 대해 걱정을 한다.",
                "3. 얼굴 표정이나 말에서 염려하는 태도가 뚜렷하다.",
                "4. 묻지 않아도 심한 공포가 드러난다."};
        ArrayAdapter<String> depSpinAdapter10 = new ArrayAdapter<String>(this, R.layout.depspin_text_style, android.R.id.text1, depSpinData10);
        depSpinAdapter10.setDropDownViewResource(R.layout.depspin_text_style);
        depSpin10.setAdapter(depSpinAdapter10);

        String[] depSpinData11 = {"0. 없다. 위장관계 - 입마름, 방귀, 소화불량, 설사, 심한 복통, 트림",
                "1. 경도. 심혈관계 - 심계항진, 두통",
                "2. 중등도. 호흡기계 - 과호흡, 한숨",
                "3. 고도. 호흡기계 - 빈뇨, 발한",
                "4. 최고도(기능을 전혀 할 수 없을 정도이다)."};
        ArrayAdapter<String> depSpinAdapter11 = new ArrayAdapter<String>(this, R.layout.depspin_text_style, android.R.id.text1, depSpinData11);
        depSpinAdapter11.setDropDownViewResource(R.layout.depspin_text_style);
        depSpin11.setAdapter(depSpinAdapter11);

        String[] depSpinData12 = {"0. 없다.",
                "1. 입맛을 잃었지만 치료진의 격려 없이도 먹는다. 속이 더부룩하다.",
                "2. 치료진의 강요 없이는 잘 먹지 않는다. 하제나 소화제 등 위장관계 증상에 대한 약제를 요구하거나 필요로 한다"};
        ArrayAdapter<String> depSpinAdapter12 = new ArrayAdapter<String>(this, R.layout.depspin_text_style, android.R.id.text1, depSpinData12);
        depSpinAdapter12.setDropDownViewResource(R.layout.depspin_text_style);
        depSpin12.setAdapter(depSpinAdapter12);

        String[] depSpinData13 = {"0. 없다.",
                "1. 팔, 다리, 등, 머리가 무겁다. 등의 통증, 두통, 근육통. 기운이 없고 쉽게 피곤해진다.",
                "2. 매우 뚜렷한 신체증상이 있다."};
        ArrayAdapter<String> depSpinAdapter13 = new ArrayAdapter<String>(this, R.layout.depspin_text_style, android.R.id.text1, depSpinData13);
        depSpinAdapter13.setDropDownViewResource(R.layout.depspin_text_style);
        depSpin13.setAdapter(depSpinAdapter13);

        String[] depSpinData14 = {"0. 없다.",
                "1. 경도",
                "2. 고도"};
        ArrayAdapter<String> depSpinAdapter14 = new ArrayAdapter<String>(this, R.layout.depspin_text_style, android.R.id.text1, depSpinData14);
        depSpinAdapter14.setDropDownViewResource(R.layout.depspin_text_style);
        depSpin14.setAdapter(depSpinAdapter14);

        String[] depSpinData15 = {"0. 없다.",
                "1. 몸에 대해 많이 생각한다.",
                "2. 건강에 대해 집착한다.",
                "3. 건강이 나쁘다고 자주 호소하거나 도움을 청한다.",
                "4. 건강염려증적 망상이 있다."};
        ArrayAdapter<String> depSpinAdapter15 = new ArrayAdapter<String>(this, R.layout.depspin_text_style, android.R.id.text1, depSpinData15);
        depSpinAdapter15.setDropDownViewResource(R.layout.depspin_text_style);
        depSpin15.setAdapter(depSpinAdapter15);

        String[] depSpinData17 = {"0. 자신이 우울하고 병들었다는 것을 인식한다.",
                "1. 병들었다는 것을 인정하지만 음식, 날씨, 과로, 바이러스, 휴식 부족 등이 이유라고 생각한다.",
                "2. 자신의 병을 전적으로 부인한다."};
        ArrayAdapter<String> depSpinAdapter17 = new ArrayAdapter<String>(this, R.layout.depspin_text_style, android.R.id.text1, depSpinData17);
        depSpinAdapter17.setDropDownViewResource(R.layout.depspin_text_style);
        depSpin17.setAdapter(depSpinAdapter17);
    }

    //16번 A,B 체크 처리
    public void RadioCheck(View v) {
        if (check_A.isChecked()) {
            String[] depSpinData16_A = {"0. 없다.",
                    "1. 병력에 의해 평가할 때",
                    "2. 체중감소가 없다.",
                    "3. 현재의 병으로 인해 체중감소가 있는 것 같다",
                    "4. (환자에 따르면) 확실한 체중감소가 있다."};
            ArrayAdapter<String> depSpinAdapter16 = new ArrayAdapter<String>(this, R.layout.depspin_text_style, android.R.id.text1, depSpinData16_A);
            depSpinAdapter16.setDropDownViewResource(R.layout.depspin_text_style);
            depSpin16.setAdapter(depSpinAdapter16);

        } else if (check_B.isChecked()) {
            String[] depSpinData16_B = {"0. 주당 0.5㎏ 미만의 체중감소.",
                    "1. 주당 0.5㎏ 이상, 1㎏ 미만의 체중감소",
                    "2. 주당 1㎏ 이상의 체중감소."};
            ArrayAdapter<String> depSpinAdapter16 = new ArrayAdapter<String>(this, R.layout.depspin_text_style, android.R.id.text1, depSpinData16_B);
            depSpinAdapter16.setDropDownViewResource(R.layout.depspin_text_style);
            depSpin16.setAdapter(depSpinAdapter16);
        }
    }

    //우울증 합 추출
    public void sumDepression() {
        int depSum = 0;
        String[] spinValue = new String[17];

        spinValue[0] = depSpin01.getSelectedItem().toString();
        spinValue[1] = depSpin02.getSelectedItem().toString();
        spinValue[2] = depSpin03.getSelectedItem().toString();
        spinValue[3] = depSpin04.getSelectedItem().toString();
        spinValue[4] = depSpin05.getSelectedItem().toString();
        spinValue[5] = depSpin06.getSelectedItem().toString();
        spinValue[6] = depSpin07.getSelectedItem().toString();
        spinValue[7] = depSpin08.getSelectedItem().toString();
        spinValue[8] = depSpin09.getSelectedItem().toString();
        spinValue[9] = depSpin10.getSelectedItem().toString();
        spinValue[10] = depSpin11.getSelectedItem().toString();
        spinValue[11] = depSpin12.getSelectedItem().toString();
        spinValue[12] = depSpin13.getSelectedItem().toString();
        spinValue[13] = depSpin14.getSelectedItem().toString();
        spinValue[14] = depSpin15.getSelectedItem().toString();
        spinValue[15] = depSpin16.getSelectedItem().toString();
        spinValue[16] = depSpin17.getSelectedItem().toString();

        for (int i = 0; i < spinValue.length; i++) {
            char nmb = ' ';
            nmb = spinValue[i].charAt(0); //첫번째 문자 가져오기

            switch (nmb) {
                case '0':
                    depSum += 0;
                    break;
                case '1':
                    depSum += 1;
                    break;
                case '2':
                    depSum += 2;
                    break;
                case '3':
                    depSum += 3;
                    break;
                case '4':
                    depSum += 4;
                    break;
            }
        }
        Log.i("sum", depSum + "");
    }

    //사는지역으로 날씨 정보 크롤링
    public void getWeather() {
        String area_value = area.getSelectedItem().toString(); //입력한 사는 지역
        for (int i = 0; i < areaData.length; i++) {
            if (area_value.equals(areaData[i])) {
                area_num = i;
            }
        }
        Log.v("지역번호", area_num + "");
    }

    public void algorythm() {
        sumDepression(); //우울증 총 합

        //사는 지역 및 날씨 정보
        getWeather();

        //editText
        TG = Integer.parseInt(txt_TG.getText().toString());
        FPG = Integer.parseInt(txt_FPG.getText().toString());
        HDL = Integer.parseInt(txt_HDL.getText().toString());
        HbA = Integer.parseInt(txt_HbA.getText().toString());
        creatinine = Integer.parseInt(txt_creatinine.getText().toString());
        leukocyte = Integer.parseInt(txt_leukocyte.getText().toString());
        total_colesterol = Integer.parseInt(txt_total_colesterol.getText().toString());
        LDL = Integer.parseInt(txt_LDL.getText().toString());
        SBP = Integer.parseInt(txt_SBP.getText().toString());
        DBP = Integer.parseInt(txt_DBP.getText().toString());
        PT_INR = Integer.parseInt(txt_PT_INR.getText().toString());
        billirubin = Integer.parseInt(txt_bilirubin.getText().toString());
        creatinine = Integer.parseInt(txt_creatinine.getText().toString());
        ammonia = Integer.parseInt(txt_ammonia.getText().toString());
        AFP = Integer.parseInt(txt_AFP.getText().toString());
        albumin = Integer.parseInt(txt_albumin.getText().toString());
        platelet = Integer.parseInt(txt_platelet.getText().toString());
        DLD_serve = Integer.parseInt(txt_DLD_serve.getText().toString());

        //라디오버튼 string->int 변경, 아니요-0 네-1
        alcohol = getIntChecked(is_aF_value.getText().toString());
        is_atrialFibrillation = getIntChecked(alcohol_value.getText().toString());
        is_hypercholesterolemia = getIntChecked(hyper_value.getText().toString());
        is_chemicHeartDisease = getIntChecked(chemic_value.getText().toString());
        history_cancer = getIntChecked(cancer_value.getText().toString());
        meal_reg = getIntChecked(meal_value.getText().toString());
        salt_pref = getIntChecked(salt_value.getText().toString());

//        Diabetes diabetes = new Diabetes(age, history_diabetes, smoking, BMI, is_hypertension, FPG, HDL, TG, HbA); //뇌졸중
//        Log.v("뇌졸중 결과", diabetes.cal_diabetes() + "");
    }

    public int getIntChecked(String value) {
        if (value.equals("아니요")) {
            return 0;
        } else { //네
            return 1;
        }
    }
}