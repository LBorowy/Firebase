package pl.lborowy.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by RENT on 2017-07-05.
 */

public class LoginActivity extends AppCompatActivity{

    @BindView(R.id.administrator_login)
    TextView login;
    @BindView(R.id.administrator_password)
    TextView password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_layout);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.button_log_in)
    public void login() {

        String login1 = login.getText().toString().trim(); // trim usuwa spacje
        String password1 = password.getText().toString().trim();

        if (login1.equals("james") && password1.equals("bond")) {
            startActivity(new Intent(this, SearchActivity.class));
        } else {
            Toast.makeText(this, "Błędne hasło lub login", Toast.LENGTH_SHORT).show();
        }
    }
}
