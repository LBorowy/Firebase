package pl.lborowy.firebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_id)
    TextView id;
    @BindView(R.id.tv_name)
    TextView name;
    @BindView(R.id.tv_surname)
    TextView surname;
    @BindView(R.id.tv_city)
    TextView city;

    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.button_save)
    public void saveUser() {
        progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Saving...");
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
////        DatabaseReference myReference = database.getReference(id.getText().toString());
//        DatabaseReference myReference = database.getReference("baza").child(id.getText().toString());

        if (!TextUtils.isEmpty(id.getText().toString())) {
            progressDialog.setMessage("Saving...");
            FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myReference = database.getReference(id.getText().toString());
            DatabaseReference myReference = database.getReference("baza").child(id.getText().toString());

            String user = "Imię: " + name.getText().toString() + " Nazwisko: " +
                    surname.getText().toString() + " Miasto: " + city.getText().toString();
            progressDialog.show();
            myReference.setValue(user);
        }


        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            public void run() {
                handler.postDelayed(this, 2000);
                progressDialog.dismiss();
//                Toast.makeText(getBaseContext(), "Pracownik zapisany...", Toast.LENGTH_SHORT).show();
            }
        };
        handler.postDelayed(runnable, 2000);
    }

    @OnClick(R.id.tv_administrator)
    public void showLogin() {
        startActivity(new Intent(this, LoginActivity.class));
    }

}
