package pl.lborowy.firebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myReference;
    @BindView(R.id.tv_id)
    EditText id;
    @BindView(R.id.tv_name)
    EditText name;
    @BindView(R.id.tv_surname)
    EditText surname;
    @BindView(R.id.tv_city)
    EditText city;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        database = FirebaseDatabase.getInstance();
    }
    @OnClick(R.id.button_save)
    public void saveUser() {
        progressDialog = new ProgressDialog(this);
        if (!TextUtils.isEmpty(id.getText().toString())) {
            progressDialog.setMessage("Saving...");
            database = FirebaseDatabase.getInstance();
//        DatabaseReference myReference = database.getReference(id.getText().toString());
            myReference = database.getReference(id.getText().toString());
            String user = createUser();
            progressDialog.show();
            myReference.setValue(user);
        } else {
            Toast.makeText(this, "Wprowadź ID pracownika...", Toast.LENGTH_SHORT).show();
        }
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 2000);
                progressDialog.dismiss();
//                Toast.makeText(MainActivity.this, "Pracownik zapisany...", Toast.LENGTH_SHORT).show();
            }
        };
        handler.postDelayed(runnable, 2000);
    }
    @NonNull
    private String createUser() {
        return "Imię: " + name.getText().toString() + " Nazwisko: " + surname.getText().toString()
                + " Miasto: " + city.getText().toString();
    }
    @OnClick(R.id.tv_administrator)
    public void showLogin() {
        startActivity(new Intent(this, LoginActivity.class));
    }
}
