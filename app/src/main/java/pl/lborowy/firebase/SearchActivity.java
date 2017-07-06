package pl.lborowy.firebase;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
public class SearchActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myReference;
    private ProgressDialog progressDialog;
    @BindView(R.id.id_search)
    EditText searchId;
    @BindView(R.id.id_result)
    TextView resultId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        database = FirebaseDatabase.getInstance();
    }


    @OnClick(R.id.button_search)
    public void searchById() {

        String x = searchId.getText().toString();

        if (!TextUtils.isEmpty(x)) {
            progressDialog = new ProgressDialog(this);
            progressDialog.show();
            progressDialog.setMessage("Szukam pracownika...");
            myReference = database.getReference(x);
            myReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String value = dataSnapshot.getValue(String.class);
                    resultId.setText(value);

                    if (TextUtils.isEmpty(resultId.getText().toString())) {
                        Toast.makeText(SearchActivity.this, "Brak pracownika o danym ID", Toast.LENGTH_SHORT).show();
                    }
                    progressDialog.dismiss();
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(SearchActivity.this, "Brak pracownika o takim IDdd", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            });
        }
        else {
            Toast.makeText(this, "Wprowadź dane", Toast.LENGTH_SHORT).show();
        }
    }
}
