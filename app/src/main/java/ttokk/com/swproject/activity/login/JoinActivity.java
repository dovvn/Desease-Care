package ttokk.com.swproject.activity.login;


import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;

import java.util.ArrayList;

import ttokk.com.swproject.R;


public class JoinActivity extends AppCompatActivity {
    public static int REQUEST_CODE = 99;
    Button go_servey_start;
    EditText userId, userPw;
    CheckBox pref1, pref2, pref3, pref4, pref5, pref6, pref7, pref8, pref9, pref10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        pref1 = findViewById(R.id.pref1);
        pref2 = findViewById(R.id.pref2);
        pref3 = findViewById(R.id.pref3);
        pref4 = findViewById(R.id.pref4);
        pref5 = findViewById(R.id.pref5);
        pref6 = findViewById(R.id.pref6);
        pref7 = findViewById(R.id.pref7);
        pref8 = findViewById(R.id.pref8);
        pref9 = findViewById(R.id.pref9);
        pref10 = findViewById(R.id.pref10);

        AccessToken accessToken = AccountKit.getCurrentAccessToken();
        if (accessToken != null) {
            goToProfileInActivity();
        }
        go_servey_start = findViewById(R.id.go_servey_start);
        //userId = findViewById(R.id.user_id);
        userPw = findViewById(R.id.user_pw);

        //회원정보 입력 완료, 서버 송신
        go_servey_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(LoginType.PHONE);
            }
        });
    }

    public void btnMethod(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.button:
                Intent intent1 = new Intent(JoinActivity.this, informationActivity.class);
                startActivity(intent1);
                break;
            case R.id.button1:
                //아이디 검증
                break;
            case R.id.button2:
                Intent intent2 = new Intent(JoinActivity.this, information2Activity.class);
                startActivity(intent2);
                break;
        }
    }

    //핸드폰 인증
    private void login(LoginType loginType) {
        final Intent intent = new Intent(this, AccountKitActivity.class);
        AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                new AccountKitConfiguration.AccountKitConfigurationBuilder(
                        loginType,
                        AccountKitActivity.ResponseType.CODE);
        intent.putExtra(
                AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,
                configurationBuilder.build());
        startActivityForResult(intent, REQUEST_CODE);
    }

    //핸드폰 인증 후
    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            AccountKitLoginResult loginResult = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
            String toastMessage;
            if (loginResult.getError() != null) {
                toastMessage = loginResult.getError().getErrorType().getMessage();
            } else {
                toastMessage = "회원가입에 성공하셨습니다";
                goToProfileInActivity();
            }

            Toast.makeText(
                    this,
                    toastMessage,
                    Toast.LENGTH_LONG)
                    .show();
        }
    }

    private void goToProfileInActivity() {
        getPreferValue();
        Intent intent = new Intent(JoinActivity.this, ServeyStart.class);
        startActivityForResult(intent, 1);
    }

    //선호 질병 데이터 추출
    private void getPreferValue() {
        ArrayList<String> preferList = new ArrayList<String>();
        if (pref1.isChecked()) {
            preferList.add(pref1.getText().toString());
        }
        if (pref2.isChecked()) {
            preferList.add(pref2.getText().toString());
        }
        if (pref3.isChecked()) {
            preferList.add(pref3.getText().toString());
        }
        if (pref4.isChecked()) {
            preferList.add(pref4.getText().toString());
        }
        if (pref5.isChecked()) {
            preferList.add(pref5.getText().toString());
        }
        if (pref6.isChecked()) {
            preferList.add(pref6.getText().toString());
        }
        if (pref7.isChecked()) {
            preferList.add(pref7.getText().toString());
        }
        if (pref8.isChecked()) {
            preferList.add(pref8.getText().toString());
        }
        if (pref9.isChecked()) {
            preferList.add(pref9.getText().toString());
        }
        if (pref10.isChecked()) {
            preferList.add(pref10.getText().toString());
        }

        for (String e : preferList) {
            Log.v("선호 질병:", e);
        }
    }
}