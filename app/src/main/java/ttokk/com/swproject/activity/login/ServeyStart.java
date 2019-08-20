package ttokk.com.swproject.activity.login;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.PhoneNumber;

import ttokk.com.swproject.R;
import ttokk.com.swproject.activity.main.MainActivity;

public class ServeyStart extends AppCompatActivity {
    ConstraintLayout go_servey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serveystart);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        go_servey = findViewById(R.id.go_servey);

        go_servey.setClickable(true);
        go_servey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServeyStart.this, ServeyActivity.class);
                startActivity(intent);
            }
        });

    }

}
